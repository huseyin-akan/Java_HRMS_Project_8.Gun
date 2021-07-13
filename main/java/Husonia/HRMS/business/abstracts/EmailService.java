package Husonia.HRMS.business.abstracts;

import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.entities.concretes.User;

public interface EmailService {
	void sendVerification(User user);
	
	Result verify(String email, String token);
}
