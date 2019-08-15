package com.example.demoSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demoSpring.entities.Student;
import com.example.demoSpring.service.ClassService;
import com.example.demoSpring.service.StudentService;

@Controller
@RequestMapping("/form")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	ClassService classService;
	
	@RequestMapping(value="/student")
	public String initial(Model model, Model modelList, Model listClassModel){
		model.addAttribute("student", new Student());
		modelList.addAttribute("list", studentService.findAll());
		listClassModel.addAttribute("listClass", classService.findAll());
		return "student";
	}
	
	@RequestMapping(value="/addProcess")
	public String addProcess(@ModelAttribute Student student){
		studentService.addOrUpdate(student);
		return "redirect:/form/student";
	}
	
	@RequestMapping(value="/student/{id}")
	public String update(@PathVariable String id, Model model, Model listClassModel){
		Student student = studentService.findOne(Long.parseLong(id));
		listClassModel.addAttribute("listClass", classService.findAll());
		model.addAttribute("student", student);
		return "update";
	}
	
	@RequestMapping(value="/updateProcess")
	public String updateProcess(@ModelAttribute Student student){
		studentService.addOrUpdate(student);
		return "redirect:/form/student";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable String id){
		studentService.delete(Long.parseLong(id));
		return "redirect:/form/student";
	}
	
}
