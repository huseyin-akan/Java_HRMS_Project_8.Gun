package Husonia.HRMS.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "employer_verifications")
public class EmployerVerification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="is_verified")
	private boolean IsVerified;
	
	@Column(name="verification_date")
	private LocalDate verificationDate; 
	
	@Column(name="is_approved")
	private boolean IsApproved;
	
	@Column(name="approval_date")
	private LocalDate approvalDate; 
}
