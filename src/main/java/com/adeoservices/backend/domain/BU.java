package com.adeoservices.backend.domain;

import javax.persistence.*;

@Entity
@Table(name="bu")
public class BU {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Basic
	@Column
	public String name;
}
