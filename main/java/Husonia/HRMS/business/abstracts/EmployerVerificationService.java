package Husonia.HRMS.business.abstracts;

import Husonia.HRMS.core.utilities.results.Result;

public interface EmployerVerificationService {
	Result verifyByEmail(int userId);
	
	Result verifyByAdminApproval(int userId);
}
