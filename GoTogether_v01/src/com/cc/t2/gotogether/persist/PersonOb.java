package com.cc.t2.gotogether.persist;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PersonOb  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7997359830829747868L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@PersistenceCapable
	@EmbeddedOnly
	public static class DataRec {
		@Persistent
		private String name;

		@Persistent
		private String email;

		@Persistent
		private String password;

		@Persistent
		private Long createDate;

		@Persistent
		boolean disabled;

		public DataRec(String name, String email, String password, Long createDate) {
			this.setName(name);
			this.setEmail(email);
			this.setPassword(password);
			this.setCreateDate(createDate);
			this.setDisabled(false);
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setCreateDate(Long createDate) {this.createDate = createDate;
		}

		public Long getCreateDate() {
			return createDate;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getEmail() {
			return email;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPassword() {
			return password;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		public boolean isDisabled() {
			return disabled;
		}

	}

	@Persistent
	@Embedded
	private DataRec info;

	@Persistent
	private List<MemberOb> members;

	@Persistent
	private List<AttendeeOb> attendees;

	public PersonOb(DataRec info) {
		this.setDataRec(info);
	}

	public Long getId() {
		return id;
	}

	public void setDataRec(DataRec info) {
		this.info = info;
	}

	public DataRec getDataRec() {
		return info;
	}

	public void setMembers(List<MemberOb> members) {
		this.members = members;
	}

	public List<MemberOb> getMembers() {
		return members;
	}

	public MemberOb join(GroupOb g) {
		MemberOb m = new MemberOb();
		m.setGroup(g);
		m.setPerson(this);
		m.setJoinDate(new Date().getTime());
		m.setBalance(0.0f);

		members.add(m);

		return m;
	}

	public AttendeeOb join(ActivityOb a) {
		AttendeeOb at = new AttendeeOb();
		at.setActivity(a);
		at.setPerson(this);

		attendees.add(at);

		return at;
	}

	public void setAttendees(List<AttendeeOb> attendees) {
		this.attendees = attendees;
	}

	public List<AttendeeOb> getAttendees() {
		return attendees;
	}

}
