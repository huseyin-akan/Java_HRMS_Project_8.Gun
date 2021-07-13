package Husonia.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import Husonia.HRMS.entities.concretes.JobSeekerVerification;

public interface JobSeekerVerificationDao extends JpaRepository<JobSeekerVerification, Integer>{
	JobSeekerVerification getByUserId(int userId);
}
