package Husonia.HRMS.api.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Husonia.HRMS.business.abstracts.JobAdService;
import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.entities.concretes.JobAd;

@RestController
@RequestMapping("/api/jobAds")
public class JobAdsController {
	private JobAdService jobAdService;

	public JobAdsController(JobAdService jobAdService) {
		super();
		this.jobAdService = jobAdService;
	}
	
	@PostMapping("/add")
	public Result add(JobAd jobAd) {
		return this.jobAdService.add(jobAd);
	}
	
	@GetMapping("/getActiveAds")
	public DataResult<List<JobAd>> getActiveAds() {
		return this.jobAdService.getActiveAds(true);
	}
	
	@GetMapping("/getActiveAdsAfter")
	public DataResult<List<JobAd>> getActiveAdsAfter(@RequestParam("publicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date publicationDate) {
				 
		return this.jobAdService.getAdsAfter(publicationDate);
	}
	
	@GetMapping("/getActiveAdsByCompanyName")
	public DataResult<List<JobAd>> findAllActiveJobAdsByCompanyName(@RequestParam("companyName") String companyName) {
				 
		return this.jobAdService.findAllActiveJobAdsByCompanyName(companyName);
	}
	
	@GetMapping("/updateJobAdIsActive")
	public Result updateJobadIsActive(int jobAdId, boolean isActive) {
				 
		return this.jobAdService.updateJobadIsActive(jobAdId, isActive);
	}
}
