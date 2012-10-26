package org.dynamicfinder.example.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FindResultDTO<E> {

	private static final Logger logger = LoggerFactory.getLogger(FindResultDTO.class);

	private final List<E> resultList;
	private  int[] pages;

	public FindResultDTO(final List<E> resultList, final Long count, final int resultSize) {
		this.resultList = resultList;
		final int arrayLength = count.intValue() / resultSize;
		final int modulo = (count.intValue() % resultSize) > 0 ? 1 : 0;
		this.pages = new int[arrayLength + modulo];

		for (int i = 1; i <= this.pages.length; i ++) 
			this.pages[i - 1] = i;

		logger.info("count={} array-length={} modulo={} pages={}", 
				new Object[] {count, arrayLength, modulo, this.pages});
	}

	public final List<E> getResultList() {
		return resultList;
	}

	public final int[] getPages() {
		return pages;
	}

}
