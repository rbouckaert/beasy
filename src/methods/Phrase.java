package methods;

import java.util.List;

import beast.core.StateNode;

/** Contains information about a word or phrase in the MethodsText,
 * including a pointer to where the phrase came from 
 */
public class Phrase {
	/** object being described **/
	Object source;
	
	/** parameter assciated with the source **/
	StateNode parameter;
	
	/** text description of the object **/
	String text;
	
	public Phrase(Object source, String phrase, StateNode parameter) {
		this.source = source;
		this.text = phrase;
		this.parameter = parameter;
	}

	
	public Phrase(Object source, String phrase) {
		this(source, phrase, null);
	}

	public Phrase(String phrase) {
		this(null, phrase);
	}
	
	static String toString(List<Phrase> phrases) {
		StringBuilder b = new StringBuilder();
		for (Phrase phrase : phrases) {
			b.append(phrase.text);
		}
		return b.toString();
	}
}
