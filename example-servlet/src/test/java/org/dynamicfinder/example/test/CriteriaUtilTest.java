package org.dynamicfinder.example.test;

import junit.framework.Assert;

import org.dynamicfinder.example.CriteriaUtil;
import org.junit.Test;

public class CriteriaUtilTest {

	@Test
	public void getCriteriaFromTokenizedKeywordTest() {
		String criteria = CriteriaUtil.getCriteriaFromTokenizedKeyword("firstName", "firstName:xsa lastName:lefter gender:male");
		Assert.assertEquals("xsa", criteria);

		criteria = CriteriaUtil.getCriteriaFromTokenizedKeyword("lastName", "firstName:xsa lastName:lefter gender:male");
		Assert.assertEquals("lefter", criteria);

		criteria = CriteriaUtil.getCriteriaFromTokenizedKeyword("gender", "blahblahblahblah gender:male");
		Assert.assertEquals("male", criteria);

		criteria = CriteriaUtil.getCriteriaFromTokenizedKeyword("nonExistField", "firstName:xsa lastName:lefter gender:male");
		Assert.assertEquals("", criteria);

		criteria = CriteriaUtil.getCriteriaFromTokenizedKeyword("nonExistField", "Illegal-format-search-criteria");
		Assert.assertEquals("", criteria);

		criteria = CriteriaUtil.getCriteriaFromTokenizedKeyword("", "");
		Assert.assertEquals("", criteria);
	}
}
