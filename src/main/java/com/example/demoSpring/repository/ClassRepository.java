package com.example.demoSpring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demoSpring.entities.Classes;

@Transactional
public interface ClassRepository extends JpaRepository<Classes, Long>{

}
