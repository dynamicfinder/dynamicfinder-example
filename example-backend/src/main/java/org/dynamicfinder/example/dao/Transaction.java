package org.dynamicfinder.example.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper for {@link UserTransaction} and get a rid all confusing exception.
 */
public final class Transaction {

	private static final String JNDI_NAME = "java:/comp/UserTransaction";
	private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

	private UserTransaction delegate;

	public Transaction() {
		Context context;
		try {
			context = new InitialContext();
			delegate = (UserTransaction) context.lookup(JNDI_NAME);
		} catch (NamingException e) {
			logger.error("Couldn't find JNDI with name: {}. {}",JNDI_NAME, e.toString());
		}
	}

	public void begin() {
		try {
			delegate.begin();
		} catch (NotSupportedException e) {
			logger.error("Not supported operation: {}", e.toString());
		} catch (SystemException e) {
			logger.error(e.toString());
		}
	}

	public void commit() {
		try {
			delegate.commit();
		} catch (SecurityException e) {
			logger.error(e.toString());
		} catch (IllegalStateException e) {
			logger.error(e.toString());
		} catch (RollbackException e) {
			logger.error(e.toString());
		} catch (HeuristicMixedException e) {
			logger.error(e.toString());
		} catch (HeuristicRollbackException e) {
			logger.error(e.toString());
		} catch (SystemException e) {
			logger.error(e.toString());
		}
	}

	public void rollback() {
		try {
			delegate.rollback();
		} catch (IllegalStateException e) {
			logger.error(e.toString());
		} catch (SecurityException e) {
			logger.error(e.toString());
		} catch (SystemException e) {
			logger.error(e.toString());
		}
	}

	public void setRollbackOnly() {
		try {
			delegate.setRollbackOnly();
		} catch (IllegalStateException e) {
			logger.error(e.toString());
		} catch (SystemException e) {
			logger.error(e.toString());
		}
	}

	public int getStatus() {
		try {
			return delegate.getStatus();
		} catch (SystemException e) {
			logger.error(e.toString());
			throw new RuntimeException(e);
		}
	}

	public void setTransactionTimeout(int seconds) {
		try {
			delegate.setTransactionTimeout(seconds);
		} catch (SystemException e) {
			logger.error(e.toString());
		}
	}

}
