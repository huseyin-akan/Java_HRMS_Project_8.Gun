package Husonia.HRMS.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Husonia.HRMS.business.abstracts.JobPositionService;
import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.entities.concretes.JobPosition;

@RestController
@RequestMapping("/api/jobPositions")
public class JobPositionsController {
	
	private JobPositionService jobPositionService;
		
	@Autowired
	public JobPositionsController(JobPositionService jobPositionService) {
		this.jobPositionService = jobPositionService;
	}

	@GetMapping("/getall")
	public DataResult<List<JobPosition>> getAll() {
		return jobPositionService.getAll();
	}
	
	@PostMapping("/add")
	public String add(JobPosition jobPosition) {
		
		this.jobPositionService.add(jobPosition);
		return "işlem başarılı";
	}
}
