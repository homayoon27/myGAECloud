package com.cc.t2.gotogether.persist.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.cc.t2.gotogether.persist.ActivityOb;
import com.cc.t2.gotogether.persist.GroupOb;

public class ActivityDao {

	private ActivityDao() {
	}

	public static void save(ActivityOb ao) {
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			pm.makePersistent(ao);
		} finally {
			pm.close();
		}
	}

	public static List<ActivityOb> getAll() {
		List<ActivityOb> events = new ArrayList<ActivityOb>();

		PersistenceManager pm = PMF.getPersistenceManager();
		Extent<ActivityOb> extent = pm.getExtent(ActivityOb.class, false);
		for (ActivityOb g : extent) {
			events.add(g);
		}
		extent.closeAll();
		return events;
	}

	@SuppressWarnings("unchecked")
	public static List<ActivityOb> getGroupActivities(GroupOb g, long from,
			long to) {
		PersistenceManager pm = PMF.getPersistenceManager();
		Query query = pm.newQuery(ActivityOb.class,
				"group == groupParam order by happenDate desc");
		query.declareParameters("Long groupParam");
		query.setRange(from, to);

		List<ActivityOb> results = (List<ActivityOb>) query.execute(g.getId());
		return results;
	}

}
