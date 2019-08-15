package com.example.demoSpring.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpring.entities.Classes;
import com.example.demoSpring.entities.Student;
import com.example.demoSpring.repository.ClassRepository;
import com.example.demoSpring.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class ClassRsService {

	private Logger log = org.slf4j.LoggerFactory.getLogger(Classes.class);

	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(value = "/class", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Classes>> findAll() {
		log.info("Load all class in database :v");
		return new ResponseEntity<List<Classes>>(classRepository.findAll(), HttpStatus.OK);
	}

}
