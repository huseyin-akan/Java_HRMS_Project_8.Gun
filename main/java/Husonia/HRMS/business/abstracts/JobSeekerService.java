package Husonia.HRMS.business.abstracts;

import java.util.List;

import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.entities.concretes.JobSeeker;

public interface JobSeekerService {
	Result add(JobSeeker jobSeeker);	
	DataResult<List<JobSeeker>> getAll();
}
