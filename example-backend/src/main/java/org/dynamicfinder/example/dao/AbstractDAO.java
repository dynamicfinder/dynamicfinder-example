package org.dynamicfinder.example.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.dynamicfinder.QueryBuilder;
import org.dynamicfinder.Restriction;
import org.dynamicfinder.jpa.JpaQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDAO<E> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);
	private static int DEFAULT_RESULT_SIZE = 20;

	private Class<E> entityClass;

	private EntityManager entityManager;
	private Transaction transaction;

	public AbstractDAO(EntityManagerFactory emf, Class<E> entityClass) {
		this.entityClass = entityClass;
		this.entityManager = emf.createEntityManager();
		this.transaction = new Transaction();
	}

	public FindResultDTO<E> find(int page, List<Restriction> restrictions) {
		logger.debug("from={} restrictions={}", page, restrictions);

		this.transaction.begin();

		// We need a string of query. It's done by our simple QueryBuilder object.
		QueryBuilder queryBuilder = new JpaQueryBuilder(entityClass).
				where(restrictions);

		// What we really need is query string based on restriction defined in 
		// controller.
		final String queryString = queryBuilder.getQueryString();
		final String countQueryString = queryBuilder.getCountQueryString();

		// Use parsed queryString as plain JPQL to EntityManager#createQuery() method.
		final TypedQuery<E> query = this.entityManager.createQuery(queryString, this.entityClass);
		final TypedQuery<Long> countQuery = this.entityManager.createQuery(countQueryString, Long.class);

		// Now, JPA need to know about which parameter should be added to their 
		// Query object. Luckily, Finder4J provide a way for this easily:
		final Map<Integer, Restriction> actualRestriction = queryBuilder.getActualRestrictions();

		for (final Integer parameter : actualRestriction.keySet()) {
			query.setParameter(parameter, actualRestriction.get(parameter).getValue());
			countQuery.setParameter(parameter, actualRestriction.get(parameter).getValue());
		}

		final int firstResult = (page - 1) * DEFAULT_RESULT_SIZE;
		final int maxResult = DEFAULT_RESULT_SIZE;

		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);

		final List<E> result = query.getResultList();
		final Long count = countQuery.getSingleResult();

		this.transaction.commit();
		this.entityManager.clear();

		return new FindResultDTO<E>(result, count, DEFAULT_RESULT_SIZE);
	}
}
