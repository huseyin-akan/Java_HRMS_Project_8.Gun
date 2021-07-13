package Husonia.HRMS.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Husonia.HRMS.business.abstracts.EmailService;
import Husonia.HRMS.business.abstracts.JobSeekerVerificationService;
import Husonia.HRMS.core.utilities.results.ErrorResult;
import Husonia.HRMS.core.utilities.results.Result;

@RestController
@RequestMapping("/api/jobSeekerVerifications")
public class JobSeekerVerificationsController {

	private EmailService emailService;
	private JobSeekerVerificationService jobSeekerVerificationService;
	
	@Autowired
	public JobSeekerVerificationsController(EmailService emailService,
			JobSeekerVerificationService jobSeekerVerificationService) {
		this.emailService = emailService;
		this.jobSeekerVerificationService = jobSeekerVerificationService;
	}
	
	@PostMapping("/verify")
	public Result verify(int userId) {
		if (this.emailService.verify("fake", "fake").isSuccess()) {
			return this.jobSeekerVerificationService.verify(userId);
		}
		return new ErrorResult("email doğrulama işlemi başarısız oldu!!!");
	}
}
