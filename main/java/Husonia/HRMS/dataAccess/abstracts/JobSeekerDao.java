package Husonia.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import Husonia.HRMS.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer>{
	JobSeeker getByUserName(String userName);
	JobSeeker getByEmail(String email);
	JobSeeker getByNationalityNo(String nationalityNo);
}
