package org.dynamicfinder.example;

import java.util.StringTokenizer;

public class CriteriaUtil {

	/**
	 * Get criteria value based on 'field' for 'keyword' supplied. Format is 
	 * <code>field1:value1 field2:value2 field3:value3 .... etc.</code> 
	 * Never return null. If no value found, empty string returned.
	 * @param field field to get.
	 * @param keyword phrase.
	 * @return Actual 'field' value, or empty string if no field found.
	 */
	public static String getCriteriaFromTokenizedKeyword(final String field, final String keyword) {
		if (field == null || keyword == null) {
			// OR perhaps throw illegalargumentexception is better..
			return "";
		}
		StringTokenizer keywordToken = new StringTokenizer(keyword.trim(), " ");
		while (keywordToken.hasMoreElements()) {
			final String keywordString = keywordToken.nextToken();
			StringTokenizer fieldToken = new StringTokenizer(keywordString, ":");
			final String fieldString = fieldToken.nextToken(); // This is will get 'field' name.
			if (fieldString.equals(field)) {
				return fieldToken.nextToken(); // Next token, get actual field value. 
			}
		}
		return "";
	}
}
