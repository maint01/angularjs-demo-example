package com.example.demoSpring.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the classes database table.
 * 
 */
@Entity
@Table(name="CLASSES")
@NamedQuery(name="ClassEntity.findAll", query="SELECT c FROM ClassEntity c")
public class ClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}