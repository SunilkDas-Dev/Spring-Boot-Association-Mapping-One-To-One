package com.example.demo.controller;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constantMessage.ConstantMessage;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepo;
import com.example.demo.responseMessage.ResponseMessage;
import com.example.demo.service.ServiceInterface;

@RestController
public class PersonController {

	@Autowired
	ServiceInterface servIntr;
	@Autowired
	PersonRepo personRepo;

	@PostMapping(value = "/save")
	public ResponseEntity<ResponseMessage> savePerson(@RequestBody Person person) {
		System.out.println("coming");

		try {
			Person savedPerson = servIntr.savePersonData(person); // ✅ Expect Person, not String

			if (savedPerson == null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_FORBIDDEN, ConstantMessage.FAILED,
						"Data insertion failed"));
			}

			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, ConstantMessage.SUCCESS,
					"Data inserted successfully", savedPerson));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, ConstantMessage.FAILED,
					"Error occurred while inserting data  Recheck where you enter blank "));
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<ResponseMessage> viewAll() {
		try {
			List<Person> all = servIntr.getAll();
			System.out.println("ListOfPerson:" + all);
			if (all.isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CONFLICT, ConstantMessage.FAILURE,
						" No Data In The Table "));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_ACCEPTED, ConstantMessage.SUCCESS,
						" Data Fetch Successfully", all));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, ConstantMessage.FAILURE,
					" Error Found during fetching the data"));
		}
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseMessage> getById(@PathVariable Long id) {
		try {
			Optional<Person> byId = personRepo.findById(id);
			if (byId.isPresent()) {
				Optional<Person> byId2 = servIntr.getById(id);
				if (byId2.isPresent()) {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, ConstantMessage.SUCCESS,
							" Data fetch Successfully", byId2));

				}
			}
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY, ConstantMessage.FAILURE,
					" Error occures during fetching the data "));

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, ConstantMessage.FAILED,
					" Internal Server Error"));
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateById(@PathVariable Long id , @RequestBody Person person) {
		try {
			Optional<Person> byId = personRepo.findById(id);
			if (byId.isPresent()) {

				Object updateById = servIntr.updateById(person);
				if (updateById != null) {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, ConstantMessage.SUCCESS,
							" Data Updated Successfully", updateById));
				}

			}
			return ResponseEntity.ok(
					new ResponseMessage(HttpURLConnection.HTTP_CONFLICT, ConstantMessage.FAILED, " Updatation Failed"));

		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, ConstantMessage.FAILURE,
					" Internal Server Error"));
		}

	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseMessage> deleteById(@PathVariable Long id){
		try {
		Optional<Person> byId = personRepo.findById(id);
		if(byId.isPresent()) {
			servIntr.deleteById(id);
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, ConstantMessage.SUCCESS, " Data deleted Successfully"));
		}
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_NOT_FOUND,ConstantMessage.FAILED," Inserted id not found"));
		
	}
		catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,ConstantMessage.FAILURE," Error occures"));
		}
	}
}
