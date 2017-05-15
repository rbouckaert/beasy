package beasy;

import java.io.File;

public class InFile extends File {

	private static final long serialVersionUID = 1L;

	public InFile(File parent, String child) {
		super(parent, child);
	}

	public InFile(String string) {
		super(string);
	}

}
