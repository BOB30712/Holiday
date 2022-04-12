package com.example.entity;

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
		 	Long time=endtime.getTime()-starttime.getTime();
			TimeUnit tt=TimeUnit.HOURS;
			Long ans=tt.convert(time,TimeUnit.MILLISECONDS);
		
			return ans;
		
		 */
		Integer ans=0;
		Date starttime2=starttime;
		Calendar calendar=Calendar.getInstance();
		
		calendar.setTime(starttime2);
		while(starttime2.getTime()<=endtime.getTime()) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			starttime2=calendar.getTime();
			 if(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && 
					 calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				 ans++;
			 }
		}
		
		return ans*24;
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
