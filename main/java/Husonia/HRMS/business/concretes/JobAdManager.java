package Husonia.HRMS.business.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import Husonia.HRMS.business.abstracts.JobAdService;
import Husonia.HRMS.core.utilities.business.BusinessRules;
import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.ErrorResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.core.utilities.results.SuccessDataResult;
import Husonia.HRMS.core.utilities.results.SuccessResult;
import Husonia.HRMS.dataAccess.abstracts.JobAdDao;
import Husonia.HRMS.entities.concretes.JobAd;

@Service
public class JobAdManager implements JobAdService {

	private JobAdDao jobAdDao;

	public JobAdManager(JobAdDao jobAdDao) {
		super();
		this.jobAdDao = jobAdDao;
	}

	@Override
	public Result add(JobAd jobAd) {
		// TODO: zorunlu alanlar işaretlendi mi?

		Result result = BusinessRules.Run(
				checkIfSomeFieldsNotNull(jobAd)
		);

		// geçemediğin ilk iş kuralını dön:
		if (result != null) {
			return result;
		}

		this.jobAdDao.save(jobAd);
		return new SuccessResult("İş ilanı başarıyla oluşturuldu.");
	}
	
	@Override
	public DataResult<List<JobAd>> getActiveAds(boolean isActive) {
		return new SuccessDataResult<List<JobAd>>(this.jobAdDao.getByIsActive(isActive), "Aktif ilanlar başarıyla listelendi.");
	}
	
	@Override
	public DataResult<List<JobAd>> getAdsAfter(Date publicationDate) {
		return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findAllWithPublicationDateTimeAfter(publicationDate), 
				publicationDate + " tarihi sonrası datalar başarıyla getirildi.");
	}
	
	@Override
	public DataResult<List<JobAd>> findAllActiveJobAdsByCompanyName(String companyName) {
		return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findAllActiveJobAdsByCompanyName(companyName), 
				companyName + " şirketinin tüm aktif ilanları getirildi.");
	}
	
	@Override
	public Result updateJobadIsActive(int jobAdId, boolean isActive) {
		JobAd jobAdToUpdate = this.jobAdDao.getById(jobAdId);
		jobAdToUpdate.setActive(isActive);
		this.jobAdDao.save(jobAdToUpdate);
		if (isActive) {
			return new SuccessResult("İş ilanı aktif olarak kaydedildi.");
		}else {
			return new SuccessResult("İş ilanı pasif olarak kaydedildi.");
		}		
	}


	public Result checkIfSomeFieldsNotNull(JobAd jobAd) {
		if (jobAd.getJobDescription() == null) {
			return new ErrorResult("Boş alan bırakılamaz");
		}
		//else if (jobAd.getJobPosition() == null) {
		//	return new ErrorResult("Boş alan bırakılamaz");
		//} 
	   //else if (jobAd.getCity() == null) {
		//	return new ErrorResult("Boş alan bırakılamaz");
		// }
	else if (jobAd.getCountOfOpenVacancy() == 0) {
			return new ErrorResult("Boş alan bırakılamaz");
		}
		return null;
	}

	


	

	

}
