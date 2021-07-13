package Husonia.HRMS.core.utilities.business;

import Husonia.HRMS.core.utilities.results.Result;

public class BusinessRules {
	public static Result Run(Result... logics) {
		for (Result logic : logics) {
			if (logic != null && !logic.isSuccess()) {
				return logic;
			}
		}
		return null;
	}
}
