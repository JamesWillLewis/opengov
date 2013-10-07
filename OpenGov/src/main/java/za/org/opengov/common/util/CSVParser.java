package za.org.opengov.common.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 * 
 */
public class CSVParser {

	private InputStream inputSteam;
	private String separatorToken;
	private String textDelimiter;

	private List<List<String>> csvMatrix;

	public CSVParser(InputStream inputStream, String separatorToken,
			String textDelimiter) throws FileNotFoundException {
		this.csvMatrix = new ArrayList<List<String>>();
		this.inputSteam = inputStream;
		this.separatorToken = separatorToken;
		this.textDelimiter = textDelimiter;
		parseInput();
	}

	private void parseInput() throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputSteam));

		String line = "";

		try {
			while ((line = reader.readLine()) != null) {
				
				System.out.println(line);

				String[] tokens = line.split(separatorToken + "(?=([^"
						+ textDelimiter + "]*" + textDelimiter + "[^"
						+ textDelimiter + "]*" + textDelimiter + ")*[^"
						+ textDelimiter + "]*$)", -1);
				
				System.out.println(tokens.length);
				System.out.println(Arrays.toString(tokens));

				ArrayList<String> newRow = new ArrayList<String>(tokens.length);

				for (String token : tokens) {

					// if token has the escape delimiter, remove it from the
					// beginning and end
					if (token.startsWith(textDelimiter)
							&& token.endsWith(textDelimiter)) {
						token = token.substring(textDelimiter.length(),
								token.length() - textDelimiter.length());
					}

					newRow.add(token);
				}

				csvMatrix.add(newRow);

			}
		} catch (IOException e) {
			System.err.println("Failed to read line from CSV file: "
					+ e.getMessage());
		}
	}

	public int getRowsCount() {
		return csvMatrix.size();
	}

	public int getColumnsCount() {
		if (csvMatrix.size() > 0) {
			return csvMatrix.get(0).size();
		} else {
			return -1;
		}
	}
	
	public String getElement(int row, int column){
		return csvMatrix.get(row).get(column);
	}
	
	public List<String> getRow(int index){
		return csvMatrix.get(index);
	}

	public List<List<String>> getRows() {
		return csvMatrix;
	}

	public String getSeparatorToken() {
		return separatorToken;
	}

	public String getTextDelimiter() {
		return textDelimiter;
	}

}
