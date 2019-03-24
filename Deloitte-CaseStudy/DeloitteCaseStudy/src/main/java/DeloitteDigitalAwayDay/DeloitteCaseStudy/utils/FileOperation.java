package DeloitteDigitalAwayDay.DeloitteCaseStudy.utils;

import java.io.File;
import java.util.Scanner;

public class FileOperation {
	
	/**
	 * Method to capture input from the user.
	 * Asks for file path and returns the value
	 * 
	 */
	@SuppressWarnings("resource")
	public static String getFilePathInput() {
		String path = "";
		Scanner scanner = new Scanner(System.in);
		while(Utilities.isNullOrEmpty(path)) {
			System.out.println("Enter file Path: ");
			path = scanner.nextLine();
		}
		return path;
	}
	
	/**
	 * File Read operation
	 * Read file content from the file path
	 * 
	 * @param filePath is the file path
	 * @return Returns file content
	 */
	@SuppressWarnings("resource")
	public static String readFileContent(String filePath) throws Exception {
		
		if(Utilities.isNullOrEmpty(filePath)) throw new Exception("File path cant be Empty");
		
    	return new Scanner(new File(filePath)).useDelimiter("\\Z").next();
	}
}
