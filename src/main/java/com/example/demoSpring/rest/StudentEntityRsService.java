/**
 * 
 */
package com.example.demoSpring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpring.entities.StudentEntity;
import com.example.demoSpring.repository.StudentEntityRepository;

/**
 * @author Nguyen
 *
 */
@RestController
@RequestMapping("/api")
public class StudentEntityRsService {

	@Autowired
	StudentEntityRepository studentRepository;

	@RequestMapping(value = "/studentEntity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentEntity>> findAll() {
		List<StudentEntity> studentList = studentRepository.findAll();
		if (studentList.isEmpty()) {
			return new ResponseEntity<List<StudentEntity>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(studentList, HttpStatus.OK);
		}
	}
}
