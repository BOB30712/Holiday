package com.example.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long>{
 
	@Transactional
	@Modifying
	@Query(value = "UPDATE Holiday SET results=?1 WHERE id=?2",nativeQuery = true)
	public void updateresult(@Param("results")String results,@Param("id")Long id);
	
	@Query(value="SELECT * FROM Holiday a WHERE a.boss_id=?1",nativeQuery = true)
	public List<Holiday> findByBossname(@Param("boss_id")Long bossid);
	
	@Query(value="SELECT * FROM Holiday a WHERE a.employee_id=?1",nativeQuery = true)
	public List<Holiday> findEmployeename(@Param("employee_id")Long employeeid);
}
