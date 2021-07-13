package Husonia.HRMS.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Husonia.HRMS.business.abstracts.EmailService;
import Husonia.HRMS.business.abstracts.JobSeekerService;
import Husonia.HRMS.business.abstracts.UserCheckService;
import Husonia.HRMS.business.constants.Messages;
import Husonia.HRMS.core.utilities.business.BusinessRules;
import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.ErrorResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.core.utilities.results.SuccessDataResult;
import Husonia.HRMS.core.utilities.results.SuccessResult;
import Husonia.HRMS.dataAccess.abstracts.JobSeekerDao;
import Husonia.HRMS.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

	private JobSeekerDao jobSeekerDao;
	private UserCheckService userCheckService;
	private EmailService emailService;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao, UserCheckService userCheckService, EmailService emailService) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.userCheckService = userCheckService;
		this.emailService = emailService;
	}

	@Override
	public Result add(JobSeeker jobSeeker) {

		// TODO: İş kuralları

		Result result = BusinessRules.Run(
				checkIfAllFieldsNotNull(jobSeeker), 
				checkIfPasswordsMatch(jobSeeker),
				checkIfUserReal(jobSeeker),
				checkIfNationalityNoExists(jobSeeker.getNationalityNo()),
				checkIfUserNameExists(jobSeeker.getUserName())
				);
		
		//geçemediğin ilk iş kuralını dön:
		if (result != null) {
			return result;
		}
		
		//tüm iş kuralları sağlandıysa kayıt işlemi yapılır:
		this.jobSeekerDao.save(jobSeeker);
		
		//kullanıcıya doğrulama linki gönderilir.
		this.emailService.sendVerification(jobSeeker);
		
		return new SuccessResult(Messages.AddingSuccessful);

	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {

		return new SuccessDataResult<List<JobSeeker>>(jobSeekerDao.findAll(), "Çalışanlar başarıyla getirildi.");
	}

	// tüm alanlar gelmiş mi kontrolü yapılır.
	public Result checkIfAllFieldsNotNull(JobSeeker jobSeeker) {

		if (jobSeeker.getFirstName() == null) {
			return new ErrorResult(Messages.UserNameCannotBeNull);
		} else if (jobSeeker.getLastName() == null) {
			return new ErrorResult(Messages.UserLastNameCannotBeNull);
		} else if (jobSeeker.getNationalityNo() == null) {
			return new ErrorResult(Messages.UserNationalityNoCannotBeNull);
		} else if (jobSeeker.getYearOfBirth() == 0) {
			return new ErrorResult(Messages.UserYearOfBirthCannotBeNull);
		} else if (jobSeeker.getEmail() == null) {
			return new ErrorResult(Messages.UserEmailCannotBeNull);
		} else if (jobSeeker.getPassword() == null) {
			return new ErrorResult(Messages.UserPasswordCannotBeNull);
		} else if (jobSeeker.getPasswordCheck() == null) {
			return new ErrorResult(Messages.UserPasswordCannotBeNull);
		} else if (jobSeeker.getUserName() == null) {
			return new ErrorResult(Messages.UserUserNameCannotBeNull);
		}
		return null;
	}

	// şifreler uyuşuyor mu kontrolü yapılır.
	public Result checkIfPasswordsMatch(JobSeeker jobSeeker) {
		if (jobSeeker.getPassword().equals(jobSeeker.getPasswordCheck()) == false) {
			return new ErrorResult(Messages.PasswordDontMatch);
		}
		return null;
	}

	// mernis kontrolü yapılır
	public Result checkIfUserReal(JobSeeker jobSeeker) {
		return this.userCheckService.checkIfUserReal(jobSeeker);
	}

	// Daha önce kayıtlı bir TC varsa kayıt yapılamaz.
	public Result checkIfNationalityNoExists(String nationalityNo) {
		if(this.jobSeekerDao.getByNationalityNo(nationalityNo) != null) {
			return new ErrorResult(Messages.NationalityNoAlreadyExists);
		}
		return null;
	}
	

	// Daha önce kayıtlı bir eposta varsa kayıt yapılamaz.
	public Result checkIfEmailExists(String email) {
		if(this.jobSeekerDao.getByEmail(email) != null) {
			return new ErrorResult(Messages.EmailAdressAlreadyExists);
		}
		return null;
	}

	// Daha önce kayıtlı bir kullanıcı adı varsa kayıt yapılamaz.
	public Result checkIfUserNameExists(String userName) {
		if(this.jobSeekerDao.getByUserName(userName) != null) {
			return new ErrorResult(Messages.UserNameAlreadyExists);
		}
		return null;
	}

}
