package Husonia.HRMS.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Husonia.HRMS.business.abstracts.EmailService;
import Husonia.HRMS.business.abstracts.EmployerVerificationService;
import Husonia.HRMS.core.utilities.results.ErrorResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.entities.concretes.SystemPersonnel;

@RestController
@RequestMapping("/api/employerVerifications")
public class EmployerVerificationsController {
	
	private EmailService emailService;
	private EmployerVerificationService employerVerificationService;
	
	@Autowired
	public EmployerVerificationsController(EmailService emailService,
			EmployerVerificationService employerVerificationService) {
		this.emailService = emailService;
		this.employerVerificationService = employerVerificationService;
	}
	
	@PostMapping("/verifyByEmail")
	public Result verifyByEmail(int userId) {
		if (this.emailService.verify("fake", "fake").isSuccess()) {
			return this.employerVerificationService.verifyByEmail(userId);
		}
		return new ErrorResult("email doğrulama işlemi başarısız oldu!!!");
	}
	
	@PostMapping("/verifyByAdmin")
	public Result verifyByAdmin(int userId, SystemPersonnel admin) {
		
		//TODO: yetkilendirme kontrolü yapılmalı. adminin onaylama yetkisi var mı?
		
		//aşağıda sadece husokanus kullanıcı adına yetki vermiş olduk. Ama authorization sistemi kurulunca burası değişmeli.
		if (admin.getUserName().equals("husokanus") ) {
			return this.employerVerificationService.verifyByAdminApproval(userId);
		}
			return new ErrorResult("email doğrulama işlemi için yetkiniz yok!!!");
		
		
	}
}
