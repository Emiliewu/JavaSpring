package com.emilie.authentication.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.emilie.authentication.models.User;

@Component
public class UserValidator implements Validator {
    
    // 1
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }

    public void validatelength(Object target, Errors errors) {
        User user = (User) target;
        if(user.getPassword().length() < 5) {
        	 errors.rejectValue("password", "Length");
        }
    }
    public void validateemail(Object target, Errors errors) {
        User user = (User) target;
//        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$";
//        String regex2 = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
//        Pattern pattern = Pattern.compile(regex);
        boolean emailValid = Pattern.matches("[a-zA-Z0-9]{1,}[@]{1}[a-z]{2,}[.]{1}+[a-z]{2,}", user.getEmail());
//        Matcher matcher = pattern.matcher(user.getEmail());
        if(emailValid == false) {
        	 errors.rejectValue("email", "Emailreg");
        }
    }
}
