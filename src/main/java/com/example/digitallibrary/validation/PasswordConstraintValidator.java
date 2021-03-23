package com.example.digitallibrary.validation;
/**
 * https://www.baeldung.com/registration-password-strength-and-rules
 */

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String>
{
  @Override
  public void initialize(ValidPassword constraintAnnotation)
  {

  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context)
  {
    PasswordValidator validator = new PasswordValidator (Arrays.asList (
        new LengthRule (6, 20), //Password between 6 and 20 characters
        new UppercaseCharacterRule (1), //Requires 1 uppercase character
        new DigitCharacterRule (1), //Requires 1 digit
        new SpecialCharacterRule (1), // Requires 1 spacial character
        new WhitespaceRule ())); // No whitespace

    RuleResult result = validator.validate (new PasswordData (password));
    if (result.isValid ()) {
      return true;
    }
    context.disableDefaultConstraintViolation ();
    context.buildConstraintViolationWithTemplate (String.valueOf (validator.getMessages (result)))
        .addConstraintViolation ();
    return false;
  }
}
