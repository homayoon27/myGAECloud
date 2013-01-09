package com.cc.t2.gotogether.persist;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AttendeeOb  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7286674862808323749L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private ActivityOb activity;

	@Persistent
	private PersonOb person;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setActivity(ActivityOb activity) {
		this.activity = activity;
	}

	public ActivityOb getActivity() {
		return activity;
	}
	public void setPerson(PersonOb p){
		this.person = p;
	}
	
	public PersonOb getPerson() {
		return person;
	}

}
