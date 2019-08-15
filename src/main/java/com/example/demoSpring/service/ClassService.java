package com.example.demoSpring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpring.entities.Classes;
import com.example.demoSpring.repository.ClassRepository;

@Service
@Transactional
public class ClassService {
	@Autowired
	ClassRepository classRepository;
	
	public List<Classes> findAll(){
		return classRepository.findAll();
	}
}
