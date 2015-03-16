package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

	private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
	private boolean isFirst;
	private int lineNumber;

	public FileNumberingFilterWriter(Writer out) {
		super(out);
		isFirst = true;
		lineNumber = 0;
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		if(isFirst) {
			isFirst = !isFirst;
		}

		String currentLine = str.substring(off, off + len);
		currentLine = currentLine.replaceAll("[0-9]", "");
		currentLine = currentLine.replaceAll("[\n\r]", " " + Integer.toString(lineNumber++) + "\t");
		out.write(Integer.toString(lineNumber++) + "\t" + currentLine);
//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		this.write(String.valueOf(cbuf), off, len);
//		throw new UnsupportedOperationException("The student has not implemented this method yet.");
	}

	@Override
	public void write(int c) throws IOException {
		this.write(Character.toChars(c), 0, 1);
//		throw new UnsupportedOperationException("The student has not implemented this method yet.");
	}

}
