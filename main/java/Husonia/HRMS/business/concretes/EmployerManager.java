package Husonia.HRMS.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Husonia.HRMS.business.abstracts.EmailService;
import Husonia.HRMS.business.abstracts.EmployerService;
import Husonia.HRMS.business.constants.Messages;
import Husonia.HRMS.core.utilities.business.BusinessRules;
import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.ErrorResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.core.utilities.results.SuccessDataResult;
import Husonia.HRMS.core.utilities.results.SuccessResult;
import Husonia.HRMS.dataAccess.abstracts.EmployerDao;
import Husonia.HRMS.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{

private EmployerDao employerDao;
private EmailService emailService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, EmailService emailService) {
		super();
		this.employerDao = employerDao;
		this.emailService = emailService;
	}
	
	@Override
	public Result add(Employer employer) {
		
		Result result = BusinessRules.Run(				
				checkIfAllFieldsNotNull(employer),
				checkIfPasswordsMatch(employer),
				checkIfEmailExists(employer.getEmail())
		);
		
		//geçemediğin ilk iş kuralını dön:
		if (result != null) {
			return result;
		}
		
		
		//TODO : iş kuralları
		// 2. HRMS personeli onayı.
		
		this.employerDao.save(employer);
		
		this.emailService.sendVerification(employer);
		
		return new SuccessResult("İş veren kayıt işlemi başarılı. Email adresinize doğrulama linki gönderildi, tıklayarak doğrulama yapabilirsiniz.");
		
	}
	
	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll());
	}
	
	// tüm alanlar gelmiş mi kontrolü yapılır.
		public Result checkIfAllFieldsNotNull(Employer employer) {

			if (employer.getCompanyName() == null) {
				return new ErrorResult(Messages.CompanyNameCannotBeNull);
			}
			 else if (employer.getWebSite() == null) {
				return new ErrorResult(Messages.WebsiteCannotBeNull);
			}else if (employer.getWebSiteMail() == null) {
				return new ErrorResult(Messages.WebsiteMailCannotBeNull);
			} else if (employer.getPhoneNumber() == null) {
				return new ErrorResult(Messages.PhoneNumberCannotBeNull);
			} else if (employer.getEmail() == null) {
				return new ErrorResult(Messages.UserEmailCannotBeNull);
			} else if (employer.getPassword() == null) {
				return new ErrorResult(Messages.UserPasswordCannotBeNull);
			} else if (employer.getPasswordCheck() == null) {
				return new ErrorResult(Messages.UserPasswordCannotBeNull);
			} else if (employer.getUserName() == null) {
				return new ErrorResult(Messages.UserUserNameCannotBeNull);
			}
			return null;
		}
		
		
		// şifreler uyuşuyor mu kontrolü yapılır.
		public Result checkIfPasswordsMatch(Employer employer) {
			if (employer.getPassword().equals(employer.getPasswordCheck()) == false) {
				return new ErrorResult(Messages.PasswordDontMatch);
			}
			return null;
		}
		
		// Daha önce kayıtlı bir eposta varsa kayıt yapılamaz.
		public Result checkIfEmailExists(String email) {
			if(this.employerDao.getByEmail(email) != null) {
				return new ErrorResult(Messages.EmailAdressAlreadyExists);
			}
			return null;
		}

		

}
