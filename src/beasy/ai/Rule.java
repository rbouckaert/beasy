package beasy.ai;

import beast.core.Description;

@Description("Knowledg base rule for BEAST-AI")
public class Rule {
	String label;
	String condition;
	Rule [] ifTrue;
	Rule ifFalse;
	int go_to;
	int indent;
	
	public Rule(String label, String condition, int go_to) {
		this.label = label;
		this.condition = condition;
		this.go_to = go_to;
	}
}
