package com.example.myinterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Repository.HolidayRepository;

public class NameCheckValidator implements ConstraintValidator<NameCheck, Long> {

	private boolean required;
	
	@Autowired
	private HolidayRepository holidayrepo;
	
	public void initialize(NameCheck constraintAnnation) {
		this.required=constraintAnnation.required();
	}
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if(required) {
			if(holidayrepo.getById(value).getEmployee().getId()==holidayrepo.getById(value).getEmployee2().getId()) {
				return false;
			}
			
		}
		return true;
	}

}
