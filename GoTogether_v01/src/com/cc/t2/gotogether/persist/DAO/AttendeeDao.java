package com.cc.t2.gotogether.persist.DAO;

import javax.jdo.PersistenceManager;
import com.cc.t2.gotogether.persist.AttendeeOb;

public class AttendeeDao {
	private AttendeeDao() {
	}

	public static void save(AttendeeOb bo) {
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			pm.makePersistent(bo);
		} finally {
			pm.close();
		}
	}
}
