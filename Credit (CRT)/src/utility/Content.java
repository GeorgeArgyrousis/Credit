package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Content {
	
	private static final boolean DEBUG = false;

	/** The path of the file to perform operations */
	private Path path;

	public Content(String path) {
		this.path = Paths.get(path);
		this.generateFile();
	}

	/**
	 * Generate the file in case that it doesn't exist.
	 */
	private void generateFile() {
		try {
			if (new File(this.path.toString()).createNewFile()) write(1, "0");
			if(this.read(1).equals("Line doesn't exist") || this.read(1).equals(""))  write(1, "0");
		} catch (IOException e) {
			System.err.println("Something went wrong. Content -> generateFile \n" + e.getMessage());
		}
	}

	/**
	 * Allows content to be writen in a file at a specific line index. NOTE ::
	 * Computational hungry if the file is large;
	 * 
	 * @param The line.
	 * @param The content.
	 */
	public void write(int line, String content) {
		List<String> lines = readFile();
		if(DEBUG) System.out.println(lines.size());
		while (line > lines.size()) {
			lines.add("");
		}
		lines.set(line - 1, content);
		writeFile(lines);
	}
	
	/**
	 * Allow a list of content to be written under
	 * the current content of the file.
	 * @param The list of content.
	 */
	public void write(List<String> content){
		List<String> lines = readFile();
		lines.addAll(content);
		writeFile(content);
	}

	/**
	 * Read a line out of a specified files
	 * and return the line as a String.
	 * @param The line to be read.
	 * @return The line.
	 */
	public String read(int line) {
		List<String> lines = readFile();
		if(line > lines.size()){
			return "Line doesn't exist";
		}
		return lines.get(line - 1);
	}
	
	/**
	 * Read multiple lines out of a file.
	 * NOTE :: for -1, -1 as input it returns
	 * the whole read list.
	 * @param The reading starting point.
	 * @param The reading ending point.
	 * @return The list of read content.
	 */
	public List<String> read(int from, int to){
		if(from == -1 && to == -1) return readFile();
		List<String> content = new ArrayList<String>();
		if(DEBUG) System.out.println("Reading from line :" + from);
		while(from <= to){
			content.add(this.read(from));
			from++;
		}
		if(DEBUG) content.forEach(System.out::println);
		if(DEBUG) System.out.println("To line :" + to);
		return content;
	}

	/**
	 * Read all the lines of a file.
	 * NOTE :: this is complexity consuming
	 * with large files.
	 * @return The list with all the lines.
	 */
	private List<String> readFile() {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(this.path);
		} catch (IOException e) {
			System.err.println("Something went wrong. Content -> readFile \n" + e.getMessage());
		}
		if(lines == null) lines = new ArrayList<String>();
		return lines;
	}
	
	/**
	 * Write all the lines back to the file.
	 * @param The list of lines.
	 */
	private void writeFile(List<String> lines){
		try {
			Files.write(this.path, lines);
		} catch (IOException e) {
			System.err.println("Something went wrong. Content -> writeFile \n" + e.getMessage());
		}
	}
}