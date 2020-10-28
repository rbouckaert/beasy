
$dy = 15;
$dx = 50;

$n = 0;
while ($s = <>) {
	if ($s =~ /{#([a-zA-Z0-9]+)}/) {
		$id{$1} = $n + 1;
		$s =~ s/{#([a-zA-Z0-9]+)}//;
	}
	$k = 0;
	while (substr($s,$k,1) =~ /\s/) {
		$k++;
	}
	$indent[$n] = $k;
	chomp($s);
	$s[$n++] = $s;
}

# dummy for last line condition
$indent[$n] = -1;


print '<svg xmlns="http://www.w3.org/2000/svg" height="'.($dy * $n + 50).'" width="1000">
<style type="text/css">
.label {fill:#000;stroke:none;font-family:arial;font-size:10pt;font-style: normal;}
.target {fill:#a00;stroke:none;font-family:arial;font-size:10pt;font-style: normal;}
.labeld {fill:#aaa;stroke:none;font-family:arial;font-size:10pt;font-style: normal;}
.smalllabel {fill:#0000f0;font-size:8pt;font-family:arial;text-anchor:middle;alignment-baseline:centre;}
.arrow {stroke:blue;stroke-width:2;fill:none;}
</style>
  <defs>
    <marker id="arrow" markerWidth="10" markerHeight="10" refX="0" refY="3" orient="auto" markerUnits="strokeWidth">
    	<g transform="matrix(0.5,0,0,0.5,0,1.5)">
	      <path d="M0,0 L0,6 L9,3 z" fill="#00f"/>
    	</g>
    </marker>
  </defs>
';





for ($i = 0; $i < $n; $i++) {
	while ($s[$i] =~ /\(\+\@([a-zA-Z0-9\.\-\_]+)\)/) {
		$id = $1;
		if ($id{$id} ne '') {
			$s[$i] =~ s/\(\+\@[a-zA-Z0-9\.\-\_]+\)/$id{$id}/;
		}
	}
#	if ($s[$i] =~ /@([a-zA-Z0-9]+)/) {
#		$id = $1;
#		if ($id{$id} ne '') {
#			$s[$i] =~ s/@[a-zA-Z0-9]+/\@line $id{$id}/;
#		}
#	}
	if ($i > 0 && $indent[$i] >= $indent[$i + 1]) {
		$c = "target";
	} else {
		$c = "label";
	}
	print "<text class=\"$c\" x=\"".(40+$indent[$i]*$dx)."\" y=\"".($i*$dy+20)."\">".$s[$i]."</text>\n";
	
	print "<text class=\"label\" x=\"".($i<9?"7":"0")."\" y=\"".($i*$dy+20)."\">".($i+1).".</text>\n";
}


for ($i = 0; $i < $n; $i++) {

	if ($indent[$i] < $indent[$i + 1]) {
		$x = 40 + $indent[$i]*$dx + 30;
		$y = 20 + $i*$dy + 2;
		if ($indent[$i] <= $indent[$i + 2]) {
			print "<path d=\"M $x $y c 0 8,0 8, 10 8\" class=\"arrow\" marker-end=\"url(#arrow)\"/>\n";
			$x -= 12;
			$y += 12;
			print "<text x=\"$x\" y=\"$y\" class=\"smalllabel\">yes</text>\n";
		} elsif ($indent[$i + 1] > $indent[$i + 2]) {
			print "<path d=\"M $x $y c 0 8,0 8, 10 8\" class=\"arrow\" marker-end=\"url(#arrow)\"/>\n";
		}
		
		$j = $i + 1;
		while ($j < $n && $indent[$i] < $indent[$j]) {
			$j++;
		}
		if ($indent[$i] == $indent[$j]) {
			$x = 40 + $indent[$i]*$dx + 2;
			$y1 = 20 + $i*$dy + 5;
			$y2 = 20 + $j*$dy - $dy - 5;
			print "<path d=\"M $x $y1 L $x $y2\" class=\"arrow\" marker-end=\"url(#arrow)\"/>\n";
			$x -= 12;
			$y1 += 9;
			print "<text x=\"$x\" y=\"$y1\" class=\"smalllabel\">no</text>\n";
		}		
	} elsif ($indent[$i] == $indent[$i + 1]) {
		$x = 40 + $indent[$i]*$dx + 30 - $dx;
		$y = 20 + $i*$dy - 6;
		print "<path d=\"M $x $y c 0 16,0 16, 10 16\" class=\"arrow\" marker-end=\"url(#arrow)\"/>\n";
		$x += 12;
		$y += 12;
		print "<text x=\"$x\" y=\"$y\" class=\"smalllabel\">or</text>\n";
	}

}

print "</svg>\n";