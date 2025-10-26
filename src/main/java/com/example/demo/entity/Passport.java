package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "passport")
public class Passport {

	@Id

	private Long id;

	@Column(name = "passportNum", nullable = false)
	private String passportNum;

	@Column(name = "issueDate")
	private LocalDate issueDate;

	@Column(name = "expDate")
	private LocalDate expDate;

	@CreationTimestamp
	@Column(name = "createdDate", updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "updatedTime")
	private LocalDateTime updatedTime;

	@OneToOne(mappedBy = "passport")
	@JsonBackReference
	@ToString.Exclude
	private Person person;

}
