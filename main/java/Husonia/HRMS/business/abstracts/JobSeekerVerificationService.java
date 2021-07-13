package Husonia.HRMS.business.abstracts;

import Husonia.HRMS.core.utilities.results.Result;

public interface JobSeekerVerificationService {
	Result verify(int userId);
}
