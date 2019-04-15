#export H=/Users/remco/workspace
#export CP=`perl t.pl`:$H/beasy/build
export CP=`perl -e '$s = system("grep","BEAST","/Users/remco/.beast/beauti.properties");$s =~ s/\\\\\\\\//g;$s =~ s/.*=//;print $s;'`:/Users/remco/beasy/build

java -cp "$CP" methods.XML2TextPane SkylinePlots/hcv_bdsky.xml SkylinePlots/hcv_bdsky.txt

java -cp "$CP" methods.XML2TextPane Bacter/bacter_tutorial.xml Bacter/bacter_tutorial.txt
java -cp "$CP" methods.XML2TextPane AIM/anoph.xml AIM/anoph.txt
java -cp "$CP" methods.XML2TextPane Mascot/H3N2.xml Mascot/H3N2.txt

java -cp "$CP" methods.XML2TextPane Starbeast2/CanisPhylogeny-example.xml Starbeast2/CanisPhylogeny-example.txt
java -cp "$CP" methods.XML2TextPane Starbeast2/CanisFBD-example.xml Starbeast2/CanisFBD-example.txt
java -cp "$CP" methods.XML2TextPane SpeciesTrees/pocket-gophers-rlc.xml SpeciesTrees/pocket-gophers-rlc.txt
java -cp "$CP" methods.XML2TextPane Starbeast2/CanisUCLN-example.xml Starbeast2/CanisUCLN-example.txt

java -cp "$CP" methods.XML2TextPane CalibratedSpeciesTrees/pocket-gophers-calibrated.xml CalibratedSpeciesTrees/pocket-gophers-calibrated.txt

java -cp "$CP" methods.XML2TextPane DivergenceTimeEstimation/bearsDivtime_FBD.1.xml DivergenceTimeEstimation/bearsDivtime_FBD.1.txt
java -cp "$CP" methods.XML2TextPane DivergenceTimeEstimation/bearsDivtime_FBD.2.xml DivergenceTimeEstimation/bearsDivtime_FBD.2.txt
java -cp "$CP" methods.XML2TextPane CladeAge/Near_et_al_red.xml CladeAge/Near_et_al_red.txt
java -cp "$CP" methods.XML2TextPane MEP/RSV2.xml MEP/RSV2.txt
java -cp "$CP" methods.XML2TextPane MEP/RSV2-bsp.xml MEP/RSV2-bsp.txt
java -cp "$CP" methods.XML2TextPane Introduction/Primates.xml Introduction/Primates.txt
java -cp "$CP" methods.XML2TextPane Introduction/Primates_long.xml Introduction/Primates_long.txt
java -cp "$CP" methods.XML2TextPane ModelAdequacy/cp3.xml ModelAdequacy/cp3.txt
java -cp "$CP" methods.XML2TextPane ModelSelection/HBVUCLN.xml ModelSelection/HBVUCLN.txt
java -cp "$CP" methods.XML2TextPane ModelSelection/HBVStrict.xml ModelSelection/HBVStrict.txt
java -cp "$CP" methods.XML2TextPane SkylinePlots/hcv_coal.xml SkylinePlots/hcv_coal.txt
java -cp "$CP" methods.XML2TextPane Troubleshooting/tutorial_run1.xml Troubleshooting/tutorial_run1.txt
java -cp "$CP" methods.XML2TextPane Troubleshooting/tutorial_run2.xml Troubleshooting/tutorial_run2.txt
java -cp "$CP" methods.XML2TextPane Troubleshooting/tutorial_run3.xml Troubleshooting/tutorial_run3.txt
java -cp "$CP" methods.XML2TextPane Troubleshooting/tutorial_run4.xml Troubleshooting/tutorial_run4.txt
java -cp "$CP" methods.XML2TextPane Starbeast/gopher.xml Starbeast/gopher.txt
java -cp "$CP" methods.XML2TextPane ModelSelection/HBVStrict-NS.xml ModelSelection/HBVStrict-NS.txt
java -cp "$CP" methods.XML2TextPane ModelSelection/HBVUCLN-NS.xml ModelSelection/HBVUCLN-NS.txt
java -cp "$CP" methods.XML2TextPane MolecularDating/h1n1_ucld.xml MolecularDating/h1n1_ucld.txt
java -cp "$CP" methods.XML2TextPane PriorSelection/Heterochronous.xml PriorSelection/Heterochronous.txt
java -cp "$CP" methods.XML2TextPane PriorSelection/Homochronous.xml PriorSelection/Homochronous.txt
java -cp "$CP" methods.XML2TextPane PriorSelection/Homochronous_tMRCA.xml PriorSelection/Homochronous_tMRCA.txt
java -cp "$CP" methods.XML2TextPane CoupledMCMC/hcv_coupled.xml CoupledMCMC/hcv_coupled.txt
java -cp "$CP" methods.XML2TextPane CoupledMCMC/hcv_mcmc.xml CoupledMCMC/hcv_mcmc.txt
java -cp "$CP" methods.XML2TextPane SubstModelAveraging/primate-mtDNA-bMT.xml SubstModelAveraging/primate-mtDNA-bMT.txt

# not generated by BEAUti:
#java -cp "$CP" methods.XML2TextPane SCOTTI/FMDV.xml SCOTTI/FMDV.txt
#
# BEAST 2.4 XML:
#java -cp "$CP" methods.XML2TextPane StructuredCoalescent/MTT.xml StructuredCoalescent/MTT.txt

for s in */*.txt; do echo $s;diff ../ttb-xml/$s ../test/$s; done
