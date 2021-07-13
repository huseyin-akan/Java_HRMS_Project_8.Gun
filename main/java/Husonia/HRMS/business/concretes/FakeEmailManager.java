package Husonia.HRMS.business.concretes;

import org.springframework.stereotype.Service;

import Husonia.HRMS.business.abstracts.EmailService;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.core.utilities.results.SuccessResult;
import Husonia.HRMS.entities.concretes.User;

@Service
public class FakeEmailManager implements EmailService{

	@Override
	public void sendVerification(User user) {
		System.out.println(user.getUserName() + " kullanı adlı kullanıcıya verification maili gönderildi.");
		
	}

	@Override
	public Result verify(String email, String token) {
		
		//kullanıcı doğrulaması kontrol edilir ve doğruysa database'de değişiklik yapılır ve kullanıcı bilgilendirilir.
		return new SuccessResult("email doğrulama işlemi başarılı");		
	}

}
