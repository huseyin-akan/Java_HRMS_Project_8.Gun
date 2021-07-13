package Husonia.HRMS.business.concretes;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import Husonia.HRMS.business.abstracts.EmployerVerificationService;
import Husonia.HRMS.core.utilities.results.ErrorResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.core.utilities.results.SuccessResult;
import Husonia.HRMS.dataAccess.abstracts.EmployerVerificationDao;
import Husonia.HRMS.entities.concretes.EmployerVerification;

@Service
public class EmployerVerificationManager implements EmployerVerificationService {

	private EmployerVerificationDao employerVerificationDao;
		
	public EmployerVerificationManager(EmployerVerificationDao employerVerificationDao) {
		this.employerVerificationDao = employerVerificationDao;
	}

	//kodların içerisi doldurulacak
	@Override
	public Result verifyByEmail(int userId) {

		if (this.employerVerificationDao.getByUserId(userId) != null)  {
			return new ErrorResult("Kullanıcı zaten doğrulandı");
		}

		
		EmployerVerification employerVerification = new EmployerVerification();
		employerVerification.setUserId(userId);
		employerVerification.setIsVerified(true);
		employerVerification.setVerificationDate(LocalDate.now());
		
		this.employerVerificationDao.save(employerVerification);
		return new SuccessResult("Şirket email doğrulama işlemi başarılı");

	}

	//HRMS personeli onayı
	@Override
	public Result verifyByAdminApproval(int userId) {
		EmployerVerification employerVerification = this.employerVerificationDao.getById(userId);
		
		if (employerVerification.isIsVerified() == false) {
			return new ErrorResult("Email doğrulaması yapılmadığı için onay verilemez!!");
		}
		
		employerVerification.setIsApproved(true);
		employerVerification.setApprovalDate(LocalDate.now());
		
		this.employerVerificationDao.save(employerVerification);
		return new SuccessResult("Şirket onaylama işlemi başarılı oldu.");
	}

}
