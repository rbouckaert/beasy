package beasy.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beast.core.Description;

@Description("Parser for knowledge base files used by Beast-AI")
public class KBParser {

	
	public Rule [] parse(String fileName) throws IOException {
		return parse(new File(fileName));
	}
	
	public Rule [] parse(File file) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader(file));
        StringBuffer buf = new StringBuffer();
        String str = null;
        int k = 0;
        while (fin.ready()) {
            str = fin.readLine();
            buf.append(str);
            buf.append('\n');
            k++;
        }
        fin.close();
        
        Rule [] rules = new Rule[k];
        String [] strs = buf.toString().split("\n");
        
        // find labels and map them to rule numbers in rules array
        Map<String,Integer> ruleMap = new HashMap<>();
        int [] indent = new int[k];
        for (int i = 0; i < k; i++) {
        	String str2 = strs[i];
    		if (str2.indexOf("{#") > 0) {
    			String label = str2.substring(str2.indexOf("{#") + 1);
    			label = label.replaceAll("}", "").trim();
    			ruleMap.put(label, i);
    		}
    		int j = 0;
    		while (j < str2.length() && Character.isWhitespace(str2.charAt(j))) {
    			j++;
    		}
    		indent[i] = j;
        }
        
        // parse single lines
        for (int i = 0; i < k; i++) {
        	rules[i] = parseRule(strs[i], rules, ruleMap);
        	rules[i].indent = indent[i];
        }

        // parse rule relations
        for (int i = 0; i < k; i++) {
        	parseRule(i, indent, rules);
        }
        return rules;
	}

	private void parseRule(int i, int[] indent, Rule[] rules) {
		int n = indent.length-1;
		if (i >= n) {
			return;
		}
		Rule rule = rules[i];
		if (indent[i] < indent[i+1]) {
			List<Rule> options = new ArrayList<>();
			options.add(rules[i+1]);
			int j = i+2;
			while (j < n && indent[j] > indent[i]) {
				if (indent[j] == indent[i] + 1) {
					options.add(rules[j]);
				}
				j++;
			}
			rule.ifTrue = options.toArray(new Rule[] {});
			if (indent[j] == indent[i]) {
				rule.ifFalse = rules[j];
			}
		}
	}

	
	private Rule parseRule(String string, Rule[] rules, Map<String, Integer> ruleMap) {
		String label = null;
		String condition; 
		int go_to = -1; 
		if (string.indexOf("{#") > 0) {
			label = string.substring(string.indexOf("{#") + 2);
			if (label.contains("}")) {
				label = label.substring(0, label.indexOf("}"));
			}
			condition = string.substring(0, string.indexOf("{#"));
		} else {
			condition = string;
		}
		if (condition.contains("goto")) {
			String target = condition.substring(condition.indexOf("goto") + 5);
			target = "#" + target.trim().replaceAll("[\\(\\+@\\)]","").trim();
			if (!ruleMap.containsKey(target)) {
				throw new IllegalArgumentException("Could not find target " + target);
			}
			go_to = ruleMap.get(target)+1;
			condition = condition.substring(0, condition.indexOf("goto"));
		}
		Rule rule = new Rule(label, condition, go_to);
		
		return rule;
	}
}
