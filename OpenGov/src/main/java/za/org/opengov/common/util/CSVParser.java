/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
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
 * Performs parsing of a CSV (Comma Separated Value) file.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 * 
 */
public class CSVParser {

	private InputStream inputSteam;
	/**
	 * Token used to separate elements in a line (usually a comma)
	 */
	private String separatorToken;
	/**
	 * Delimiter used to escape the separator token. (usually a quotation mark)
	 */
	private String textDelimiter;

	/**
	 * Represents lines and elements of the parsed CSV file. 
	 */
	private List<List<String>> csvMatrix;

	/**
	 * 
	 * @param inputStream
	 * @param separatorToken
	 * @param textDelimiter
	 * @throws FileNotFoundException
	 */
	public CSVParser(InputStream inputStream, String separatorToken,
			String textDelimiter) throws FileNotFoundException {
		this.csvMatrix = new ArrayList<List<String>>();
		this.inputSteam = inputStream;
		this.separatorToken = separatorToken;
		this.textDelimiter = textDelimiter;
		parseInput();
	}

	/**
	 * 
	 * @throws FileNotFoundException
	 */
	private void parseInput() throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputSteam));

		String line = "";

		try {
			while ((line = reader.readLine()) != null) {
			

				String[] tokens = line.split(separatorToken + "(?=([^"
						+ textDelimiter + "]*" + textDelimiter + "[^"
						+ textDelimiter + "]*" + textDelimiter + ")*[^"
						+ textDelimiter + "]*$)", -1);
				

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

	/**
	 * 
	 * @return
	 */
	public int getColumnsCount() {
		if (csvMatrix.size() > 0) {
			return csvMatrix.get(0).size();
		} else {
			return -1;
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public String getElement(int row, int column){
		return csvMatrix.get(row).get(column);
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public List<String> getRow(int index){
		return csvMatrix.get(index);
	}

	/**
	 * 
	 * @return
	 */
	public List<List<String>> getRows() {
		return csvMatrix;
	}

	/**
	 * 
	 * @return
	 */
	public String getSeparatorToken() {
		return separatorToken;
	}

	
	/**
	 * 
	 * @return
	 */
	public String getTextDelimiter() {
		return textDelimiter;
	}

}
