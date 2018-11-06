package beast.app.beauti;




import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.IntStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import beast.app.beauti.BeautiConfig;
import beast.app.beauti.BeautiDoc;
import beast.app.beauti.PartitionContext;
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
import beast.util.PackageManager;

public class CompactAnalysisByAntlr extends CABaseListener {
	BeautiDoc doc = null;
	CAParser parser = null;
	IntStream input = null;

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
	    CALexer lexer = new CALexer(CharStreams.fromString(CASentence));
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

	    // Get a list of matched tokens
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	 
	    // Pass the tokens to the parser
	    parser = new CAParser(tokens);
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

        print(parseTree);
        
        visitor.visit(parseTree);
	}
	

	private void print(ParseTree parseTree) {		
//		System.out.println(parseTree.getClass().getName() + " >" + parseTree.getText() + "<");
//		for (int i = 0; i < parseTree.getChildCount(); i++) {
//			print(parseTree.getChild(i));
//		}
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
		
		private void processPattern(ParseTree ctx, int context) {
			String pattern = ctx.getText();
			pattern = pattern.substring(1, pattern.length() - 1);
			String [] patterns = pattern.split(",");
			for (int i = 0; i < patterns.length; i++) {
				patterns[i] = patterns[i].replaceAll("\\*",".*");
			}
			
			partitionContext.clear();
			for (PartitionContext p : doc.partitionNames) {
				for (String pattern2: patterns) {
					switch (context) {
					case BeautiDoc.ALIGNMENT_PARTITION:
						if (p.partition.matches(pattern2)) {
							partitionContext.add(new PartitionContext(p.partition, p.siteModel, p.clockModel, p.tree));
						}
						break;
					case BeautiDoc.SITEMODEL_PARTITION:
						if (p.siteModel.matches(pattern2)) {
							partitionContext.add(new PartitionContext(p.partition, p.siteModel, p.clockModel, p.tree));
						}
						break;
					case BeautiDoc.CLOCKMODEL_PARTITION:
						if (p.clockModel.matches(pattern2)) {
							partitionContext.add(new PartitionContext(p.partition, p.siteModel, p.clockModel, p.tree));
						}
						break;
					case BeautiDoc.TREEMODEL_PARTITION:
						if (p.tree.matches(pattern2)) {
							partitionContext.add(new PartitionContext(p.partition, p.siteModel, p.clockModel, p.tree));
						}
						break;
					}
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
				if (providerList == null || providerList.size() == 0) {
					parser.notifyErrorListeners("Could not find importer. Did you load a template yet?");
					return null;
				}
				String providers = providerList.get(0).getID();
				for (int i = 1; i < providerList.size(); i++) {
					providers += "," + providerList.get(i).getID();
				}
				parser.notifyErrorListeners("Could not match '" + providerID+"' to one of these providers: " + providers);
				return null;
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
			String elementPattern = null;
			String inputPattern = null;
			
			inputSet = null;
			// collect all possible inputs
			for (ParseTree o : ctx.children) {
				if (o instanceof IdPatternContext || o instanceof PartitionPatternContext) {
					Object o2 = visit(o);
					if (o2 instanceof Set){
						inputSet = (Set<Input<?>>) o2;
					}
				}
				if (o instanceof ElemntNameContext) {
					elementPattern = ((ElemntNameContext) o).getText().trim();
					// remove '@' sign at end
					elementPattern = elementPattern.substring(0,  elementPattern.length() - 1);
				}
				if (o instanceof InputnameContext) {
					inputPattern = ((InputnameContext) o).getText().trim();
				}
			}
			mapInputToObject = InputFilter.initInputMap(doc);
			InputFilter filter = new InputFilter();
			
			inputSet = filter.getInputSet(doc, inputSet, elementPattern, inputPattern);
			
			return null;
		}

		@Override
		public Object visitIdPattern(IdPatternContext ctx) {
			String idPattern = ctx.getText().trim();
			// remove brackets
			idPattern = idPattern.substring(1, idPattern.length() - 1) + ".*";
			InputFilter filter = new InputFilter();
			inputSet = filter.getInputSet(doc, idPattern, null, null);
			return inputSet;
		}
		
		@Override
		public Object visitPartitionPattern(PartitionPatternContext ctx) {
			String partitionPattern = ctx.getText().trim();
			partitionPattern = partitionPattern.substring(1, partitionPattern.length() - 1);
			String [] patterns = partitionPattern.split(",");
			inputSet = new LinkedHashSet<>();
			for (String pattern: patterns) {
				InputFilter filter = new InputFilter();
				inputSet.addAll(filter.getInputSet(doc, pattern, BeautiDoc.ALIGNMENT_PARTITION, null, null));
			}
			return inputSet;
		}
		
		int linkType = BeautiDoc.ALIGNMENT_PARTITION;
		
		@Override
		public Object visitLinktype(LinktypeContext ctx) {
			String linktype = ctx.getText();
			linkType = -1;
			switch (linktype) {
			case "sitemodel":
				linkType =  BeautiDoc.SITEMODEL_PARTITION;
				break;
			case "clock":
				linkType = BeautiDoc.CLOCKMODEL_PARTITION;
				break;
			case "tree":
				linkType = BeautiDoc.TREEMODEL_PARTITION;
				break;
			}
			if (linkType < 0) {
				parser.notifyErrorListeners("link type not recognised: choose one of `sitemodel`, `clock`, `tree`");
				return null;
			}
			return linkType;
		}

		@Override
		public Object visitLink(LinkContext ctx) {
			if (doc.mcmc.get() == null) {
				parser.notifyErrorListeners("Use `template` and `import` command before `link` command.");
				return null;
			}

			Integer linktype = (Integer) visit(ctx.getChild(1));
			if (ctx.getChildCount() == 2) {
				processPattern(".*");
			} else {
				processPattern(ctx.getChild(2), linktype);
			}
			if (partitionContext.size() <= 1) {
				parser.notifyErrorListeners("Link command : At least two partitions must be selected '" + ctx.getText() + "'");
				return null;
			}
			DocumentEditor.link(doc, linktype, partitionContext);
			return null;
		}
		
		@Override
		public Object visitUnlink(UnlinkContext ctx) {
			if (doc.mcmc.get() == null) {
				parser.notifyErrorListeners("Use `template` and `import` command before `unlink` command.");
				return null;
			}

			Integer linktype = (Integer) visit(ctx.getChild(1));
			if (ctx.getChildCount() == 2) {
				processPattern(".*");
			} else {
				processPattern(ctx.getChild(2), linktype);
			}
			if (partitionContext.size() <= 1) {
				parser.notifyErrorListeners("Command unlink: At least two partitions must be selected " + ctx.getText());
				return null;
			}
			DocumentEditor.unlink(doc, linktype, partitionContext);
			return null;
		}
		
		
		@Override
		public Object visitSet(SetContext ctx) {
			if (doc.mcmc.get() == null) {
				parser.notifyErrorListeners("Use `template` command before `set` command.");
				return null;
			}

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
				parser.notifyErrorListeners("Command set: cannot find suitable match for " + ctx.getText());
				return null;
			}
			return null;
		}

		@Override
		public Object visitUse(UseContext ctx) {
			if (doc.mcmc.get() == null) {
				parser.notifyErrorListeners("Use `template` command before `use` command.");
				return null;
			}
			
			// set up inputSet
			mapInputToObject = InputFilter.initInputMap(doc);
			super.visitUse(ctx);
			if (inputSet == null || inputSet.size() == 0) {
				setupInputSet(((MCMC)doc.mcmc.get()).posteriorInput.get());
			}
			
			BeautiSubTemplate subTemplate = getSubTemplateName(ctx);
			
			// collect parameters
			List<String> params = new ArrayList<>();
			List<Object> values = new ArrayList<>();
			String newID = collectParameters(ctx, params, values);
			
			// see if we can find an input to match the subtemplate to
			int instantCount = 0;
			for(Input<?> in : inputSet) {
				BEASTInterface o = mapInputToObject.get(in);
				if (in.getType() != null) {
					if (in.get() instanceof List<?>) {
						if (in.getType().isAssignableFrom(subTemplate._class)) {
							BEASTInterface bo = createSubnet(subTemplate, params, values, newID, o, in);
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
							BEASTInterface bo = createSubnet(subTemplate, params, values, newID, o, in);
							if (in.canSetValue(bo, o)) {
								Log.info("Using " + in.getName() + "[" +o.getID() +"] = " + bo.getID());
								in.setValue(bo, o);
								instantCount++;
							}
						}
					}
				}
			}
			
			if (instantCount == 0) {
				if (ctx.getParent() instanceof ValueContext) {
					BEASTInterface bo = createSubnet(subTemplate, params, values, newID, null, null);
					return bo;
				} else {
					String mainID = subTemplate.getMainID();
					if (mainID.indexOf("$(m)") > 0 && doc.alignments.size() > 0) {
						// it's a dual partition subnet
						BEASTInterface bo = createSubnet(subTemplate, params, values, newID, doc.alignments.get(0), null);
						// find 2nd partition context
						PartitionContext context = (PartitionContext) partitionContext.toArray()[0];
						String newName = context.partition;
						for(BEASTInterface o : doc.pluginmap.values()) {
							if (o.getID() != null && o.getID().indexOf("$(m)") > 0) {
								String newID2 = o.getID().replaceAll("$(m)", newName);
								o.setID(newID2);
							}
						}
					}
				}
			}
			
			if (instantCount == 0) {
				parser.notifyErrorListeners("Command use: cannot find suitable input to match for " + ctx.getText());
				return null;
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
			
			BEASTInterface bo = subTemplate.createSubNet(pc, false);
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
	            			parser.notifyErrorListeners("Could not determine partition context based on site model");
	            			return null;
	            		}
	            	break;
	            	case 't': 
	            		if (!p.tree.equals(base.tree)) {
	            			parser.notifyErrorListeners("Could not determine partition context based on tree model");
	            			return null;
	            		}
	            	break;
	            	case 'c': 
	            		if (!p.clockModel.equals(base.clockModel)) {
	            			parser.notifyErrorListeners("Could not determine partition context based on clock model");
	            			return null;
	            		}
	            	break;
	            	default : 
	            		if (!p.partition.equals(base.partition)) {
	            			parser.notifyErrorListeners("Could not determine partition context based on partition");
	            			return null;
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

            
            parser.notifyErrorListeners("Could not determine partition context for '"+o.getID()+"': a more specific ID would help");
            return null;
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
	        parser.notifyErrorListeners("Command use: cannot find matching template for " + subTemplateName);
	        return null;
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
				o.determindClassOfInputs();
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
			if (doc.mcmc.get() == null) {
				parser.notifyErrorListeners("Use `template` and `import` command before `rename` command.");
				return null;
			}

			Object o = visit(ctx.getChild(1));
			int partitionID = (Integer) o;

			String oldName = null, newName;
			if (ctx.getChildCount() > 4) {
				oldName = ctx.getChild(2).getText();
				newName = ctx.getChild(4).getText();
			} else {
				switch (partitionID) {
				case BeautiDoc.SITEMODEL_PARTITION : 
					oldName = ((PartitionContext)doc.possibleContexts.toArray()[0]).siteModel;
					break;
				case BeautiDoc.TREEMODEL_PARTITION : 
					oldName = ((PartitionContext)doc.possibleContexts.toArray()[0]).tree;
					break;
				case BeautiDoc.CLOCKMODEL_PARTITION : 
					oldName = ((PartitionContext)doc.possibleContexts.toArray()[0]).clockModel;
					break;
				default:
					oldName = ((PartitionContext)doc.possibleContexts.toArray()[0]).partition;
				}
				newName = ctx.getChild(3).getText();
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
	        List<String> importerClasses = PackageManager.find(PriorProvider.class, new String[]{"beast"});
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
			if (doc.mcmc.get() == null) {
				parser.notifyErrorListeners("Use `template` command before `add` command.");
				return null;
			}

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

			return newObjects; // super.visitAdd(ctx);
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
	    		parser.notifyErrorListeners("Could not find provider. Use one of " + providers + ".");
	    		return null;
	    	}
	    	return matches.get(0);
		}

		@Override
		public Object visitRm(RmContext ctx) {
			if (doc.mcmc.get() == null) {
				parser.notifyErrorListeners("Use `template` and `import` command before `rm` command.");
				return null;
			}

			// set up inputSet
			mapInputToObject = InputFilter.initInputMap(doc);
			partitionContext = new HashSet<>();
			super.visitRm(ctx);
			
//			if (partitionContext.size() > 0) {
//				for (PartitionContext p : partitionContext) {
//					if (doc.pluginmap.get(p.partition) != null) {
//						BEASTInterface o = doc.pluginmap.get(p.partition);
//						if (o instanceof Alignment) {
//							Log.info("Removing partition " + o.getID());	
//							// remove partition
//							DocumentEditor.removePartition(doc, (Alignment) o);
//						} else {
//							Log.info("Cannot remove partition " + p.partition + " since it is not an alignment");
//						}
//					} else {
//						Log.info("Cannot find partition " + p.partition);
//					}
//				}
//				return null;
//			}
			
			// remove matching inputs
			boolean gotOne = false;
			for (Input<?> in : inputSet) {
				BEASTInterface bo = mapInputToObject.get(in);
				if (in.getType() == null) {
					in.determineClass(bo);
				}
				if (in.get() instanceof Alignment) {
					Log.info("Removing partition " + ((BEASTInterface)in.get()).getID());	
					// remove partition
					DocumentEditor.removePartition(doc, (Alignment)in.get());
					gotOne = true;
				} 
			}
			if (gotOne) {
				return null;
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
			if (doc.mcmc.get() == null) {
				parser.notifyErrorListeners("Use `template` command before `taxonset` command.");
				return null;
			}

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