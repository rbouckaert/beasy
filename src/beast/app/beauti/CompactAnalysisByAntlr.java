package beast.app.beauti;




import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.PartitionContext;
import beast.app.beauti.PriorListInputEditor.MRCAPriorProvider;
import beast.app.beauti.compactanalysis.*;
import beast.app.beauti.compactanalysis.CAParser.*;
import beast.core.BEASTInterface;
import beast.core.Distribution;
import beast.core.Input;
import beast.core.MCMC;
import beast.core.parameter.Parameter;
import beast.core.util.CompoundDistribution;
import beast.core.util.Log;
import beast.evolution.alignment.Alignment;
import beast.evolution.alignment.Taxon;
import beast.evolution.alignment.TaxonSet;
import beast.evolution.tree.TreeDistribution;
import beast.math.distributions.MRCAPrior;
import beast.util.AddOnManager;

public class CompactAnalysisByAntlr extends CABaseListener {
	BeautiDoc doc = null;

	public CompactAnalysisByAntlr() {
		this.doc = new BeautiDoc();
		this.doc.beautiConfig = new BeautiConfig();
		this.doc.beautiConfig.initAndValidate();
	}

	public CompactAnalysisByAntlr(BeautiDoc doc) {
		this.doc = doc;
		if (this.doc == null) {
			this.doc = new BeautiDoc();
			this.doc.beautiConfig = new BeautiConfig();
			this.doc.beautiConfig.initAndValidate();
		}
	}
	
	public void enterCAsentence(CasentenceContext ctx) {
		System.out.println(ctx.getText());
	}
	
	
	public void parseCA(String CASentence) {
        // Custom parse/lexer error listener
        BaseErrorListener errorListener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg, RecognitionException e) {
                throw new CAParsingException(msg, charPositionInLine, line);
            }
        };

        // Get our lexer
	    CALexer lexer = new CALexer(new ANTLRInputStream(CASentence));
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

	    // Get a list of matched tokens
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	 
	    // Pass the tokens to the parser
	    CAParser parser = new CAParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
	 
        ParseTree parseTree = parser.casentence();
//	    // Specify our entry point
//	    CasentenceContext CASentenceContext = parser.casentence();
//	 
//	    // Walk it and attach our listener
//	    ParseTreeWalker walker = new ParseTreeWalker();
//	    AntlrCompactAnalysisListener listener = new AntlrCompactAnalysisListener();
//	    walker.walk(listener, CASentenceContext);


        // Traverse parse tree, constructing BEAST tree along the way
        CAASTVisitor visitor = new CAASTVisitor(doc);

        visitor.visit(parseTree);
	}
	

	public class CAASTVisitor extends CABaseVisitor<Object> {
		
		private Set<PartitionContext> partitionContext;
		
		public CAASTVisitor(BeautiDoc doc) {
			partitionContext = new LinkedHashSet<>();
		}
		
		@Override
		public Object visitTemplate(TemplateContext ctx) {
			TemplatenameContext name = (TemplatenameContext) ctx.children.get(ctx.children.size() - 1);

			String template = name.getText();
			if (!template.toLowerCase().endsWith("xml")) {
				template += ".xml";
			}
			doc.loadNewTemplate(template);
			
			return super.visitTemplate(ctx);
		}
		
		
//		@Override
//		public BEASTInterface visitPartition(beast.app.beauti.compactanalysis.CAParser.PartitionContext ctx) {
//		String pattern = ctx.getChild(1).getText();
//		partitionContext.clear();
//		for (PartitionContext p : doc.partitionNames) {
//			if (p.partition.matches(pattern)) {
//				partitionContext.add(new PartitionContext(p.partition, p.siteModel, p.clockModel, p.tree));
//			}
//		}
//		return super.visitPartition(ctx);

		private void processPattern(String pattern) {
			partitionContext.clear();
			for (PartitionContext p : doc.partitionNames) {
				if (p.partition.matches(pattern)) {
					partitionContext.add(new PartitionContext(p.partition, p.siteModel, p.clockModel, p.tree));
				}
			}
		}
		
		@Override
		public Object visitImport_(Import_Context ctx) {
			String providerID = "Import Alignment";
			String fileName = null;
			List<String> args = new ArrayList<>();
			if (ctx.getChild(1) instanceof FilenameContext) {
				fileName = ctx.getChild(1).getText(); 
				for (int i = 2; i < ctx.children.size(); i++) {
					if (ctx.getChild(i) instanceof ArgContext) {
						args.add(ctx.getChild(i).getText());
					}
				}
			} else {
				providerID = ctx.getChild(1).getText();
				if (providerID.startsWith("'") && providerID.endsWith("'")) {
					providerID = providerID.substring(1, providerID.length()-1);
				}
				providerID = ".*" + providerID + ".*";
				fileName = ctx.getChild(2).getText();
				for (int i = 3; i < ctx.children.size(); i++) {
					if (ctx.getChild(i) instanceof ArgContext) {
						args.add(ctx.getChild(i).getText());
					}
				}
			}
						
			List<BeautiAlignmentProvider> providerList = doc.beautiConfig.alignmentProvider;
			BeautiAlignmentProvider provider = null;
			for (BeautiAlignmentProvider p : providerList) {
				if (p.getID().matches(providerID)) {
					provider = p;
					break;
				}
			}
			if (provider == null) {
				String providers = providerList.get(0).getID();
				for (int i = 1; i < providerList.size(); i++) {
					providers += "," + providerList.get(i).getID();
				}
				throw new IllegalArgumentException("Could not match '" + providerID+"' to one of these providers: " + providers);
			}
			
	        List<BEASTInterface> beastObjects = provider.getAlignments(doc, new File[]{new File(fileName)}, args.toArray(new String[]{}));

	        if (beastObjects != null) {
		        for (BEASTInterface o : beastObjects) {
		        	if (o instanceof Alignment) {	        		
		        		try {
		        			BeautiDoc.createTaxonSet((Alignment) o, doc);
		        		} catch(Exception ex) {
		        			ex.printStackTrace();
		        		}
		        	}
		        }
	        }

	        doc.connectModel();
	        doc.fireDocHasChanged();
	        
	        if (beastObjects != null) {
		        for (BEASTInterface o : beastObjects) {
		        	if (o instanceof MRCAPrior) {
	        			doc.addMRCAPrior((MRCAPrior) o);
		        	}
		        }
	        }
	        // set partition context to latest partition
			PartitionContext p = doc.partitionNames.get(doc.partitionNames.size() - 1);
			partitionContext.clear();
			partitionContext.add(new PartitionContext(p.partition, p.siteModel, p.clockModel, p.tree));

			doc.scrubAll(true, false);
			return null;
		}
		
		
		Set<Input<?>> inputSet;
		Map<Input<?>, BEASTInterface> mapInputToObject;
		
		@Override
		public Object visitInputidentifier(InputidentifierContext ctx) {
			String idPattern = null;
			String partitionPattern = null;
			String elementPattern = null;
			String inputPattern = null;
			
			// collect all possible inputs
			for (Object o : ctx.children) {
				if (o instanceof IdPatternContext) {
					idPattern = ((IdPatternContext) o).getText().trim();
					// remove brackets
					idPattern = idPattern.substring(1, idPattern.length() - 1) + ".*";
				}
				if (o instanceof ElemntNameContext) {
					elementPattern = ((ElemntNameContext) o).getText().trim();
				}
				if (o instanceof InputnameContext) {
					inputPattern = ((InputnameContext) o).getText().trim();
				}
				if (o instanceof PartitionPatternContext) {
					partitionPattern = ((PartitionPatternContext)o).getText().trim();
					// remove braces
					partitionPattern = partitionPattern.substring(1, partitionPattern.length()-1);
				}
			}
			mapInputToObject = InputFilter.initInputMap(doc);
			InputFilter filter = new InputFilter();
			if (partitionPattern != null) {
				inputSet = filter.getInputSet(doc, partitionPattern, '*', null, null);
			} else {
				inputSet = filter.getInputSet(doc, idPattern, elementPattern, inputPattern);
			}
			
			return null;
		}

		@Override
		public Object visitPartitionPattern(PartitionPatternContext ctx) {
			String partitionPattern = ctx.getText().trim();
			InputFilter filter = new InputFilter();
			inputSet = filter.getInputSet(doc, partitionPattern, '*', null, null);
			return inputSet;
		}
		

		@Override
		public Object visitLink(LinkContext ctx) {
			if (ctx.getChildCount() == 2) {
				processPattern(".*");
			} else {
				processPattern(ctx.getChild(2).getText());
			}
			if (partitionContext.size() <= 1) {
				throw new IllegalArgumentException("Link command : At least two partitions must be selected '" + ctx.getText() + "'");
			}
			DocumentEditor.link(doc, ctx.getChild(1).getText(), partitionContext);
			return null;
		}
		
		@Override
		public Object visitUnlink(UnlinkContext ctx) {
			if (ctx.getChildCount() == 2) {
				processPattern(".*");
			} else {
				processPattern(ctx.getChild(2).getText());
			}
			if (partitionContext.size() <= 1) {
				throw new IllegalArgumentException("Command unlink: At least one partition must be selected " + ctx.getText());
			}
			DocumentEditor.unlink(doc, ctx.getChild(1).getText(), partitionContext);
			return null;
		}
		
		@Override
		public Object visitSet(SetContext ctx) {
			// set <identifier> = <value>;
			String value = ctx.getChild(3).getText();;
			
			// set up inputSet member variable:
			visitChildren(ctx);
			
			
			for(Input<?> in : inputSet) {
				BEASTInterface p = null;
				if (in.get() instanceof Parameter) {
					p = (BEASTInterface) in.get();
					in = ((Parameter.Base<?>)in.get()).valuesInput;
				}
				Log.info("Setting " + in.getName() + "[" + mapInputToObject.get(in).getID() + "]" + " = " + value);
				in.set(value);
				if (p != null) {
					p.initAndValidate();
				}
			}

			if (inputSet.size() == 0) {
				throw new IllegalArgumentException("Command set: cannot find suitable match for " + ctx.getText());
			}
			return null;
		}

		@Override
		public Object visitUse(UseContext ctx) {
			// set up inputSet
			mapInputToObject = InputFilter.initInputMap(doc);
			super.visitUse(ctx);
			if (inputSet == null) {
				setupInputSet(((MCMC)doc.mcmc.get()).posteriorInput.get());
			}
			if (inputSet.size() == 0) {
				throw new IllegalArgumentException("Command use: cannot find suitable match for " + ctx.getText());
			}
			
			BeautiSubTemplate subTemplate = getSubTemplateName(ctx);
			
			// collect parameters
			List<String> param = new ArrayList<>();
			List<Object> value = new ArrayList<>();
			String newID = collectParameters(ctx, param, value);
			
			int instantCount = 0;
			for(Input<?> in : inputSet) {
				BEASTInterface o = mapInputToObject.get(in);
				if (in.getType() != null) {
					if (in.get() instanceof List<?>) {
						if (in.getType().isAssignableFrom(subTemplate._class)) {
							BEASTInterface bo = createSubnet(subTemplate, param, value, newID, o, in);
							boolean found = false;
							for (Object o2 : (Collection<?>) in.get()) {
								if (o2 == bo) {
									found = true;
									instantCount++;
									Log.info("Already using " + bo.getID());
								}
							}
							if (!found) {
								if (in.canSetValue(bo, o)) {
									Log.info("Using " + in.getName() + "[" +o.getID() +"] = " + bo.getID());
									in.setValue(bo, o);
									instantCount++;
								}
							}
						}
					} else {
						if (in.getType().isAssignableFrom(subTemplate._class)) { 
							BEASTInterface bo = createSubnet(subTemplate, param, value, newID, o, in);
							if (in.canSetValue(bo, o)) {
								Log.info("Using " + in.getName() + "[" +o.getID() +"] = " + bo.getID());
								in.setValue(bo, o);
								instantCount++;
							}
						}
					}
				}
			}
			
			if (ctx.getParent() instanceof ValueContext) {
				BEASTInterface bo = createSubnet(subTemplate, param, value, newID, null, null);
				return bo;
			}
			
			if (instantCount == 0) {
				throw new IllegalArgumentException("Command use: cannot find suitable input to match for " + ctx.getText());				
			} else {
				doc.scrubAll(false, false);
			}


			return null;
		}
		
		
		// hack to replace tree prior
		private void purgeTreeDistribution(BeautiSubTemplate subTemplate, PartitionContext pc, Input<?> in, BEASTInterface o) {
			if (o instanceof CompoundDistribution && in.getName().equals("distribution") && 
					TreeDistribution.class.isAssignableFrom(subTemplate._class)) {
				// may need to replace existing distribution
				CompoundDistribution dist = (CompoundDistribution) o;
				Distribution treeDist = null;
				//Alignment a = doc.getPartition(o);
				for (Distribution d : dist.pDistributions.get()) {
					if (d instanceof TreeDistribution) {
						String distID = d.getID();
						String tree = distID.substring(distID.indexOf('.') + 3);
						if (tree.equals(pc.tree)) {
							treeDist = d;
						}
					}
				}
				if (treeDist != null) {
					dist.pDistributions.get().remove(treeDist);
				}
			}
			
		}

		private BEASTInterface createSubnet(BeautiSubTemplate subTemplate, List<String> param, List<Object> value,
				String id, BEASTInterface o, Input<?> input) {
			PartitionContext pc = getPartitionContext(o, subTemplate);
			if (!pc.partition.equals("null")) {
				Log.info(pc.toString());
				purgeTreeDistribution(subTemplate, pc, input, o);
			}
			
			BEASTInterface bo = subTemplate.createSubNet(pc, true);
    		for (int i = 0; i < param.size(); i++) {
    			Input<?> in = bo.getInput(param.get(i));
    			if (in.get() instanceof Parameter.Base) {
    				Parameter.Base<?>  p = (Parameter.Base<?>) in.get();
    				p.valuesInput.setValue(value.get(i), p);
    			} else {
    				bo.setInputValue(param.get(i), value.get(i));
    			}
    		}
    		if (id != null) {
    			bo.setID(id);
    		}
    		return bo;
		}

		/** determine partition context for a beast object
		 * by parsing its ID. 
		 * **/
		private PartitionContext getPartitionContext(BEASTInterface o, BeautiSubTemplate subTemplate) {
			if (o == null) {
	            return new PartitionContext("null", "null", "null", "null");
			}
			
			String id = o.getID();
			if (id.indexOf('.') == -1) {
				// determine partition from mainID of subtemplate
	            id = subTemplate.mainInput.get();
				if (id.indexOf('.') == -1) {
		            if (doc.possibleContexts.size() == 1) {
		            	return (PartitionContext) doc.possibleContexts.toArray()[0];
		            }
		            return new PartitionContext("null", "null", "null", "null");
		            //throw new IllegalArgumentException("Could not determine partition context for '"+o.getID()+"': a more specific ID would help");
				}
		        char c = id.charAt(id.indexOf('.') + 1);
		        PartitionContext base = (PartitionContext) doc.possibleContexts.toArray()[0];
		        for (PartitionContext p : doc.possibleContexts) {
	            	switch (c) {
	            	case 's': 
	            		if (!p.siteModel.equals(base.siteModel)) {
	    		            throw new IllegalArgumentException("Could not determine partition context based on site model");
	            		}
	            	break;
	            	case 't': 
	            		if (!p.tree.equals(base.tree)) {
	    		            throw new IllegalArgumentException("Could not determine partition context based on tree model");
	            		}
	            	break;
	            	case 'c': 
	            		if (!p.clockModel.equals(base.clockModel)) {
	    		            throw new IllegalArgumentException("Could not determine partition context based on clock model");
	            		}
	            	break;
	            	default : 
	            		if (!p.partition.equals(base.partition)) {
	    		            throw new IllegalArgumentException("Could not determine partition context based on partition");
	            		}
	            	}
	            }
		        return base;
			}
			
			// determine partition from ID of receiving object
	        String partition = id.substring(id.indexOf('.') + 1);
        	char c = 'x';
	        if (partition.indexOf(':') >= 0) {
	        	c = partition.charAt(0);
	        	partition = partition.substring(partition.indexOf(':') + 1);
	        }
	        
            for (PartitionContext p : doc.possibleContexts) {
            	switch (c) {
            	case 's': if (p.siteModel.equals(partition)) {
            		return p;
            	}
            	break;
            	case 't': if (p.tree.equals(partition)) {
            		return p;
            	}
            	break;
            	case 'c': if (p.clockModel.equals(partition)) {
            		return p;
            	}
            	break;
            	default : if (p.partition.equals(partition)) {
            		return p;
            	}
            	}
            }
            if (doc.possibleContexts.size() == 1) {
            	return (PartitionContext) doc.possibleContexts.toArray()[0];
            }

            
            throw new IllegalArgumentException("Could not determine partition context for '"+o.getID()+"': a more specific ID would help");
		}

		// assume this specifies a subtemplate
		// [<id pattern> =]? <SubTemplate>[(param1=value[,param2=value,...])];
		private BeautiSubTemplate getSubTemplateName(ParserRuleContext ctx) {
			String subTemplateName;
			if (ctx.getChild(1) instanceof InputidentifierContext) {
				subTemplateName = ctx.getChild(3).getText();
			} else {
				// match anything
				subTemplateName = ctx.getChild(0).getText();
				if (subTemplateName.equals("use")) {
					subTemplateName = ctx.getChild(1).getText();
				}
			}
			if (subTemplateName.indexOf('(') >= 0) {
				subTemplateName = subTemplateName.substring(0, subTemplateName.indexOf('('));
			}
	        for (BeautiSubTemplate subTemplate : doc.beautiConfig.subTemplates) {
	        	if (subTemplate.getID().matches(subTemplateName)) {
	        		return subTemplate;
	        	}
	        }
			throw new IllegalArgumentException("Command use: cannot find matching template for " + subTemplateName);
		}

		private String collectParameters(ParserRuleContext ctx, List<String> param, List<Object> value) {
			String id = null;
			for (int i = 2; i < ctx.getChildCount(); i++) {
				if (ctx.getChild(i) instanceof KeyContext) {
					String key = ctx.getChild(i).getText();
					String val = ctx.getChild(i + 2).getText();
					
					if (key.trim().toLowerCase().equals("id")) {
						id = val.trim();
					} else {
						param.add(key.trim());
						if (doc.pluginmap.containsKey(val)) {
							value.add(doc.pluginmap.get(val));
						} else {
							value.add(val.trim());
						}
					}
				}
			}
			return id;
		}

		private void setupInputSet(BEASTInterface object) {
			inputSet = new LinkedHashSet<>();
			for (BEASTInterface o : InputFilter.getDocumentObjects(object)) {
				for (Input<?> input : o.listInputs()) {
					inputSet.add(input);
					if (input.getType() == null) {
						input.determineClass(o);
					}
				}
			}
		}
		
		@Override
		public Object visitRename(RenameContext ctx) {
			String partition = ctx.getChild(1).getText();

			String oldName = null, newName;
			if (ctx.getChildCount() > 4) {
				oldName = ctx.getChild(2).getText();
				newName = ctx.getChild(4).getText();
			} else {
				switch (partition) {
				case "sitemodel" : 
					oldName = ((PartitionContext)doc.possibleContexts.toArray()[0]).siteModel;
					break;
				case "tree" : 
					oldName = ((PartitionContext)doc.possibleContexts.toArray()[0]).tree;
					break;
				case "clock" : 
					oldName = ((PartitionContext)doc.possibleContexts.toArray()[0]).clockModel;
					break;
				default:
					oldName = ((PartitionContext)doc.possibleContexts.toArray()[0]).partition;
				}
				
				newName = ctx.getChild(3).getText();
			}
			int partitionID;
			switch (partition) {
			case "sitemodel" : 
				partitionID = BeautiDoc.SITEMODEL_PARTITION;
				break;
			case "tree" : 
				partitionID = BeautiDoc.TREEMODEL_PARTITION;
				break;
			case "clock" : 
				partitionID = BeautiDoc.CLOCKMODEL_PARTITION;
				break;
			default:
				partitionID = BeautiDoc.ALIGNMENT_PARTITION;
			}
			doc.renamePartition(partitionID, oldName, newName);
			
			return super.visitRename(ctx);
		}
		
		
	    List<PriorProvider> priorProviders;
	    
	    private void initProviders() {
	    	priorProviders = new ArrayList<>();
	    	PriorListInputEditor p = new PriorListInputEditor(doc);
	    	priorProviders.add(p.new MRCAPriorProvider());
	    	
	        // build up list of data types
	        List<String> importerClasses = AddOnManager.find(PriorProvider.class, new String[]{"beast.app"});
	        for (String _class: importerClasses) {
	        	try {
	        		if (!_class.startsWith(this.getClass().getName())) {
	        			PriorProvider priorProvider = (PriorProvider) Class.forName(_class).newInstance();
						priorProviders.add(priorProvider);
	        		}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	    
	    @Override
	    public Object visitValue(ValueContext ctx) {
			String value = ctx.getText();
			if (doc.pluginmap.containsKey(value)) {
				return doc.pluginmap.get(value);
			} else {
				return value;
			}
	    }
	    
	    @Override
	    public Object visitSubtemplate(SubtemplateContext ctx) {
			BeautiSubTemplate subTemplate = getSubTemplateName(ctx);
			
			// collect parameters
			List<String> param = new ArrayList<>();
			List<Object> value = new ArrayList<>();
			String newID = collectParameters(ctx, param, value);

			BEASTInterface bo = createSubnet(subTemplate, param, value, newID, null, null);
			return bo;
	    }
	    
		@Override
		public Object visitAdd(AddContext ctx) {
			PriorProvider provider = getProvider(ctx);
			
			// grab arguments
			List<Object> args = new ArrayList<>();
			for (int i = 2; i < ctx.children.size(); i++) {
				if (ctx.getChild(i) instanceof ArgOrUseContext) {
					args.add(visit(ctx.getChild(i)));
				}
			}

			List<Distribution> newObjects = provider.createDistribution(doc, args);

			if (newObjects == null) {
				Log.info("No objects created");
			} else {
				BEASTInterface p = doc.pluginmap.get("prior");
				if (p != null && p instanceof CompoundDistribution) {
					CompoundDistribution prior = (CompoundDistribution) p;
					for (Distribution distr : newObjects) {
						Log.info("Created " + distr.getID());
						prior.pDistributions.set(distr);
					}
				}
			}

			return super.visitAdd(ctx);
		}
		
		private PriorProvider getProvider(AddContext ctx) {
	    	if (priorProviders == null) {
	    		initProviders();
	    	}
	    	List<PriorProvider> matches = new ArrayList<>();
			String priorProviderName = ".*" + ctx.getChild(1).getText() + ".*";
	    	for (PriorProvider provider : priorProviders) {
	    		if (provider.getClass().getName().matches(priorProviderName)) {
	    			matches.add(provider);
	    		}
	    	}
			
	    	if (matches.size() > 1) {
	    		priorProviderName = ctx.getChild(1).getText();
	    		matches.clear();
		    	for (PriorProvider provider : priorProviders) {
		    		if (provider.getClass().getName().equals(priorProviderName)) {
		    			matches.add(provider);
		    		}
		    	}
	    	}
	    	
	    	if (matches.size() != 1) {
	    		String providers = "";
	    		for (int i = 0; i < priorProviders.size(); i++) {
	    			providers += priorProviders.get(i).getClass().getName();
	    			if (i < priorProviders.size() - 1) {
	    				providers += ", ";
	    			}
	    		}
	    		throw new IllegalArgumentException("Could not find provider. Use one of " + providers + ".");
	    	}
	    	return matches.get(0);
		}

		@Override
		public Object visitRm(RmContext ctx) {
			// set up inputSet
			mapInputToObject = InputFilter.initInputMap(doc);
			super.visitRm(ctx);
			
			// remove matching inputs
			for (Input<?> in : inputSet) {
				BEASTInterface bo = mapInputToObject.get(in);
				if (in.getType() == null) {
					in.determineClass(bo);
				}
				if (in.get() instanceof Alignment) {
					Log.info("Removing partition " + ((BEASTInterface)in.get()).getID());	
					// remove partition
					DocumentEditor.removePartition(doc, (Alignment)in.get());
					return null;
				} 
			}
			
			int instances = 0;
			for (Input<?> in : inputSet) {
				BEASTInterface bo = mapInputToObject.get(in);
				if (in.get() instanceof List) {
					Log.info("Refusing to remove " + in.getName() + "[" + bo.getID() + "]");	
				} else {
					Log.info("Setting " + in.getName() + "[" + bo.getID() + "] to null (fingers crossed)");	
					in.setValue(null, bo);
					instances++;
				}
			}
			if (instances == 0) {
				Log.info("Failed to remove anything");
			}
			return null;
		}

		@Override
		public Object visitTaxonset(TaxonsetContext ctx) {
			String setID = ctx.getChild(1).getText();
			TaxonSet taxonset;
			if (doc.taxaset.containsKey(setID) && doc.taxaset.get(setID) instanceof TaxonSet) {
				taxonset = (TaxonSet) doc.taxaset.get(setID);
				taxonset.taxonsetInput.get().clear();
			} else {			
				taxonset = new TaxonSet();
				taxonset.setID(setID);
			}
			for (int i = 3; i < ctx.getChildCount(); i++) {
				String id = ctx.getChild(i).getText();
				Taxon taxon;
				if (doc.taxaset.containsKey(id)) {
					taxon = doc.taxaset.get(id);
				} else {
					taxon = new Taxon(id);
					doc.registerPlugin(taxon);	
				}
				taxonset.taxonsetInput.setValue(taxon, taxonset);
			}
			doc.registerPlugin(taxonset);
			return super.visitTaxonset(ctx);
		}
		
	}
	
}