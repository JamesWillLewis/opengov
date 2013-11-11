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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Provides string-matching to one or more other strings based on closeness. In
 * other words, allows for the most similar string(s) to be found which are most
 * similar in spelling (word distance) to a given string.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 * 
 */
public class StringMatcher {

	private List<Element> elements;

	public StringMatcher() {
		elements = new LinkedList<StringMatcher.Element>();
	}

	/**
	 * 
	 * @param elementsToCompare
	 */
	public StringMatcher(List<StringMatchable> elementsToCompare) {
		this();
		if (elementsToCompare == null) {
			throw new IllegalArgumentException(
					"elementsToCompare can not be null");
		}
		// wrap each StringMatchable in an Element
		for (StringMatchable stringMatchable : elementsToCompare) {
			Element element = new Element(stringMatchable);
			elements.add(element);
		}

	}

	/**
	 * 
	 * @param stringMatchable
	 */
	public void addStringMatchable(StringMatchable stringMatchable) {
		elements.add(new Element(stringMatchable));
	}

	/**
	 * Returns the string which has the lowest string-distance value to the
	 * given string.
	 * 
	 * @param stringMatchableToMatch
	 * @return
	 */
	public StringMatchable getClosestMatch(String stringMatchableToMatch) {

		if (stringMatchableToMatch == null) {
			throw new IllegalArgumentException(
					"stringMatchableToMatch must not be null");
		}

		Element closestMatchable = new Element(null);
		closestMatchable.distance = Integer.MAX_VALUE;

		for (Element nextElement : elements) {
			String toCompare = nextElement.stringMatchable.getStringToMatch();
			if (toCompare == null) {
				// a null string is considered an empty string
				toCompare = "";
			}
			nextElement.distance = computeLevenshteinDistance(
					stringMatchableToMatch, toCompare);
			if (nextElement.distance < closestMatchable.distance) {
				closestMatchable = nextElement;
			}
		}

		return closestMatchable.stringMatchable;
	}

	/**
	 * 
	 * @param stringMatchableToMatch
	 * @param limit
	 * @return
	 */
	public List<StringMatchable> getClosestMatches(
			StringMatchable stringMatchableToMatch, int limit) {

		if (stringMatchableToMatch == null) {
			throw new IllegalArgumentException(
					"stringMatchableToMatch must not be null");
		}
		if (limit < 1) {
			throw new IllegalArgumentException("limit must be >= 1");
		}

		for (Element nextElement : elements) {
			String toCompare = nextElement.stringMatchable.getStringToMatch();
			if (toCompare == null) {
				// a null string is considered an empty string
				toCompare = "";
			}
			nextElement.distance = computeLevenshteinDistance(
					stringMatchableToMatch.getStringToMatch(), toCompare);

		}

		Collections.sort(elements);

		List<StringMatchable> orderedResults = new LinkedList<StringMatchable>();

		for (int i = 0; i < limit && i < elements.size(); i++) {
			orderedResults.add(elements.get(i).stringMatchable);
		}

		return orderedResults;
	}

	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	private int computeLevenshteinDistance(String s1, String s2) {

		s1 = s1.toLowerCase();

		s2 = s2.toLowerCase();
		// trimming the word allows better matching to a word prefix
		if (s2.length() > s1.length()) {
			s2 = s2.substring(0, s1.length());
		}

		int[] costs = new int[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0)
					costs[j] = j;
				else {
					if (j > 0) {
						int newValue = costs[j - 1];
						if (s1.charAt(i - 1) != s2.charAt(j - 1))
							newValue = Math.min(Math.min(newValue, lastValue),
									costs[j]) + 1;
						costs[j - 1] = lastValue;
						lastValue = newValue;
					}
				}
			}
			if (i > 0)
				costs[s2.length()] = lastValue;
		}
		return costs[s2.length()];
	}

	/**
	 * 
	 * @author james
	 * 
	 */
	private class Element implements Comparable<Element> {

		private int distance;
		private StringMatchable stringMatchable;

		public Element(StringMatchable stringMatchable) {
			this.stringMatchable = stringMatchable;
		}

		@Override
		public int compareTo(Element o) {
			if (distance < o.distance) {
				return -1;
			} else if (distance == o.distance) {
				return 0;
			} else {
				return 1;
			}
		}

	}

}
