package Husonia.HRMS.business.concretes;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import Husonia.HRMS.business.abstracts.JobSeekerVerificationService;
import Husonia.HRMS.core.utilities.results.ErrorResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.core.utilities.results.SuccessResult;
import Husonia.HRMS.dataAccess.abstracts.JobSeekerVerificationDao;
import Husonia.HRMS.entities.concretes.JobSeekerVerification;

@Service
public class JobSeekerVerificationManager implements JobSeekerVerificationService {

	private JobSeekerVerificationDao jobSeekerVerificationDao;
	
	public JobSeekerVerificationManager(JobSeekerVerificationDao jobSeekerVerificationDao) {
		this.jobSeekerVerificationDao = jobSeekerVerificationDao;
	}

	@Override
	public Result verify(int userId) {
				
		if (this.jobSeekerVerificationDao.getByUserId(userId) != null) {
			return new ErrorResult("Kullanıcı zaten doğrulandı.");
		}
		
		//aşağıdaki kod revize edilebilir, üzerinden geçmemiz lazım.
		JobSeekerVerification jobSeekerVerification = new JobSeekerVerification();
		jobSeekerVerification.setUserId(userId);
		jobSeekerVerification.setVerificationDate(LocalDate.now());
		jobSeekerVerification.setIsVerified(true);
		
		this.jobSeekerVerificationDao.save(jobSeekerVerification);
		
		return new SuccessResult("doğrulama işlemi başarılı");
	}

}
