package Husonia.HRMS.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Husonia.HRMS.business.abstracts.JobSeekerService;
import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.entities.concretes.JobSeeker;


@RestController
@RequestMapping("/api/jobSeekers")
public class JobSeekersController {

	private JobSeekerService jobSeekerService;
	
	@Autowired
	public JobSeekersController(JobSeekerService jobSeekerService) {
		this.jobSeekerService = jobSeekerService;
	}

	@GetMapping("/getall")
	public DataResult<List<JobSeeker>> getAll() {
		return jobSeekerService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(JobSeeker jobSeeker) {
		
		return this.jobSeekerService.add(jobSeeker);
	}
}
