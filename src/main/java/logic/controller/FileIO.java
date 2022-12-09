package logic.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FileIO {
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public FileIO() {}

	/**
	 * 
	 * @param fileName
	 * @return contents of entire file
	 */
	public String readInEntireFile(String fileName) {
		StringBuilder stringToBuild = new StringBuilder();
		String lines = "";
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while((lines = reader.readLine()) != null)
				stringToBuild.append(lines).append('\n');
			reader.close();
		} catch (IOException e) { System.out.println(e); }

		return stringToBuild.toString();
	}
	
	/**
	 * 
	 * @param fileName
	 * @param text - String wanting to save to file. OVERWRITES FILE CONTENTS 
	 */
	public void writeToFile(String fileName, String text) {
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(text);
			writer.close();
		} catch (IOException e) { System.out.println("File Writer Class: " + e); }
		
	}
	
	/**
	 * 
	 * @param fileName
	 * @param text - String wanting to save to file. OVERWRITES FILE CONTENTS 
	 */
	public void writeToFile(Path fileName, String text) {
		try {
			writer = new BufferedWriter(new FileWriter(fileName.toString()));
			writer.write(text);
			writer.close();
		} catch (IOException e) { System.out.println("File Writer Class: " + e); }
		
	}
}
