package Husonia.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import Husonia.HRMS.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	Employer getByEmail(String email);
}
