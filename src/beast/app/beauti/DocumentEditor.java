package beast.app.beauti;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import beast.core.BEASTInterface;
import beast.core.Distribution;
import beast.core.Input;
import beast.core.MCMC;
import beast.core.util.CompoundDistribution;
import beast.evolution.alignment.Alignment;
import beast.evolution.branchratemodel.BranchRateModel;
import beast.evolution.likelihood.GenericTreeLikelihood;
import beast.evolution.sitemodel.SiteModelInterface;
import beast.evolution.tree.TreeInterface;

/** useful methods for manipulating a BEAST model contained in a BeautiDoc **/
public class DocumentEditor {

	@SuppressWarnings({"rawtypes", "unchecked"})
	public static void link(BeautiDoc doc, String partitionID, Set<PartitionContext> partitionContext) {
		PartitionContext [] contexts = partitionContext.toArray(new PartitionContext[]{});
		GenericTreeLikelihood [] treelikelihood = new GenericTreeLikelihood[contexts.length];
		CompoundDistribution likelihoods = (CompoundDistribution) doc.pluginmap.get("likelihood");

		for (int i = 0; i < partitionContext.size(); i++) {
			String partition = contexts[i].partition;
			for (int j = 0; j < likelihoods.pDistributions.get().size(); j++) {
				GenericTreeLikelihood likelihood = (GenericTreeLikelihood) likelihoods.pDistributions.get().get(i);
				assert (likelihood != null);
				if (likelihood.dataInput.get().getID().equals(partition)) {
					treelikelihood[i] = likelihood;
				}
			}
		}

		switch (partitionID) {
		case "sitemodel" :
			SiteModelInterface sitemodel = treelikelihood[0].siteModelInput.get();
			for (int i = 1; i < contexts.length; i++) {
				PartitionContext oldContext = new PartitionContext(treelikelihood[i]);

				SiteModelInterface oldSiteModel = treelikelihood[i].siteModelInput.get();
				for (Object beastObject : BEASTInterface.getOutputs(oldSiteModel).toArray()) { //.toArray(new BEASTInterface[0])) {
					for (Input<?> input : ((BEASTInterface)beastObject).listInputs()) {
						try {
						if (input.get() == oldSiteModel) {
							if (input.getRule() != Input.Validate.REQUIRED) {
								input.setValue(sitemodel /*null*/, (BEASTInterface) beastObject);
							//} else {
								//input.setValue(tree, (BEASTInterface) beastObject);
							}
						} else if (input.get() instanceof List) {
							List list = (List) input.get();
							if (list.contains(oldSiteModel)) { // && input.getRule() != Validate.REQUIRED) {
								list.remove(oldSiteModel);
								if (!list.contains(sitemodel)) {
									list.add(sitemodel);
								}
							}
						}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				treelikelihood[i].siteModelInput.setValue(sitemodel, treelikelihood[i]);
				contexts[i].siteModel = contexts[0].siteModel;
				repartition(doc, oldContext);
			}
			break;
		case "clock" :
			BranchRateModel clockmodel = treelikelihood[0].branchRateModelInput.get();
			for (int i = 1; i < contexts.length; i++) {
				PartitionContext oldContext = new PartitionContext(treelikelihood[i]);

				BranchRateModel oldClock = treelikelihood[i].branchRateModelInput.get();
				for (Object beastObject : BEASTInterface.getOutputs(oldClock).toArray()) { //.toArray(new BEASTInterface[0])) {
					for (Input<?> input : ((BEASTInterface)beastObject).listInputs()) {
						try {
						if (input.get() == oldClock) {
							if (input.getRule() != Input.Validate.REQUIRED) {
								input.setValue(clockmodel /*null*/, (BEASTInterface) beastObject);
							//} else {
								//input.setValue(tree, (BEASTInterface) beastObject);
							}
						} else if (input.get() instanceof List) {
							List list = (List) input.get();
							if (list.contains(oldClock)) { // && input.getRule() != Validate.REQUIRED) {
								list.remove(oldClock);
								if (!list.contains(clockmodel)) {
									list.add(clockmodel);
								}
							}
						}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				treelikelihood[i].branchRateModelInput.setValue(clockmodel, treelikelihood[i]);
				contexts[i].clockModel = contexts[0].clockModel;
				repartition(doc, oldContext);
			}
			break;
		case "tree" :
			TreeInterface tree = treelikelihood[0].treeInput.get();
			for (int i = 1; i < contexts.length; i++) {
				PartitionContext oldContext = new PartitionContext(treelikelihood[i]);

				TreeInterface oldTree = treelikelihood[i].treeInput.get();
				((BEASTInterface)oldTree).setInputValue("estimate", false);
				treelikelihood[i].treeInput.setValue(tree, treelikelihood[i]);
				contexts[i].tree = contexts[0].tree;
				
            	// use toArray to prevent ConcurrentModificationException
				for (Object beastObject : BEASTInterface.getOutputs(oldTree).toArray()) { //.toArray(new BEASTInterface[0])) {
					for (Input<?> input : ((BEASTInterface)beastObject).listInputs()) {
						try {
						if (input.get() == oldTree) {
							if (input.getRule() != Input.Validate.REQUIRED) {
								input.setValue(tree/*null*/, (BEASTInterface) beastObject);
							//} else {
								//input.setValue(tree, (BEASTInterface) beastObject);
							}
						} else if (input.get() instanceof List) {
							List<TreeInterface> list = (List<TreeInterface>) input.get();
							if (list.contains(oldTree)) { // && input.getRule() != Validate.REQUIRED) {
								list.remove(oldTree);
								if (!list.contains(tree)) {
									list.add(tree);
								}
							}
						}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				repartition(doc, oldContext);

			}
			break;
		default:
			throw new IllegalArgumentException("Command link: expected 'link [sitemodel|clock|tree] but got " + partitionID);
		}
		doc.determinePartitions();
		doc.scrubAll(true, false);
	}

	public static void unlink(BeautiDoc doc, String partitionID, Set<PartitionContext> partitionContext) {
		PartitionContext [] contexts = partitionContext.toArray(new PartitionContext[]{});
		GenericTreeLikelihood [] treelikelihood = new GenericTreeLikelihood[contexts.length];
		CompoundDistribution likelihoods = (CompoundDistribution) doc.pluginmap.get("likelihood");

		for (int i = 0; i < partitionContext.size(); i++) {
			String partition = contexts[i].partition;
			for (int j = 0; j < likelihoods.pDistributions.get().size(); j++) {
				GenericTreeLikelihood likelihood = (GenericTreeLikelihood) likelihoods.pDistributions.get().get(i);
				assert (likelihood != null);
				if (likelihood.dataInput.get().getID().equals(partition)) {
					treelikelihood[i] = likelihood;
				}
			}
		}

		switch (partitionID) {
		case "sitemodel" :
			SiteModelInterface sitemodel = treelikelihood[0].siteModelInput.get();
			for (int i = 1; i < contexts.length; i++) {
				PartitionContext oldContext = new PartitionContext(treelikelihood[i]);
				contexts[i].siteModel = contexts[i].partition;

				SiteModelInterface newSitemodel = (SiteModelInterface) BeautiDoc.deepCopyPlugin((BEASTInterface) sitemodel, treelikelihood[i], (MCMC) doc.mcmc.get(), oldContext, contexts[i], doc, new ArrayList<>());
				treelikelihood[i].siteModelInput.setValue(newSitemodel, treelikelihood[i]);
				repartition(doc, contexts[i]);
			}
			break;
		case "clock" :
			BranchRateModel clockModel = treelikelihood[0].branchRateModelInput.get();
			for (int i = 1; i < contexts.length; i++) {
				PartitionContext oldContext = new PartitionContext(treelikelihood[i]);
				contexts[i].clockModel = contexts[i].partition;

				BranchRateModel newClockmodel = (BranchRateModel) BeautiDoc.deepCopyPlugin((BEASTInterface) clockModel, treelikelihood[i], (MCMC) doc.mcmc.get(), oldContext, contexts[i], doc, new ArrayList<>());
				treelikelihood[i].siteModelInput.setValue(newClockmodel, treelikelihood[i]);
				repartition(doc, contexts[i]);
			}
			break;
		case "tree" :
			TreeInterface tree = treelikelihood[0].treeInput.get();
			for (int i = 1; i < contexts.length; i++) {
				PartitionContext oldContext = new PartitionContext(treelikelihood[i]);
				contexts[i].tree = contexts[i].partition;

				TreeInterface newTree = (TreeInterface) BeautiDoc.deepCopyPlugin((BEASTInterface) tree, treelikelihood[i], (MCMC) doc.mcmc.get(), oldContext, contexts[i], doc, new ArrayList<>());
				treelikelihood[i].treeInput.setValue(newTree, treelikelihood[i]);
				repartition(doc, contexts[i]);
			}
			break;
		default:
			throw new IllegalArgumentException("Command unlink: expected 'unlink [sitemodel|clock|tree] but got " + partitionID);
		}
		doc.determinePartitions();
		doc.scrubAll(true, false);
	}

	private static void repartition(BeautiDoc doc, PartitionContext oldContext) {
		List<BeautiSubTemplate> templates = new ArrayList<>();
		templates.add(doc.beautiConfig.partitionTemplate.get());
		templates.addAll(doc.beautiConfig.subTemplates);
		// keep applying rules till model does not change
		doc.setUpActivePlugins();
		int n;
		do {
			n = doc.posteriorPredecessors.size();
			doc.applyBeautiRules(templates, false, oldContext);
			doc.setUpActivePlugins();
		} while (n != doc.posteriorPredecessors.size());
		doc.determinePartitions();		
	}


	public static void removePartition(BeautiDoc doc, Alignment data) {
		Object o = doc.pluginmap.get("likelihood");
		if (o != null && o instanceof CompoundDistribution) {
			CompoundDistribution likelihood = (CompoundDistribution) o;
			List<Distribution> distrs = likelihood.pDistributions.get();
			BEASTInterface bo = null;
			for (Distribution d : distrs) {
				if (d instanceof GenericTreeLikelihood && ((GenericTreeLikelihood)d).dataInput.get() == data) {
					bo = d;
				}
			}
			distrs.remove(bo);
		}
		MRCAPriorInputEditor.customConnector(doc);
		doc.determinePartitions();
		doc.scrubAll(true, false);
	} // removePartition
}
