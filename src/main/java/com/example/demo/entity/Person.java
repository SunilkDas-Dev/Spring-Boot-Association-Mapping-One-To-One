package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "person")
public class Person {

	@Id
	private Long id;

	//@NotBlank(message = "Name Can't be Blank")
	@Column(nullable = false)
	private String name;

//	@NotBlank(message = " Gender Can't be Blank")
	@Column(nullable = false)
	private String gender;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "passport_id")
	@JsonManagedReference
	@ToString.Exclude
	private Passport passport;

}
