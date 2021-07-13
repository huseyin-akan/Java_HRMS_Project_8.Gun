package Husonia.HRMS.business.abstracts;

import java.util.Date;
import java.util.List;

import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.entities.concretes.JobAd;

public interface JobAdService {
	Result add(JobAd jobAd);
	
	DataResult<List<JobAd>> getActiveAds(boolean isActive);
	
	DataResult<List<JobAd>> getAdsAfter(Date publicationDate);
	
	DataResult<List<JobAd>> findAllActiveJobAdsByCompanyName(String companyName);
	
	Result updateJobadIsActive(int jobAdId, boolean isActive);
}
