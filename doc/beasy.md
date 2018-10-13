#Beasy: setting up BEAST XML without tears

<center>
Remco Bouckaert 

[remco@cs.auckland.ac.nz](mailto:remco@cs.auckland.ac.nz)
</center>

# Introduction

Beasy is a small scripting language for building BEAST XML files. This difference between Beasy and [other scripts](http://www.beast2.org/2018/03/09/10-ways-to-generate-beast-xml.html) is that Beasy relies on BEAUti templates. Many BEAST packages come with BEAUti templates, which allow these packages to be used from within BEAUti. With Beasy, these templates can be used as well. This means that when new packages become available, they will be immediately available, where other scripts requires updates. So, Beasy will **always be up to date** with the latest packages.

Beasy is **compact**. For example to specify a standard analysis with HKY substitution model with a relaxed clock and coalescent with exponential growth, the following will be sufficient:

```logo
template Standard;
import ../beast2/examples/nexus/dna.nex;
use substModel{dna} = HKY;
use branchRateModel{dna} = RelaxedClockLogNormal;
use [prior] = CoalescentExponentialPopulation;
```
This is the canonical form, where every input-identifier is explicitly formulated. When there is no ambiguity, for instance, because there is only a single place in the model where a substitution model or clock model would fit, the input-identifier can be omitted, giving the abbreviated form below:

```json
mode compact;
template Standard;
import ../beast2/examples/nexus/dna.nex;
use HKY;
use RelaxedClockLogNormal;
use [prior] = CoalescentExponentialPopulation;

```

Note that the input identifier for the prior cannot be omitted, since there are multiple CompoundDistributions where the coalescent distribution would fit (namely, in the posterior and in the likelihood).
 
Beasy allows you to do pretty much **everything you can do in BEAUti**, like selecting sub-templates, setting values, adding priors, etc. But, you can run it from the command line, and it is easier to apply to multiple alignments.


# Getting started

To use Beasy, first you need to install the Beasy package in the [package manager](http://www.beast2.org/managing-packages/). There are two ways to use Beasy: as a script interpreter using the AppLauncher, or interactively using the Read-Eval-Print-Loop (REPL).


