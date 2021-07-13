package Husonia.HRMS.business.abstracts;

import java.util.List;

import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.entities.concretes.JobPosition;

public interface JobPositionService {
	DataResult<List<JobPosition>> getAll();
	
	Result add(JobPosition jobPosition);
}
