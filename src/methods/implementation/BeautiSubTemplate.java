package methods.implementation;

import java.util.ArrayList;
import java.util.List;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import methods.MethodsText;
import methods.MethodsTextFactory;
import methods.Phrase;

public class BeautiSubTemplate {
	static private String [][] templates = new String[][]{
	    new String[]{"RandomTree.t:$(n)", "random starting tree"},
	    new String[]{"ClusterTree.t:$(n)", "cluster starting tree", "clusterType"},
	    new String[]{"NewickTree.t:$(n)", "Newick starting tree"},

	    new String[]{"StrictClock.c:$(n)", "strict clock", "with ", "clockRate.c:$(n)"},
	    new String[]{"ExponentialRelaxedClock.c:$(n)", "uncorrelated exponential relaxed clock", "with ", "ucedMean.c:$(n)"},
	    new String[]{"RelaxedClock.c:$(n)", "uncorrelated log-normal relaxed clock", "with ", "ucldMean.c:$(n)", " and ", "ucldStdev.c:$(n)"},
	    new String[]{"RandomLocalClock.c:$(n)", "random local clock", "with ", "meanClockRate.c:$(n)"},
//	    new String[]{"SiteModel.s:$(n)", "gamma site model", "with ", "substModel", "proportionInvariant", "gammaCategoryCount", "shape"},

	    new String[]{"BEASTModelTest.s:$(n)", "bModelTest", "site model with ", "gammaCategoryCount", " gamma categories ", "substModel"},
	    new String[]{"RevJump.s:$(n)", "using ", "modelSet", " models"},

	    new String[]{"beast.evolution.substitutionmodel.JukesCantor", "JC69.s:$(n)", "Jukes Cantor"},
	    new String[]{"beast.evolution.substitutionmodel.HKY", "hky.s:$(n)", "with ", "kappa.s:$(n)", "and ", "frequencies"},
	    new String[]{"beast.evolution.substitutionmodel.TN93", "tn93.s:$(n)", "with ", "kappa1.s:$(n)", "and ", "kappa2.s:$(n)"},
	    new String[]{"beast.evolution.substitutionmodel.GTR", "gtr.s:$(n)", "with ", "rateAC.s:$(n)", ", ", "rateAG.s:$(n)", ", ", "rateAT.s:$(n)", ", ", "rateCG.s:$(n)", ", ", "rateCT.s:$(n)", "and ", "rateGT.s:$(n)"},
	    new String[]{"Blosum62.s:$(n)"},
	    new String[]{"Dayhoff.s:$(n)"},
	    new String[]{"JTT.s:$(n)"},
	    new String[]{"CPREV.s:$(n)"},
	    new String[]{"MTREV.s:$(n)"},
	    new String[]{"WAG.s:$(n)"},
	    new String[]{"MutationDeathModel.s:$(n)", "stochastic Dollo", "model"},
	    new String[]{"estimatedFreqs.s:$(n)", "estimated", " frequencies"},
	    new String[]{"empiricalFreqs.s:$(n)", "empirical", " frequencies"},
	    new String[]{"equalFreqs.s:$(n)", "equal", " frequencies"},
	    new String[]{"YuleModel.t:$(n)", "Yule model", "birthDiffRate"},
	    new String[]{"CalibratedYuleModel.t:$(n)", "Calibrated Yule model", "with ", "birthRate"},
	    new String[]{"BirthDeath.t:$(n)", "birth death model", "with ", "type", ", ", "relativeDeathRate", "and ", "sampleProbability"},
	    new String[]{"CoalescentConstant.t:$(n)", "constant coalescent", "tree prior with ", "popSize.t:$(n)"},
	    new String[]{"CoalescentExponential.t:$(n)", "exponential coalescent", "tree prior with ", "ePopSize.t:$(n)", "and ", "growthRate.t:$(n)"},
	    new String[]{"BayesianSkyline.t:$(n)", "Bayesian skyline", "tree prior with ", "bPopSizes.t:$(n)"},
	    new String[]{"ExtendedBayesianSkyline.t:$(n)", "extended Bayesian skyline", "tree prior with ", "popSizes.alltrees", "and ", "populationMean.alltrees"},

	    new String[]{"beast.math.distributions.Uniform", "uniformly", "distributed ", "(lower =", "lower", "and upper =", "upper", ")"},
	    new String[]{"beast.math.distributions.Normal", "normally", "distributed ", "(mean =", "mean", "and sigma =",  "sigma", ")"},
	    new String[]{"beast.math.distributions.OneOnX", "1/X", "distributed"},
	    new String[]{"beast.math.distributions.LogNormalDistributionModel", "log-normally", "distributed ", "(mean log=", "M", "and stdev log=", "S", ")"},
	    new String[]{"beast.math.distributions.Exponential", "exponentially", "distributed ", "(mean =", "mean", ")"},
	    new String[]{"beast.math.distributions.Gamma", "gamma", "distributed ", "(alpha =", "alpha", "and beta =", "beta", ")"},
	    new String[]{"beast.math.distributions.Beta", "beta", "distributed ", "(alpha =", "alpha", "and beta =", "beta", ")"},
	    new String[]{"beast.math.distributions.LaplaceDistribution", "Laplace", "distributed ", "(mu =", "mu", "and scale =", "scale", ")"},
	    new String[]{"beast.math.distributions.InverseGamma", "inverse Gamma", "distributed ", "(alpha =", "alpha", "and beta =", "beta", ")"},
	    new String[]{"beast.math.distributions.Poisson", "Poisson", "distributed ", "(lambda =", "lambda", ")"}
	};

	
	
	static public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input, BeautiDoc doc) {
		if (o instanceof BEASTInterface) {
			BEASTInterface bi = (BEASTInterface) o;
			String [] match = match(bi);
			if (match == null) {
				return null;
			}
			if (MethodsText.done.contains(bi)) {
				return new ArrayList<>();
			}
			MethodsText.done.add(bi);

			List<Phrase> m = new ArrayList<>();
			m.add(new Phrase(o,parent,input, match[1]));
			for (int i = 2; i < match.length; i++) {
				String str = match[i];
				if (str.contains("$(n)")) {
					beast.app.beauti.PartitionContext partition = doc.getContextFor(bi);
					str = BeautiDoc.translatePartitionNames(str, partition);
					BEASTInterface o2 = doc.pluginmap.get(str);
					List<Phrase> m2 = MethodsTextFactory.getModelDescription(o2, null, null, doc);
					m.addAll(m2);
				} else {
					Input<?> input2 = hasInput(bi, str);
					if (input2 != null) {
						if (input2.get() != null) {
							List<Phrase> m2 = MethodsTextFactory.getModelDescription(input2.get(), bi, input2, doc);
							m.addAll(m2);
						}
					} else {
						m.add(new Phrase(str));
					}
				}
			}			
			return m;
		}
		return null; 
	}

	private static Input<?> hasInput(BEASTInterface bi, String str) {
		for (Input<?> input : bi.listInputs()) {
			if (input.getName().equals(str)) {
				return input;
			}
		}
		return null;
	}

	static private String[] match(BEASTInterface bi) {
		String id = bi.getID();
		if (id.lastIndexOf('.') > 0) {
			id = id.substring(0, id.lastIndexOf('.'));
		}
		String className = bi.getClass().getName();
		for (String [] str : templates) {
			String str0 = str[0];
			str0 = str0.substring(0, str0.lastIndexOf('.'));
			if (str0.equals(id)) {
				return str;
			}
			if (str[0].equals(className)) {
				return str;
			}
		}
		return null;
	}
	
	

}
