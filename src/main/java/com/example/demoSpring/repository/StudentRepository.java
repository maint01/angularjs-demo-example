package com.example.demoSpring.repository;

import java.util.List;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demoSpring.entities.Classes;
import com.example.demoSpring.entities.Student;

@Transactional
public interface StudentRepository extends JpaRepository<Student, Long>{
	List<Student> findByClasses(Classes classes);
	
	
	@Query(value="select s from Student s where lower(s.name) like lower(concat('%', :key, '%') )")
	List<Student> findByName(@Param("key") String key);
}
