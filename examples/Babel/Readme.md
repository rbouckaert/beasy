
# Babel scripts

This folder contains beasy scripts for setting


## Install

You need to install 
* (BEAST 2)[http://beast2.org] using the 
* (Babel)[https://github.com/rbouckaert/Babel/] package and the 
* (Beasy)[https://github.com/rbouckaert/beasy] package.

## NEXUS file format

It assumes you have a NEXUS file with ascertainment correction columns (one for each word) and an assumptions block to define word boundaries -- the first column is assumed to be the column to use ascertainment correction on), e.g., like so:

```
begin characters;
  dimensions ntax=20 nchar=877;
  format type=binary symbols="01" missing=?;
matrix
'Brahui'        01010100001001010...
'Malto'         01010100100010010...
...
;
end;

begin assumptions;
charset I = 1-2;
charset thou = 3-4;
charset we = 5-7;
charset this = 8-11;
...
end;

```

There can be additional blocks for calibration information in there as well.


```
# Define monophyletic clades
begin sets;
taxset germanic = oldnorse oldhighgerman oldprussian oldenglish;
taxset tocharian = tocharian_a tocharian_b;
taxset anatolian = hittite lycian luvian;
end;

# Define time calibrations on tips, and clades.
# Note that since anatolian does not have a calibration, 
# only a monophyletic MRCAPrior is created in BEAUti
begin assumptions;
calibrate oldnorse = normal(775,40)
calibrate avestan = normal(2500,50)
calibrate gothic = normal(1650,25)
calibrate germanic = normal(1875,67)
calibrate tocharian = offsetlognormal(1650,200,0.9)
end;
```

## How to run

Use the `applauncher` to kick of the `BeasyInterpreter`. You need to define the input file using the `-D file=<filename>` argument, for example, like so:

```
/path/to/applauncher BeasyInterpreter -D file=Drav.nex -in ctmc-est-ucln-yule.bea 
```

where `/path/to/` is the path to where the applauncher is installed on your system.

