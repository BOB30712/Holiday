package com.example.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.HolidayRepository;
import com.example.Repository.MyCalendarRepository;
import com.example.entity.CountCalendar;
import com.example.entity.Holiday;
import com.example.entity.MyCalendar;

@RestController
@RequestMapping("/boss/FullCalender")
public class MyCalendarController {
	
	@Autowired
	private MyCalendarRepository calendarRepo;
	
	private Integer count=0;
	
	private Date date=new Date();
	
	@Autowired
	private HolidayRepository holidayrepo;
	
	@GetMapping(value = {"/test/{id}"}) 
	public List<CountCalendar> index(@PathVariable("id")Long id){
		List<CountCalendar>ans1=new ArrayList<CountCalendar>();
		List<Holiday> holiday=holidayrepo.findEmployeename(id);
			for(Holiday i:holiday) {
				if(i.getResults()!=null) {
					if(i.getResults().equals("同意")) {
						CountCalendar c=new CountCalendar();
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
						c.setTitle(sdf2.format(i.getStarttime())+" - "+
								sdf2.format(i.getEndtime())+" 原因:"+i.getReason());
						c.setStart(sdf1.format(i.getStarttime()));
						c.setEnd(sdf1.format(i.getEndtime()));
						ans1.add(c);
					}
				}
			}
		return ans1;
		/*第一次測試
		List<Holiday> holiday=holidayrepo.findAll();
		List<Map<String, String>>ans1=new ArrayList<Map<String,String>>();
		Map<String,String> ans2=new HashMap<String, String>();
		Map<String,String> ans3=new HashMap<String, String>();
		for(Holiday i:holiday) {
			ans2.put("title", i.getReason());
			ans2.put("start", "2022-04-05");
			ans2.put("end", "2022-05-10");
			ans1.add(ans2);
			ans2.clear();
		}
		 ans2.put("title", "Event1");
		 ans2.put("start", "2022-04-05");
		 ans2.put("end", "2022-05-10");
		 ans1.add(ans2);
		 ans3.put("title", "Event2");
		 ans3.put("start", "2022-04-01");
		 ans3.put("end", "2022-04-10");
		 ans1.add(ans3);
		return ans1;
		*/
	}
	
	@GetMapping(value = {"/JQuery"}) 
	public String Jquery() {
		return "JQuery測試成功";
	}
	
	@GetMapping(value = {"/JQuery2"}) 
	public Integer Jquery2() {
		count++;
		return count;
	}
	
	@GetMapping(value = {"/JQuery3"}) 
	public List<String> Jquery3() {
		List<String> list=new ArrayList<>();
		list.add(Integer.toString(count));
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		list.add(dtf.format(date));
		return list;
	}
	
	/*
	 * 	網站上JQuery程式碼
	@GetMapping(value = {"/JQuery"}) 
	public List<String> Jquery() {
		List<String> time=new ArrayList<>();
		time.add(Integer.toString(count));
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		time.add(dtf.format(date));
		count++;
		return time;
	}
	 */
	
	
}
