package com.example.demoSpring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoSpring.entities.Student;
import com.example.demoSpring.repository.StudentRepository;

import groovy.util.logging.Log4j2.Log4j2LoggingStrategy;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	
	public List<Student> findAll(){
		return studentRepository.findAll();
	}
	
	public Student findOne(Long id){
		return studentRepository.findOne(id);
	}
	
	public void addOrUpdate(Student student){
		studentRepository.save(student);
	}
	
	public void delete(Long id){
		studentRepository.delete(id);
	}
	
}
