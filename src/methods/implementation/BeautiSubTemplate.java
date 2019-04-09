package methods.implementation;

import java.util.ArrayList;
import java.util.List;

import beast.app.beauti.BeautiDoc;
import beast.core.BEASTInterface;
import beast.core.Input;
import methods.MethodsTextFactory;
import methods.Phrase;
import methods.XML2TextPane;

public class BeautiSubTemplate {
	static private String [][] templates = new String[][]{
	    new String[]{"RandomTree.t:$(n)", "random starting tree"},
	    new String[]{"ClusterTree.t:$(n)", "cluster starting tree", "clusterType"},
	    new String[]{"NewickTree.t:$(n)", "Newick starting tree"},

	    new String[]{"StrictClock.c:$(n)", "strict clock", "clockRate.c:$(n)"},
	    new String[]{"ExponentialRelaxedClock.c:$(n)", "uncorrelated exponential relaxed clock", "ucedMean.c:$(n)"},
	    new String[]{"RelaxedClock.c:$(n)", "uncorrelated log-normal relaxed clock", "ucldMean.c:$(n)", "ucldStdev.c:$(n)"},
	    new String[]{"RandomLocalClock.c:$(n)", "random local clock", "meanClockRate.c:$(n)"},
	    new String[]{"SiteModel.s:$(n)", "gamma site model", "substModel", "proportionInvariant", "gammaCategoryCount", "shape"},
	    new String[]{"beast.evolution.substitutionmodel.JukesCantor", "JC69.s:$(n)", "Jukes Cantor substitution model"},
	    new String[]{"beast.evolution.substitutionmodel.HKY", "hky.s:$(n)", "HKY substitution model", "kappa.s:$(n)"},
	    new String[]{"beast.evolution.substitutionmodel.TN93", "tn93.s:$(n)", "TN93 substitution model", "kappa1.s:$(n)", "kappa2.s:$(n)"},
	    new String[]{"beast.evolution.substitutionmodel.GTR", "gtr.s:$(n)", "GTR substitution model", "rateAC.s:$(n)", "rateAG.s:$(n)", "rateAT.s:$(n)", "rateCG.s:$(n)", "rateCT.s:$(n)", "rateGT.s:$(n)"},
	    new String[]{"Blosum62.s:$(n)", "Blossum substitution model"},
	    new String[]{"Dayhoff.s:$(n)", "Dayhoff substitution model"},
	    new String[]{"JTT.s:$(n)", "JTT substitution model"},
	    new String[]{"CPREV.s:$(n)", "CPREV substitution model"},
	    new String[]{"MTREV.s:$(n)", "MTREV substitution model"},
	    new String[]{"WAG.s:$(n)", "WAG substitution model"},
	    new String[]{"MutationDeathModel.s:$(n)", "stochastic Dollo model"},
	    new String[]{"estimatedFreqs.s:$(n)", "estimated frequencies"},
	    new String[]{"empiricalFreqs.s:$(n)", "empirical frequencies"},
	    new String[]{"equalFreqs.s:$(n)", "equal frequencies"},
	    new String[]{"YuleModel.t:$(n)", "Yule model", "birthDiffRate"},
	    new String[]{"CalibratedYuleModel.t:$(n)", "Calibrated Yule model", "birthRate"},
	    new String[]{"BirthDeath.t:$(n)", "birth death model", "type", "relativeDeathRate", "sampleProbability"},
	    new String[]{"CoalescentConstant.t:$(n)", "constant coalescent"},
	    new String[]{"CoalescentExponential.t:$(n)", "exponential coalescent"},
	    new String[]{"BayesianSkyline.t:$(n)", "Bayesian skyline"},
	    new String[]{"ExtendedBayesianSkyline.t:$(n)", "extended Bayesian skyline"},
	    new String[]{"beast.math.distributions.Uniform", "uniformly distributed", "lower", "upper"},
	    new String[]{"beast.math.distributions.Normal", "normally distributed", "mean", "sigma"},
	    new String[]{"beast.math.distributions.OneOnX", "1/X distributed"},
	    new String[]{"beast.math.distributions.LogNormalDistributionModel", "log-normally distributed", "M", "S"},
	    new String[]{"beast.math.distributions.Exponential", "exponentially distributed", "mean"},
	    new String[]{"beast.math.distributions.Gamma", "gamma distributed", "alpha", "beta"},
	    new String[]{"beast.math.distributions.Beta", "beta distributed", "alpha", "beta"},
	    new String[]{"beast.math.distributions.LaplaceDistribution", "Laplace distributed", "mu", "scale"},
	    new String[]{"beast.math.distributions.InverseGamma", "inverse Gamma distributed", "alpha", "beta"},
	    new String[]{"beast.math.distributions.Poisson", "Poisson distributed", "lambda"}
	};

	
	
	static public List<Phrase> getModelDescription(Object o, BEASTInterface parent, Input<?> input, BeautiDoc doc) {
		if (o instanceof BEASTInterface) {
			BEASTInterface bi = (BEASTInterface) o;
			String [] match = match(bi);
			if (match == null) {
				return null;
			}
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
					Input<?> input2 = bi.getInput(str);
					if (input2 != null) {
						List<Phrase> m2 = MethodsTextFactory.getModelDescription(input2.get(), bi, input2, doc);
						m.addAll(m2);
					} else {
						m.add(new Phrase(str));
					}
				}
			}			
			return m;
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
