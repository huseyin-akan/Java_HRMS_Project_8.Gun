package Husonia.HRMS.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import Husonia.HRMS.entities.concretes.JobPosition;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer>{
	JobPosition getByPositionName(String positionName);
}
