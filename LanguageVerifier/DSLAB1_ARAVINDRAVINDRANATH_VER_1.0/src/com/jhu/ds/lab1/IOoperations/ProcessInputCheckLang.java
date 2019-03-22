/**
 * 
 */
package com.jhu.ds.lab1.IOoperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.jhu.ds.lab1.langcheck.*;

/**
 * @author  ARAVIND RAVINDRANATH
 * @version 1.0
 *
 *          The class contains logic to read a file placed in the Project Folder.
 *          This file would have the language patterns to be assessed and then
 *          compared against defined patterns. This class will take the role of
 *          the driver.
 *
 */
public class ProcessInputCheckLang {

	/**
	 * This is the main method which will be called in this project. The input file
	 * which contains strings to be analyzed and an output file which contains
	 * the result of the analysis are received here from the console. These are
	 * passed into another method for the actual parsing and verification.
	 * The user will be asked for a file until a valid one is provided as input
	 * The loop limit has been set to 5 by default, after which it returns.
	 * 
	 * @param args The normal dynamic arguments passed to the main. An integer
	 *             can be passed as the first argument to control the number of times
	 *             the user is asked for a valid file. 
	 */
	public static void main(String[] args) {

		boolean bRepeat = true;
		Scanner scan = new Scanner(System.in);
		int counter = 0;
		int limit = 0;
		try{
			limit = Integer.parseInt(args[0]);
		}catch (NumberFormatException e ){
			System.out.println("Please enter a valid integer next time.");
			System.out.println("System will prompt for file names "
					+ "5 times and then exit");
			limit =5;
		}catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Please enter a valid integer next time.");
			System.out.println("System will prompt for file names "
					+ "5 times and then exit");
			limit =5;			
		}
		while (bRepeat) {
			counter += 1;
			String[] files = new String[2]; // takes an input and output file
			System.out.println("Enter the Input FileName : ");
			files[0] = scan.nextLine();
			System.out.println("Enter the Output FileName : ");
			files[1] = scan.nextLine();
			try{
				fileReader(files[0], files[1]);
				bRepeat = false;
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			if (counter == limit){
				System.out.println("Not entering a valid file. System tired!");
				break;
			}
		}//end while
		scan.close();
	}

	/**
	 * This is the method which will call File I/O JAVA library methods. The
	 * input file will be opened and using a buffered reader, the contents will
	 * be read line by line. A handle to output file will also be instantiated
	 * so that the results can be written into it as well. The string in each
	 * line will be traversed character by character and fed into the stack and
	 * the verifier methods will be invoked on the string in each line. The
	 * result comes back in an array and that is used to build the string which
	 * states which language type holds good.
	 * 
	 * @param InpFile
	 *            Input File which contains the string for analysis OutFile The
	 *            file to which the analyzed results are written.
	 */

	public static void fileReader(String InpFile, String OutFile) 
			throws IOException {
		String line;

		int[] langtypes = new int[EnumLanguageType.values().length];

		FileReader inputStream = new FileReader(InpFile); // input file
		FileWriter outputStream = new FileWriter(OutFile); // output file
		BufferedReader bReader = new BufferedReader(inputStream); // line reader

		String outString = "";
		String sEmpty = "";
		String display = "";
		while (true) {
			line = bReader.readLine();
			if (line == null) { // End of File
				break;
			} else {
				LangParser langparser = new LangParser();
				outString = "";
				for (int i = 0; i < line.length(); i++) {
					langparser.fill_stacks(line.charAt(i));
				}
				langtypes = langparser.checkNDetectLangType();
				langparser.clear_all_stacks();
				display = line + ": " + "L"+ langtypes[0] + " " + "L"+ langtypes[1] 
						+ " " + "L"+ langtypes[2] + " " + "L"+ langtypes[3] + " " 
						+ "L" + langtypes[4] + " " + "L"+ langtypes[5] + " " 
						+ "L"+ langtypes[6];
				display = display.replaceAll("L0", "").replaceAll("  ", " ");
				System.out.println(display);
				outputStream.write(line + "\n");
				for (int j = 0; j < langtypes.length; j++) {
					if (langtypes[j] != 0) {
						outString += " L" + EnumLanguageType.getLangType(j).toString();
					}
				}
				if (outString != "") {
					outString = "The string above is valid in " + "the following languages: " 
									+ outString;
					outputStream.write(outString + "\n" + "\n");
					outString = "";
				} else {
					if (line.length() == 0)
						sEmpty = "Empty ";
					outputStream.write("There are no valid languages " + "which accepts the " 
						               + sEmpty + "string above\n" + "\n");
					sEmpty = "";
				}
			}
		}
		bReader.close();
		outputStream.close();

	}

}
