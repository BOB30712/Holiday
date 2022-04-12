package com.example.entity;

import java.time.Duration;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;


import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "holiday")
public class Holiday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 修改內容:1.存入MySQL的日期資料要為日期+時間
	 		2.從資料庫取出的資料一樣為日期+時間
	 		
	 原先程式標記:
			@Column
			@Temporal(TemporalType.DATE)
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			@JsonFormat(pattern = "yyyy-MM-dd")
			private Date starttime;
	 */
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date starttime;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date endtime;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="employee_id2")
	@NotNull
	private Employee employee2;
	
	@ManyToOne
	@JoinColumn(name="boss_id")
	private Boss boss;
	
	@Column
	private String results;
	
	
	@Column
	private String type;
	
	@Column
	private String reason;

	public Integer getHour() {
		/*
		 修改原先計算兩個DATE之間的時間(HOUR)，並扣除包含假日的時間
		 ->計算時間差改用Duration，原方法計算假日天數並扣除
		 
		 待處理:工作時間應為07:00~17:00、且扣除員工休息時間&午餐時間
		       目前計算為24小時制
		 */
		Integer NationalHoliday=0;
		Date starttime2=starttime;
		Calendar calendar=Calendar.getInstance();
		
		calendar.setTime(starttime2);
		while(starttime2.getTime()<=endtime.getTime()) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			starttime2=calendar.getTime();
			 if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
					 calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				 NationalHoliday++;
			 }
		}
		
		// 使用Duration類別計算時間差，把原本date類別轉成localdate
		Duration d=Duration.between(starttime.toInstant().atZone(ZoneId.systemDefault()).toInstant(),
				endtime.toInstant().atZone(ZoneId.systemDefault()).toInstant());
		int ans=(int)d.toHours();
		
		return ans-(NationalHoliday*24);
	}
	
	
	
	public String getResults() {
		return results;
	}



	public void setResults(String results) {
		this.results = results;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Boss getBoss() {
		return boss;
	}


	public void setBoss(Boss boss) {
		this.boss = boss;
	
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Employee getEmployee2() {
		return employee2;
	}

	public void setEmployee2(Employee employee2) {
		if(employee2.getId()!=employee.getId()) {
			this.employee2 = employee2;
		}
	}
	
	
}
