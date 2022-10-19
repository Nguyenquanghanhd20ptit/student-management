package laptrinh.validate;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import laptrinh.entity.student;
import laptrinh.repository.student_repository;

@Service
public class studentValidate implements Validator{
	@Autowired
	student_repository student_repository;

	@Override
	public boolean supports(Class<?> clazz) {
		return student.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		student student = (student) target;
		for(int i=0;i<student.getSdt().length();i++) {
			if(student.getSdt().charAt(i)<'0' || student.getSdt().charAt(i) >'9') {
				errors.rejectValue("sdt", "InvalidSdt");
				break;
			}
		}
		String patten = "^B[0-9]{2}[a-z A-Z]{4}[0-9]{3}$";
		if(Pattern.matches(patten, student.getMsv())== false) {
			errors.rejectValue("msv", "InvalidMsv");
		}
		if(ObjectUtils.isEmpty(student_repository.findbyMsv(student.getMsv()))==false) {
			errors.rejectValue("msv", "existMsv");
			
		}
	}

}
