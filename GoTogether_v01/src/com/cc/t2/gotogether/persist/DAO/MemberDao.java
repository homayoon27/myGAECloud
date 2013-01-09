package com.cc.t2.gotogether.persist.DAO;

import javax.jdo.PersistenceManager;

import com.cc.t2.gotogether.persist.MemberOb;

public class MemberDao {
	private MemberDao() {
	}

	public static void save(MemberOb mo) {
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			pm.makePersistent(mo);
		} finally {
			pm.close();
		}
	}
}
