package com.example.demoSpring.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demoSpring.entities.CustomerInfo;
import com.example.demoSpring.entities.DownloadFile;
import com.example.demoSpring.entities.Student;
import com.example.demoSpring.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentRsService {
	
	public static final String FILE_PATH = "C://Users/Nguyen/Desktop/batch_template.xlsx";
	
	private Logger log = LoggerFactory.getLogger(StudentRsService.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * @return
	 */
	@RequestMapping(value="/student",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> findAll(){
		log.info("Get all student from database");
		List<Student> studentList = studentRepository.findAll();
		return new ResponseEntity<>(studentList, HttpStatus.OK);
	}
	
	
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/student/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> findOne(@PathVariable Long id){
		log.info("Get student by id: {}", id);
		Student student = studentRepository.findOne(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	/**
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/student",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> save(@RequestBody Student student){
		log.info("Insert student to database");
		studentRepository.save(student);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/student",
			method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@RequestBody Student student){
		log.info("Update student from database");
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo
			.accountID("0122255")
			.userID("549464646")
			.customerName("NGUYEN THE ANH")
			.email("gdja@gmail.com")
			.phoneNumber("0122454494")
			.functionImpl("Quen mat khau")
			.status("FAIL")
			.datePerform(LocalDate.now());
		
		RestTemplate restTemplate = new RestTemplate(); 
		HttpEntity<CustomerInfo> requestEntity = new HttpEntity<CustomerInfo>(customerInfo);
		ResponseEntity<CustomerInfo> response =	restTemplate.postForEntity("http://localhost:9000/api/customer-infos", requestEntity, CustomerInfo.class);
		
		studentRepository.save(student);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/student/{id}",
			method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		log.info("Delete student from database by id = {}", id);
		Student student = studentRepository.findOne(id);
		if(student == null){
			log.info("Student dose not exist or deleted");
			return ResponseEntity.badRequest().build();
		}else{
			studentRepository.delete(student);
			return ResponseEntity.ok().build();
		}
	}
	
	/**
	 * @param key
	 * @return
	 */
	@RequestMapping(value="/student/search/{key}",
			method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> findByName(@PathVariable String key){
		log.info("Get student from database by name: {}", key);
		List<Student> studentList = studentRepository.findByName(key);
		return new ResponseEntity<>(studentList, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    public @ResponseBody void download(HttpServletRequest request, HttpServletResponse response) {
		log.info("Write response...");
        File file = new File (FILE_PATH);
        try {
			InputStream inputStream = new FileInputStream(file);
			
			response.setContentType(MediaType.TEXT_PLAIN_VALUE);
			
			response.addHeader("Content-disposition", String.format("attchment;file=\"%s\"", FILE_PATH));
			response.setHeader("Content-Length", String.valueOf(file.length()));
			
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			log.error("File not found.");
		}
    }

    
    @RequestMapping(value = "/batch_template.xlsx",
            method = RequestMethod.GET)
    public ResponseEntity<Resource> getBatchTemplate() throws URISyntaxException, IOException {
        log.info("Download batch template");
        File file = new File(FILE_PATH);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        java.nio.file.Path path = java.nio.file.Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);

    }
    
    @RequestMapping(value="/getFile", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DownloadFile> getFile(HttpServletResponse response){
    	Path path = Paths.get(this.FILE_PATH);
    	DownloadFile downloadFile = new DownloadFile();
    	String rsBase64 = null;
    	byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(path);
			String contentType = Files.probeContentType(path);
			String name = FILE_PATH.substring(FILE_PATH.lastIndexOf("/") + 1, FILE_PATH.length());
			//rsBase64 = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(bytes);
			//response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			downloadFile.setId(1);
			downloadFile.setName(name);
			downloadFile.setBytes(bytes);
			downloadFile.setContentType(contentType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	return new ResponseEntity<>(downloadFile, HttpStatus.OK);
    }
    
}
