package com.cc.t2.gotogether.persist.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.cc.t2.gotogether.persist.PersonOb;

public class PersonDao {

	private PersonDao() {
	}

	public static void save(PersonOb bo) {
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			pm.makePersistent(bo);
		} finally {
			pm.close();
		}
	}

	public static List<PersonOb> getAll() {
		List<PersonOb> groups = new ArrayList<PersonOb>();

		PersistenceManager pm = PMF.getPersistenceManager();
		Extent<PersonOb> extent = pm.getExtent(PersonOb.class, false);
		for (PersonOb g : extent) {
			groups.add(g);
		}
		extent.closeAll();
		return groups;
	}

	@SuppressWarnings("unchecked")
	public static PersonOb getPersonByName(String name) {
		PersonOb g = null;

		PersistenceManager pm = PMF.getPersistenceManager();
		Query query = pm.newQuery(PersonOb.class,
				"name == nameParam order by createDate desc");
		query.declareParameters("String nameParam");

		List<PersonOb> results = (List<PersonOb>) query.execute(name);
		if (results.size() > 0) {
			g = results.get(0);
		}

		return g;
	}

	@SuppressWarnings("unchecked")
	public static PersonOb getPersonByEmail(String email) {
		PersonOb g = null;

		PersistenceManager pm = PMF.getPersistenceManager();
		Query query = pm.newQuery(PersonOb.class,
				"email == emailParam order by createDate desc");
		query.declareParameters("String emailParam");

		List<PersonOb> results = (List<PersonOb>) query.execute(email);
		if (results.size() > 0) {
			g = results.get(0);
		}

		return g;
	}

	@SuppressWarnings("unchecked")
	public static boolean exists(String name, String email) {
		PersistenceManager pm = PMF.getPersistenceManager();
		Query query = pm.newQuery(PersonOb.class,
				"name == nameParam || email == emailParam");
		query.declareParameters("String nameParam");
		query.declareParameters("String emailParam");

		List<PersonOb> results = (List<PersonOb>) query.execute(name, email);
		return (results.size() > 0);
	}
}
