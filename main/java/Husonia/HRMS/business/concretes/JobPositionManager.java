package Husonia.HRMS.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Husonia.HRMS.business.abstracts.JobPositionService;
import Husonia.HRMS.core.utilities.results.DataResult;
import Husonia.HRMS.core.utilities.results.ErrorResult;
import Husonia.HRMS.core.utilities.results.Result;
import Husonia.HRMS.core.utilities.results.SuccessDataResult;
import Husonia.HRMS.core.utilities.results.SuccessResult;
import Husonia.HRMS.dataAccess.abstracts.JobPositionDao;
import Husonia.HRMS.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {

	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll());
	}

	@Override
	public Result add(JobPosition jobPosition) {
		
		if (this.jobPositionDao.getByPositionName(jobPosition.getPositionName())!= null) {
			return new ErrorResult("Eklemek istediğiniz pozisyon zaten mevcut!!!");
		}
		
		this.jobPositionDao.save(jobPosition);
		return new SuccessResult("Pozisyon ekleme işlemi başarılı oldu");
		
	}
}
