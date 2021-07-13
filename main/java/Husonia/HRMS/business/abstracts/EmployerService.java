package Husonia.HRMS.business.abstracts;

import java.util.List;

import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.entities.concretes.Employer;

public interface EmployerService {
	Result add(Employer employer);
	
	DataResult<List<Employer>> getAll();
}
