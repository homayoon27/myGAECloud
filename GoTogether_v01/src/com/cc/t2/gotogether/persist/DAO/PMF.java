package com.cc.t2.gotogether.persist.DAO;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

// Singletone Factory
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory gets() {
        return pmfInstance;
    }
    
	public static PersistenceManager getPersistenceManager() {
		return gets().getPersistenceManager();
	}    
}
