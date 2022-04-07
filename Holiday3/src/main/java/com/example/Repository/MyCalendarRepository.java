package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.MyCalendar;

@Repository
public interface MyCalendarRepository extends JpaRepository<MyCalendar, Integer>{

}
