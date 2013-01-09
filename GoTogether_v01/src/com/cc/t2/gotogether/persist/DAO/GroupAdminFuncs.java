package com.cc.t2.gotogether.persist.DAO;

import java.util.List;

import com.cc.t2.gotogether.persist.GroupOb;
import com.cc.t2.gotogether.persist.DAO.GroupDao;

public class GroupAdminFuncs {

	private GroupAdminFuncs() {
	}

	// group management
	public static boolean createGroup(GroupOb.DataRec v) {
		if (GroupDao.exists(v.getName()))
			return false;
		GroupOb g = new GroupOb(v);
		GroupDao.save(g);
		return true;
	}

	// TODO:
	public static boolean disableGroup(String groupName) {
		return false;
	}

	// TODO:
	public static boolean modifyGroup(String groupName, GroupOb.DataRec v) {
		return false;
	}

	public static List<GroupOb> listGroups() {
		return GroupDao.getAll();
	}

	public static GroupOb getGroupByName(String groupName) {
		return GroupDao.getGroupByName(groupName);
	}
}
