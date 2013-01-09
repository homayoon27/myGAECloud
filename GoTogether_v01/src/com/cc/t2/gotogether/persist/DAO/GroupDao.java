package com.cc.t2.gotogether.persist.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.cc.t2.gotogether.persist.GroupOb;
import com.cc.t2.gotogether.persist.PersonOb;

public class GroupDao {

	private GroupDao() {
	}

	public static void save(GroupOb bo) {
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			pm.makePersistent(bo);
		} finally {
			pm.close();
		}
	}

	public static List<GroupOb> getAll() {
		List<GroupOb> groups = new ArrayList<GroupOb>();

		PersistenceManager pm = PMF.getPersistenceManager();
		Extent<GroupOb> extent = pm.getExtent(GroupOb.class, false);
		for (GroupOb g : extent) {
			groups.add(g);
		}
		extent.closeAll();
		return groups;
	}

	@SuppressWarnings("unchecked")
	public static GroupOb getGroupByName(String name) {
		GroupOb g = null;

		PersistenceManager pm = PMF.getPersistenceManager();
		Query query = pm.newQuery(GroupOb.class,
				"name == nameParam order by createDate desc");
		query.declareParameters("String nameParam");

		List<GroupOb> results = (List<GroupOb>) query.execute(name);
		if (results.size() > 0) {
			g = results.get(0);
		}

		return g;
	}

	@SuppressWarnings("unchecked")
	public static boolean exists(String name) {
		PersistenceManager pm = PMF.getPersistenceManager();
		Query query = pm.newQuery(GroupOb.class, "name == nameParam");
		query.declareParameters("String nameParam");

		List<PersonOb> results = (List<PersonOb>) query.execute(name);
		return (results.size() > 0);
	}
}
