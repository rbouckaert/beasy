package methods;

import java.util.List;

import beast.core.*;

public class CitationPhrase extends Phrase {
	String DOI;
	Citation citation;
	
	public enum mode {none,bibtex,markdown,text}
	
	public static mode CitationMode = mode.text;

	public CitationPhrase(Object source) {
		super(source, "");
		
		if (source instanceof BEASTInterface) {
			BEASTInterface bi = (BEASTInterface) source;
			List<Citation> c = bi.getCitationList();
			if (c != null && c.size() > 0) {
				citation = c.get(0);
				DOI = citation.DOI();
				if (DOI != null) {
					setText(toString());
				}
			}
		}
	}

	public CitationPhrase(String DOI) {
		super(DOI);
		this.DOI = DOI;		
		setText(toString());
	}
	
	@Override
	public String toString() {
		switch (CitationMode) {
		case none:
			return "";
		case bibtex:
			return bibtexRef();
		case text:
			return textRef();
		case markdown:
			return markdownRef();
		}
		return "";
	}

	private String markdownRef() {
		try {
			String citation = DOI2Citation.resolve(DOI);
			return bibtex2markdownRef(citation);
		} catch (Exception e) {
			e.printStackTrace();
			return "[@" + DOI + "]";
		}
	}

	public static String bibtex2markdownRef(String citation) {
		String ref = bibtex2textRef(citation);
		ref = ref.replaceAll("[\\(\\) ]","");
		return "[@" + ref + "]";
	}


	private String textRef() {
		try {
			String citation = DOI2Citation.resolve(DOI);
			return bibtex2textRef(citation);
		} catch (Exception e) {
			e.printStackTrace();
			return "(" + DOI + ")";
		}
	}

	/** format text reference from bibtex citation **/
	public static String bibtex2textRef(String citation) {
		String [] strs = citation.split("\n");
		String author = "";
		String year = "";
		for (String str : strs) {
			if (str.trim().startsWith("author=")) {
				author = str.substring(str.indexOf('{') + 1, str.indexOf('}'));
				String [] authors = author.split("and");
				if (authors.length == 1) {
					author = getSurname(author);
				} else if (authors.length == 2) {
					author = getSurname(authors[0]) + " and " + getSurname(authors[1]);
				} else {
					author = getSurname(authors[0]) + " et al.";
				}
			}
			if (str.trim().startsWith("year=")) {
				year = str.substring(str.indexOf("year=") + 6, str.indexOf('}'));
			}
		}
		return author + " (" + year + ")";
	}


	private static String getSurname(String author) {
		if (author.indexOf(',') > 0) {
			author = author.substring(0, author.indexOf(','));
		}
		return author;
	}


	private String bibtexRef() {
		try {
			String citation = DOI2Citation.resolve(DOI);
			String cite = citation.substring(citation.indexOf('{') + 1, citation.indexOf(','));
			return "\\cite{" + cite + "}";
		} catch (Exception e) {
			return "\\cite{" + DOI + "}";
		}
	}
}
