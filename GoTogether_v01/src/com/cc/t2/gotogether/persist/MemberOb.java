package com.cc.t2.gotogether.persist;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class MemberOb  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8314151725948730776L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private GroupOb group;

	@Persistent
	private PersonOb person;

	@Persistent
	private Long joinDate;

	@Persistent
	private Float balance;

	@Persistent
	private boolean disabled;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setGroup(GroupOb group) {
		this.group = group;
	}

	public GroupOb getGroup() {
		return group;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Float getBalance() {
		return balance;
	}

	public void setJoinDate(Long joinDate) {
		this.joinDate = joinDate;
	}

	public Long getJoinDate() {
		return joinDate;
	}

	public void setPerson(PersonOb person) {
		this.person = person;
	}

	public PersonOb getPerson() {
		return person;
	}

	public Float Add(Float amount) {
		this.balance += amount;
		return this.balance;
	}

	public Float Reduce(Float amount) {
		this.balance -= amount;
		return this.balance;
	}

	public Float Deposit(Float amount) {
		return this.Add(amount);
	}

	public Float Expense(Float amount) {
		return this.Reduce(amount);
	}

	public Float Pay(Float amount) {
		return this.Add(amount);
	}

	public Float Receive(Float amount) {
		return this.Reduce(amount);
	}

	public Float Transfer(MemberOb other, Float amount) {
		other.Receive(amount);
		return this.Pay(amount);
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isDisabled() {
		return disabled;
	}

}
