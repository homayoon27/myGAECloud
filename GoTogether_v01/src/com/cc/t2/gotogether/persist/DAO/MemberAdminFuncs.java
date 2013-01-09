package com.cc.t2.gotogether.persist.DAO;

import java.util.List;

import com.cc.t2.gotogether.persist.GroupOb;
import com.cc.t2.gotogether.persist.MemberOb;
import com.cc.t2.gotogether.persist.PersonOb;
import com.cc.t2.gotogether.persist.DAO.GroupDao;
import com.cc.t2.gotogether.persist.DAO.MemberDao;
import com.cc.t2.gotogether.persist.DAO.PersonDao;

public class MemberAdminFuncs {
	private MemberAdminFuncs() {
	}

	public static List<MemberOb> listMembersByGroup(String name) {
		GroupOb g = GroupDao.getGroupByName(name);
		if (null == g)
			return null;
		return g.getMembers();
	}

	// person management
	public static boolean createPerson(PersonOb.DataRec v) {
		if (PersonDao.exists(v.getName(), v.getEmail()))
			return false;

		PersonOb p = new PersonOb(v);
		PersonDao.save(p);

		return true;
	}

	public static boolean addPersonToGroup(String personName, String gName) {
		PersonOb p = PersonDao.getPersonByName(personName);
		if (null == p)
			return false;
		GroupOb g = GroupDao.getGroupByName(gName);
		if (null == g)
			return false;

		if (!g.hasPerson(p)) {
			MemberOb m = p.join(g);
			MemberDao.save(m);
			// person & group changes auto saved
			// PersonDao.save(p);
			// GroupDao.save(g);
		}

		return true;
	}

	public static boolean disableMember(String groupName, String personName,
			boolean disabled) {
		GroupOb g = GroupDao.getGroupByName(groupName);
		if (null == g)
			return false;
		PersonOb p = PersonDao.getPersonByName(personName);
		if (null == p)
			return false;
		MemberOb m = g.getMember(p);
		if (null == m)
			return false;
		m.setDisabled(disabled);
		MemberDao.save(m);
		return true;
	}

	public static boolean disablePerson(String personName, boolean disabled) {
		PersonOb p = PersonDao.getPersonByName(personName);
		if (null == p)
			return false;
		p.getDataRec().setDisabled(disabled);
		PersonDao.save(p);
		return true;
	}

	public static List<PersonOb> listPersons() {
		return PersonDao.getAll();
	}

	public static PersonOb findPersonByName(String personName) {
		return PersonDao.getPersonByName(personName);
	}

	public static List<MemberOb> listMembersByPerson(String name) {
		PersonOb p = PersonDao.getPersonByName(name);
		if (null == p)
			return null;
		return p.getMembers();
	}

}
