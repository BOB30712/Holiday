package com.example.Controller;

import java.util.ArrayList;
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
	
	@Autowired
	private HolidayRepository holidayrepo;
	
	@GetMapping(value = {"/test/{id}"}) 
	public List<CountCalendar> index(@PathVariable("id")Long id){
		List<CountCalendar>ans1=new ArrayList<CountCalendar>();
		List<Holiday> holiday=holidayrepo.findEmployeename(id);
			for(Holiday i:holiday) {
				CountCalendar c=new CountCalendar();
				c.setTitle(i.getReason());
				c.setStart(i.getStarttime());
				c.setEnd(i.getEndtime());
				ans1.add(c);
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
	
}
