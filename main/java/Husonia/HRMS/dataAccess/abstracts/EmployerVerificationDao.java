package Husonia.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import Husonia.HRMS.entities.concretes.EmployerVerification;

public interface EmployerVerificationDao extends JpaRepository<EmployerVerification, Integer> {
	EmployerVerification getByUserId(int userId);
}
