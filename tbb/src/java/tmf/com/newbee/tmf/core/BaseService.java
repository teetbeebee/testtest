package com.newbee.tmf.core;

import com.ibatis.dao.client.Dao;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoTransaction;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 实现了DaoManager接口，子类可以方便地调用这些方法取的Dao对象，进行相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 *  
 * @version 1.0
 */
public class BaseService implements DaoManager {

	private DaoManager daoManager;

	public BaseService() {
		daoManager = DaoConfig.getDaoManager();
	}

	public BaseService(String sourcename) {
		daoManager = DaoConfig.getDaoManager(sourcename);
	}

	/**
	 * Gets a Dao instance for the requested interface type.
	 * 
	 * @param type
	 *            The interface or generic type for which an implementation
	 *            should be returned.
	 * @return The Dao implementation instance.
	 */
	public Dao getDao(Class type) {
		return daoManager.getDao(type);
	}

	/**
	 * Gets a Dao instance for the requested interface type registered under the
	 * context with the specified id.
	 * 
	 * @param iface
	 *            The interface or generic type for which an implementation
	 *            should be returned.
	 * @param contextId
	 *            The ID of the context under which to find the DAO
	 *            implementation (use for multiple interface defs).
	 * @return The Dao implementation instance.
	 */
	public Dao getDao(Class iface, String contextId) {
		return daoManager.getDao(iface, contextId);
	}

	/**
	 * Gets the transaction that the provided Dao is currently working under. If
	 * there is no current transaction in scope, one will be started.
	 * 
	 * @param dao
	 *            The Dao to find a transaction for.
	 * @return The Transaction under which the Dao provided is working under.
	 */
	public DaoTransaction getTransaction(Dao dao) {
		return daoManager.getTransaction(dao);
	}

	/**
	 * Starts a transaction scope managed by this DaoManager. If this method
	 * isn't called, then all DAO methods use "autocommit" semantics.
	 */
	public void startTransaction() {
		daoManager.startTransaction();
	}

	/**
	 * Commits all transactions currently started for all DAO contexts managed
	 * by this DaoManager.
	 */
	public void commitTransaction() {
		daoManager.commitTransaction();
	}

	/**
	 * Ends all transactions currently started for all DAO contexts managed by
	 * this DaoManager. If any transactions have not been successfully
	 * committed, then those remaining will be rolled back.
	 */
	public void endTransaction() {
		daoManager.endTransaction();
	}

}
