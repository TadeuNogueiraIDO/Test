package br.com.idolink.api.validator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.ResourceBundleMessageResolver;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import br.com.idolink.api.annotation.ValidPassword;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String>{
	
	@Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        
		ResourceBundleMessageResolver resolver = new ResourceBundleMessageResolver(
				  ResourceBundle.getBundle("passay", Locale.ENGLISH));
		
	
		PasswordValidator validator = new PasswordValidator(resolver,Arrays.asList(
            
        	// pelo menos 8 caracteres
            new LengthRule(8, 30),

            // Pelo menos 1 caracter maiusculo
            //new CharacterRule(EnglishCharacterData.UpperCase, 1),

            // pelo menos 1 caracter minusculo
            //new CharacterRule(EnglishCharacterData.LowerCase, 1),

            // pelo menos 1 caracter especial
            //new CharacterRule(EnglishCharacterData.Special, 1),

            // sem espaco em branco
            new WhitespaceRule()

        ));
                  
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = messages.stream()
            .collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation()
            .disableDefaultConstraintViolation();
        return false;
    }
}








	

