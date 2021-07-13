package Husonia.HRMS.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="employers")
@AllArgsConstructor
@NoArgsConstructor
public class Employer extends User{
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="web_site")
	private String webSite;
	
	@Column(name="web_site_mail")
	private String webSiteMail;
	
	@JsonInclude()
	@Transient
	private String passwordCheck;
	
}
