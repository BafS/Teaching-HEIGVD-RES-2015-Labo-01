package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import org.apache.commons.io.comparator.NameFileComparator;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

	@Override
	public void explore(File rootDirectory, IFileVisitor visitor) {
		visitor.visit(rootDirectory);

		File[] files = rootDirectory.listFiles();

		if(files != null) {
			Arrays.sort(files, NameFileComparator.NAME_INSENSITIVE_COMPARATOR);

			for(File file : files) {
				if(file.isFile()) {
					visitor.visit(file);
				}
			}

			for(File dir : files) {
				if(dir.isDirectory()) {
					explore(dir, visitor);
				}
			}
		}

//    throw new UnsupportedOperationException("The student has not implemented this method yet.");
	}

}
