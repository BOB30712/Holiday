package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Boss;


@Repository
public interface BossRepository extends JpaRepository<Boss, Long>{
	
	@Query("SELECT u FROM Boss u WHERE u.username = :username")
	public Boss getUserByUsername(@Param("username") String username);
}
