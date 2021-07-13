package Husonia.HRMS.business.abstracts;

import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.entities.concretes.User;

public interface UserCheckService {
	Result checkIfUserReal(User user);
}
