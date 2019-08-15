package com.example.demoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpring.entities.StudentEntity;

public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long>{
}
