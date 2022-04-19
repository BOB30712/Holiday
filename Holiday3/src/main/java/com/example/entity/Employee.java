package com.example.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private Integer userpassword;
	
	@Column
	private String Department;
	
	@Column
	private String Job;
	
	@ManyToOne
	@JoinColumn(name="boss_id")
	private Boss boss;
	
	@Column
	private Integer paidleave;
	
	
	//刪除員工資料時，連帶刪除休假資料
	@OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "employee")
	private Set<Holiday> Holidays;

	
	
	public Set<Holiday> getHolidays() {
		return Holidays;
	}

	public void setHolidays(Set<Holiday> holidays) {
		Holidays = holidays;
	}

	public Integer getPaidleave() {
		return paidleave;
	}

	public void setPaidleave(Integer paidleave) {
		this.paidleave = paidleave;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(Integer userpassword) {
		this.userpassword = userpassword;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getJob() {
		return Job;
	}

	public void setJob(String job) {
		Job = job;
	}


	
	
}
