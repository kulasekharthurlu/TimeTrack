package com.app.timetrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.timetrack.ServiceImpl.EnagementService;
import com.app.timetrack.entity.Engagement;
import com.app.timetrack.util.Response;

@RestController
@RequestMapping("/engagement")
public class EngagementController {
	
	
	
	@Autowired
	private EnagementService engagementService;
	
	
	@PostMapping("/save")
	public Response<Engagement> saveEngagement(@RequestBody Engagement engagement){
		Engagement saveEngagement = engagementService.saveEngagement(engagement);
		if(saveEngagement!=null)
			return new Response<Engagement>(HttpStatus.OK, "Engagement Saved ", saveEngagement);
		else
			return new Response<Engagement>(HttpStatus.INTERNAL_SERVER_ERROR, " Engagement not saved ", saveEngagement);
			
	}
	
	@GetMapping("/all")
	public Response<List<Engagement>> getAllEnagagements(){
		List<Engagement> allEngagements = engagementService.getAllEngagements();
		if(!allEngagements.isEmpty())
			return new Response<List<Engagement>>(HttpStatus.OK, "OK", allEngagements);
		else
			return new Response<List<Engagement>>(HttpStatus.BAD_REQUEST, "Engagements are not presents", allEngagements);
		
	}

}
