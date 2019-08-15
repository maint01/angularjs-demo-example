package com.example.demoSpring.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.annotation.Transient;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(name="student")
@NamedQuery(name="StudentEntity.findAll", query="SELECT s FROM StudentEntity s")
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String address;

	private String name;

	//bi-directional many-to-one association to ClassEntity
	@ManyToOne
	@JoinColumn(name="classes_id")
	@Transient
	private ClassEntity class1;


	public StudentEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassEntity getClass1() {
		return this.class1;
	}

	public void setClass1(ClassEntity class1) {
		this.class1 = class1;
	}


}