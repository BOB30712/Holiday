package com.example.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
測試calendar類別會不會在mysql中轉為datetime
結果--->會
@Entity
@Table(name = "test")
*/
public class Test {

	/*
	@Id
	private Integer id;
	
	@Column
	private Calendar test;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getTest() {
		return test;
	}

	public void setTest(Calendar test) {
		this.test = test;
	}
	*/
}
