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
public class ActivityOb implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7709198008163975480L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@PersistenceCapable
	@EmbeddedOnly
	public static class DataRec {
		@Persistent
		private String name;

		@Persistent
		private Long happenDate;

		@Persistent
		private String location;

		@Persistent
		private String remark;

		@Persistent
		private Float payment;

		public DataRec(String name, Long happenDate, String location, Float payment,
				String remark) {
			this.setName(name);
			this.setHappenDate(happenDate);
			this.setLocation(location);
			this.setPayment(payment);
			this.setRemark(remark);
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setHappenDate(Long happenDate) {
			this.happenDate = happenDate;
		}

		public Long getHappenDate() {
			return happenDate;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getLocation() {
			return location;
		}

		public void setPayment(Float payment) {
			this.payment = payment;
		}

		public Float getPayment() {
			return payment;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getRemark() {
			return remark;
		}

	}

	@Persistent
	@Embedded
	private DataRec info;

	@Persistent
	private GroupOb group;

	@Persistent
	private PersonOb payer;

	@Persistent
	private List<AttendeeOb> attendees;

	public ActivityOb(DataRec info) {
		this.setDataRec(info);
	}

	public Long getId() {
		return this.id;
	}

	public void setDataRec(DataRec info) {
		this.info = info;
	}

	public DataRec getDataRec() {
		return info;
	}

	public void setGroup(GroupOb group) {
		this.group = group;
	}

	public GroupOb getGroup() {
		return group;
	}

	public void setPayer(PersonOb payer) {
		this.payer = payer;
	}

	public PersonOb getPayer() {
		return payer;
	}

	public AttendeeOb addAttendee(PersonOb p) {
		AttendeeOb at = p.join(this);
		this.attendees.add(at);
		return at;
	}

	public void setAttendees(List<AttendeeOb> attendees) {
		this.attendees = attendees;
	}

	public List<AttendeeOb> getAttendees() {
		return attendees;
	}
}
