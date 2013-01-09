package com.cc.t2.gotogether.persist.DAO;

import java.util.ArrayList;
import java.util.List;

import com.cc.t2.gotogether.persist.ActivityOb;
import com.cc.t2.gotogether.persist.AttendeeOb;
import com.cc.t2.gotogether.persist.GroupOb;
import com.cc.t2.gotogether.persist.MemberOb;
import com.cc.t2.gotogether.persist.PersonOb;
import com.cc.t2.gotogether.persist.DAO.ActivityDao;
import com.cc.t2.gotogether.persist.DAO.AttendeeDao;
import com.cc.t2.gotogether.persist.DAO.GroupDao;
import com.cc.t2.gotogether.persist.DAO.MemberDao;
import com.cc.t2.gotogether.persist.DAO.PersonDao;

public class ActivityFuncs {
	private ActivityFuncs() {
	}

	// Activities
	public static List<ActivityOb> listAllActivities() {
		return ActivityDao.getAll();
	}

	public static List<ActivityOb> listActivitiesByGroup(String groupName,
			long from, long to) {
		GroupOb g = GroupDao.getGroupByName(groupName);
		return ActivityDao.getGroupActivities(g, from, to);
	}

	public static boolean newActivity(String groupName, List<String> attendees,
			String payer, ActivityOb.DataRec adr) {
		GroupOb g = null;
		PersonOb p = null;
		MemberOb m = null;
		List<MemberOb> ats = new ArrayList<MemberOb>();
		ActivityOb a = null;
		AttendeeOb at = null;

		g = GroupDao.getGroupByName(groupName);
		if (null == g)
			return false;

		for (String s : attendees) {
			p = PersonDao.getPersonByName(s);
			if (null == p)
				return false;
			m = g.getMember(p);
			if (null == m)
				return false;
			ats.add(m);
		}

		p = PersonDao.getPersonByName(payer);
		if (null == p)
			return false;
		m = g.getMember(p);
		if (null == m)
			return false;

		a = new ActivityOb(adr);
		a.setGroup(g);
		a.setPayer(p);
		ActivityDao.save(a);

		m.Pay(adr.getPayment());
		MemberDao.save(m);

		Float averageExp = adr.getPayment() / ats.size();
		for (MemberOb i : ats) {
			at = new AttendeeOb();
			at.setActivity(a);
			at.setPerson(i.getPerson());
			AttendeeDao.save(at);

			i.Expense(averageExp);
			MemberDao.save(i);
		}

		return false;
	}

	// TODO:
	public static boolean deleteActivity(Long id) {
		return false;
	}

	// TODO:
	public static boolean modifyActivity(Long id) {
		return false;
	}

	// Finance
	public static boolean TransferCash(String groupName, String personA,
			String personB, Float amount) {
		GroupOb g = GroupDao.getGroupByName(groupName);
		if (null == g)
			return false;
		PersonOb pA = PersonDao.getPersonByName(personA);
		if (null == pA)
			return false;
		PersonOb pB = PersonDao.getPersonByName(personB);
		if (null == pB)
			return false;

		MemberOb mA = g.getMember(pA);
		MemberOb mB = g.getMember(pB);
		if (null == mA || null == mB)
			return false;

		mA.Transfer(mB, amount);
		MemberDao.save(mA);
		MemberDao.save(mB);

		return true;
	}

}
