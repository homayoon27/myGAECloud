package com.cc.t2.gotogether.persist;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class GroupOb implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 304103080345122409L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@PersistenceCapable
	@EmbeddedOnly
	public static class DataRec {
		@Persistent
		private String name;

		@Persistent
		private Long createDate;

		@Persistent
		private String description;

		@Persistent
		private PersonOb admin;

		public DataRec(String name, String description, Long createDate) {
			this.setName(name);
			this.setCreateDate(createDate);
			this.setDescription(description);
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setCreateDate(Long createDate) {
			this.createDate = createDate;
		}

		public Long getCreateDate() {
			return createDate;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public void setAdmin(PersonOb admin) {
			this.admin = admin;
		}

		public PersonOb getAdmin() {
			return admin;
		}

	}

	@Persistent
	@Embedded
	private DataRec vo;

	@Persistent
	private List<MemberOb> members;

	public GroupOb(DataRec v) {
		this.setDataRec(v);
	}

	public Long getId() {
		return id;
	}

	public void setDataRec(DataRec vo) {
		this.vo = vo;
	}

	public DataRec getDataRec() {
		return vo;
	}

	public void setMembers(List<MemberOb> members) {
		this.members = members;
	}

	public List<MemberOb> getMembers() {
		return members;
	}

	public MemberOb addMember(PersonOb p) {
		return p.join(this);
	}

	public boolean hasPerson(PersonOb p) {
		for (MemberOb i : members) {
			if (i.getPerson().equals(p))
				return true;
		}
		return false;
	}

	public MemberOb getMember(PersonOb p) {
		for (MemberOb m : members) {
			if (m.getPerson().equals(p))
				return m;
		}
		return null;
	}
}
