package Husonia.HRMS.business.concretes;

import org.springframework.stereotype.Service;

import Husonia.HRMS.business.abstracts.UserCheckService;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.core.utilities.results.SuccessResult;
import Husonia.HRMS.entities.concretes.User;

@Service
public class FakeMernisManager implements UserCheckService {

	@Override
	public Result checkIfUserReal(User user) {
		
		//kişinin bilgileri kontrol edilir ve doğru ise true döner.
		return new SuccessResult();
	}
	
}
