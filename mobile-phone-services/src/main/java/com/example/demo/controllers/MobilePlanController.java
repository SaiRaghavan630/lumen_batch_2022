package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.MobilePlan;
import com.example.demo.services.MobilePlanService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/mobileplans")
@AllArgsConstructor
public class MobilePlanController {
	
	@Autowired
	private MobilePlanService servcie;
	
	@GetMapping
	public List<MobilePlan> getAll(){
		return this.servcie.findAll();
	}
	
	@GetMapping("/srch/{id}")
	public MobilePlan findById(@PathVariable("id") int id) {
		return this.servcie.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<MobilePlan> add(@RequestBody MobilePlan entity){
		MobilePlan addedObj=this.servcie.add(entity);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").
				buildAndExpand(entity.getPlanId()).toUri();
		return ResponseEntity.created(location).body(addedObj);
	}
	
	@PutMapping
	public ResponseEntity<MobilePlan> update(@RequestBody MobilePlan entity){
		MobilePlan addedObj=this.servcie.update(entity);
		return ResponseEntity.ok().body(null);
	}
	
	@DeleteMapping(path="{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id){
		MobilePlan deletedElement=this.servcie.remove(id);
		return ResponseEntity.status(HttpStatus.OK).body("deleted Element");
	}
	
	@GetMapping("/srch/plan/{planName}")
	public List<MobilePlan> findByPlanName(@PathVariable("planName") String planName) {
		return this.servcie.findByPlanName(planName);
	}
	
	@GetMapping("/srch/amount/{srchAmount}")
	public List<MobilePlan> getCostGrtThan(@PathVariable("srchAmount") double srchAmount){
		return this.servcie.getCostGrtThan(srchAmount);
	}
	
	
	@PatchMapping("/srch/{pname}/{newValue}")
	public ResponseEntity<String> updateValidity(@Param("newValue") String updateValidity,@Param("pname") String planName) {
		this.servcie.updateValidity(updateValidity, planName);
		return ResponseEntity.ok().body(null);
	}
}









