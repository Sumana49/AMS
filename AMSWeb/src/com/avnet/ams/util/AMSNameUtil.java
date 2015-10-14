package com.avnet.ams.util;

import java.util.ArrayList;
import java.util.List;

public class AMSNameUtil {

	/**
	 * @author Dinesh
	 * @param stringArray
	 * @return sentence cased words for labels from variable type named fields
	 */
	public static String[] convertToLabelName(String[] stringArray) {
		StringBuilder sentenceWords = null;
		List<String> stringList = new ArrayList<String>();

		// loop each set of words in the array
		for (String s : stringArray) {
			// splitting if the character is either '_' or 'UpperCase'
			String sArray[] = s.split("([-_ ]|(?<=[^-_ A-Z])(?=[A-Z]))");
			sentenceWords = new StringBuilder();

			// loop each word in the set of words
			for (String word : sArray) {
				char firstLetter = word.charAt(0);
				char upperCase = String.valueOf(firstLetter).toUpperCase()
						.charAt(0);
				StringBuilder SentenceCaseWord = new StringBuilder(
						word.toLowerCase());
				SentenceCaseWord.setCharAt(0, upperCase);
				sentenceWords.append(SentenceCaseWord + " ");
			}
			// removing last character, to remove the last space character
			stringList.add(sentenceWords.substring(0,
					sentenceWords.length() - 1).toString());
		}

		String[] returnArray = stringList
				.toArray(new String[stringList.size()]);
		return returnArray;
	}

	/**
	 * @author Dinesh
	 * @param stringArray
	 * @return sentence cased words for labels from variable type named fields
	 */
	public static String[] convertToVariableName(String[] inputArray) {
		StringBuilder finalVariableName = null;
		List<String> stringList = new ArrayList<String>();

		// loop each set of words in the array
		for (String s : inputArray) {
			String sArray[] = s.split("[ ]|[_]|(?<=[^-_ A-Z])(?=[A-Z])");
			finalVariableName = new StringBuilder();

			// loop each word in the set of words
			for (int i = 0; i < sArray.length; i++) {
				String word = sArray[i].toLowerCase();
				StringBuilder variableNameWord = new StringBuilder(word);
				if (i == 0) {
					char firstLetter = word.charAt(0);
					char lowerCaseFirstLetter = String.valueOf(firstLetter)
							.toLowerCase().charAt(0);
					variableNameWord.setCharAt(0, lowerCaseFirstLetter);
				} else {
					char firstLetter = word.charAt(0);
					char upperCaseFirstLetter = String.valueOf(firstLetter)
							.toUpperCase().charAt(0);
					variableNameWord.setCharAt(0, upperCaseFirstLetter);
				}

				finalVariableName.append(variableNameWord + "");
			}
			stringList.add(finalVariableName.toString());
		}

		String[] returnArray = stringList
				.toArray(new String[stringList.size()]);
		return returnArray;
	}

	/**
	 * @author Dinesh
	 * @param string
	 * @return converted Sentence Case Word
	 */
	public static String convertToLabelName(String inputString) {
		StringBuilder sentenceWords = null;

		// splitting if the character is either '_' or 'UpperCase'
		String sArray[] = inputString.split("([-_ ]|(?<=[^-_ A-Z])(?=[A-Z]))");
		sentenceWords = new StringBuilder();

		// loop each word in the set of words
		for (String word : sArray) {
			char firstLetter = word.charAt(0);
			char upperCase = String.valueOf(firstLetter).toUpperCase()
					.charAt(0);
			StringBuilder SentenceCaseWord = new StringBuilder(
					word.toLowerCase());
			SentenceCaseWord.setCharAt(0, upperCase);
			sentenceWords.append(SentenceCaseWord + " ");
		}
		// removing last character, to remove the last space character
		String outputString = sentenceWords.substring(0,
				sentenceWords.length() - 1).toString();

		return outputString;
	}

	/**
	 * @author Dinesh
	 * @param string
	 * @return converted variableNameConventionalTypeWord
	 */
	public static String convertToVariableName(String inputString) {
		StringBuilder finalVariableName = null;

		String sArray[] = inputString.split("[ ]|[_]|(?<=[^-_ A-Z])(?=[A-Z])");
		finalVariableName = new StringBuilder();

		// loop each word in the set of words
		for (int i = 0; i < sArray.length; i++) {
			String word = sArray[i].toLowerCase();
			StringBuilder variableNameWord = new StringBuilder(word);
			if (i == 0) {
				char firstLetter = word.charAt(0);
				char lowerCaseFirstLetter = String.valueOf(firstLetter)
						.toLowerCase().charAt(0);
				variableNameWord.setCharAt(0, lowerCaseFirstLetter);
			} else {
				char firstLetter = word.charAt(0);
				char upperCaseFirstLetter = String.valueOf(firstLetter)
						.toUpperCase().charAt(0);
				variableNameWord.setCharAt(0, upperCaseFirstLetter);
			}

			finalVariableName.append(variableNameWord + "");
		}
		String outputString = finalVariableName.toString();

		return outputString;
	}

	/**
	 * @author Dinesh
	 * @param stringArray
	 * @return sentence cased words for labels from variable type named fields
	 */
	public static String convertToClassVariableName(String inputString) {
		StringBuilder finalVariableName = null;

		String sArray[] = inputString.split("[ ]|[_]|(?<=[^-_ A-Z])(?=[A-Z])");
		finalVariableName = new StringBuilder();

		// loop each word in the set of words
		for (int i = 0; i < sArray.length; i++) {
			String word = sArray[i].toLowerCase();
			StringBuilder variableNameWord = new StringBuilder(word);
			if (i == 0) {
				char firstLetter = word.charAt(0);
				char lowerCaseFirstLetter = String.valueOf(firstLetter)
						.toLowerCase().charAt(0);
				variableNameWord.setCharAt(0, lowerCaseFirstLetter);
			} else {
				char firstLetter = word.charAt(0);
				char upperCaseFirstLetter = String.valueOf(firstLetter)
						.toUpperCase().charAt(0);
				variableNameWord.setCharAt(0, upperCaseFirstLetter);
			}

			finalVariableName.append(variableNameWord + "");
		}

		return finalVariableName.toString();
	}
}
