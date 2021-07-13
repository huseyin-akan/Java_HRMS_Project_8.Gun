package Husonia.HRMS.dataAccess.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Husonia.HRMS.entities.concretes.JobAd;

public interface JobAdDao extends JpaRepository<JobAd, Integer> {
	List<JobAd> getByIsActive(boolean isActive);
	
	@Query("select j from JobAd j where j.publicationDate >= :publicationDate")
    List<JobAd> findAllWithPublicationDateTimeAfter(
      @Param("publicationDate") Date publicationDate);
	
	@Query("select j from JobAd j where j.employer.companyName >= :companyName and j.isActive = true")
    List<JobAd> findAllActiveJobAdsByCompanyName(String companyName);
}
