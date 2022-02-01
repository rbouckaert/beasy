package beasy.ai;

import java.io.File;
import java.io.PrintStream;

import beast.app.util.Application;
import beast.app.util.OutFile;
import beast.core.Description;
import beast.core.Input;
import beast.core.Runnable;
import beast.core.util.Log;

@Description("Visualise knowledge base as SVG tree")
public class KB2SVG extends Runnable {
	public Input<File> treesInput = new Input<>("input","file containing a knowledge base", new File("[[none]]"));
	final public Input<OutFile> outputInput = new Input<>("out", "output file, or stdout if not specified", new OutFile("[[none]]"));


	@Override
	public void initAndValidate() {
	}
	
	String svgHeader = "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"\" width=\"1000\">\n" + 
"<style type=\"text/css\">\n" + 
".label {fill:#000;stroke:none;font-family:arial;font-size:10pt;font-style: normal;}\n" + 
".target {fill:#a00;stroke:none;font-family:arial;font-size:10pt;font-style: normal;}\n" + 
".labeld {fill:#aaa;stroke:none;font-family:arial;font-size:10pt;font-style: normal;}\n" + 
".smalllabel {fill:#0000f0;font-size:8pt;font-family:arial;text-anchor:middle;alignment-baseline:centre;}\n" + 
".arrow {stroke:blue;stroke-width:2;fill:none;}\n" + 
"</style>\n" + 
"  <defs>\n" + 
"    <marker id=\"arrow\" markerWidth=\"10\" markerHeight=\"10\" refX=\"0\" refY=\"3\" orient=\"auto\" markerUnits=\"strokeWidth\">\n" + 
"    	<g transform=\"matrix(0.5,0,0,0.5,0,1.5)\">\n" + 
"	      <path d=\"M0,0 L0,6 L9,3 z\" fill=\"#00f\"/>\n" + 
"    	</g>\n" + 
"    </marker>\n" + 
"  </defs>";

	@Override
	public void run() throws Exception {
		KBParser kb = new KBParser();
		Rule [] rules = kb.parse(treesInput.get());
		int n = rules.length;
		int offsety = 15;
		int dy = 15;
		int dx = 50;

		
		PrintStream out = System.out;
		if (outputInput.get() != null && !outputInput.get().getName().equals("[[none]]")) {
			Log.warning("Writing to file " + outputInput.get().getPath());
			out = new PrintStream(outputInput.get());
		}
		
		int h = n * dy + 50;
		out.println(svgHeader.replaceAll("height=\"\"","height=\"" + h + "\""));
		
		
		for (int i = 0; i < n; i++) {
			Rule rule = rules[i];
			String s = rule.condition;
			int start = s.indexOf("(+@");
			while (start > 0) {
				int end = s.indexOf(")", start);
				String label = s.substring(start+3, end);
				int target = 0;
				while (target < n && (rules[target].label == null || !rules[target].label.equals(label))) {
					target++;
				}
				if (target == n) {
					throw new IllegalArgumentException("Could not find match for (+@" + label + ") at rule " + i + " " + rule.condition);
				}
				target++;
				s = s.substring(0, start) + "<a href=\"#" + target +"\">at " + target + "</a>" + s.substring(end+1);
				start = s.indexOf("(+@");
			}
			if (rule.go_to >= 0) {
				s += "<a href=\"#" + rule.go_to +"\">go to " + rule.go_to + "</a>";
			}
			

			String c;
			if (i < n-1 && rule.indent >= rules[i+1].indent) {
				c = "target";
			} else {
				c = "label";
			}
			out.println("<text id=\""+(i+1)+"\" class=\""+c+"\" x=\""+(40+rule.indent*dx)+"\" y=\""+(i*dy+offsety)+"\">"+s+
					"</text>");

			out.println("<text class=\"label\" x=\""+(i<9?"7":"0")+"\" y=\""+(i*dy+offsety)+"\">"+(i+1)+".</text>");
		}
		
		
		
		
		for (int i = 0; i < n-1; i++) {
			Rule rule = rules[i];

			if (rule.indent < rules[i+1].indent) {
				int x = 40 + rule.indent*dx + 30;
				int y = offsety + i*dy + 2;
				if (i < n-2) {
					if (rule.indent <= rules[i+2].indent) {
						out.print("<path d=\"M "+x+" "+y+" c 0 8,0 8, 10 8\" class=\"arrow\" marker-end=\"url(#arrow)\"/>\n");
						x -= 12;
						y += 12;
						out.print("<text x=\""+x+"\" y=\""+y+"\" class=\"smalllabel\">yes</text>\n");
					} else if (rules[i+1].indent > rules[i+2].indent) {
						out.print("<path d=\"M "+x+" "+y+" c 0 8,0 8, 10 8\" class=\"arrow\" marker-end=\"url(#arrow)\"/>\n");
					}
				}
				
				int j = i + 1;
				while (j < n && rule.indent < rules[j].indent) {
					j++;
				}
				if (j < n && rule.indent == rules[j].indent) {
					x = 40 + rule.indent*dx + 2;
					int y1 = offsety + i*dy + 5;
					int y2 = offsety + j*dy - dy - 5;
					out.print("<path d=\"M "+x+" "+y1+" L "+x+" "+y2+"\" class=\"arrow\" marker-end=\"url(#arrow)\"/>\n");
					x -= 12;
					y1 += 9;
					out.print("<text x=\""+x+"\" y=\""+y1+"\" class=\"smalllabel\">no</text>\n");
				}
			} else if (rule.indent == rules[i+1].indent) {
				int x = 40 + rule.indent*dx + 30 - dx;
				int y = offsety + i*dy - 6;
				out.print("<path d=\"M "+x+" "+y+" c 0 16,0 16, 10 16\" class=\"arrow\" marker-end=\"url(#arrow)\"/>\n");
				x += 12;
				y += 12;
				out.print("<text x=\""+x+"\" y=\""+y+"\" class=\"smalllabel\">or</text>\n");
			}

		}
		
		out.println("</svg>");
		if (outputInput.get() != null && !outputInput.get().getName().equals("[[none]]")) {
			out.close();
		}
        Log.err.println("Done");
	}

	
	public static void main(String[] args) throws Exception {
		new Application(new KB2SVG(), "KB to SVG", args);
	}
}
