package com.translator.gwa.application.resources.validation;

import com.translator.gwa.application.contents.LanguageCode;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class LanguageCodeValidatorImpl implements ConstraintValidator<LanguageCodeValidator, String[]> {

    /**
     * ${inheritDoc}
     */
    @Override
    public boolean isValid(String[] values, ConstraintValidatorContext context) {

        // LanguageCodeに存在しない引数がある場合falseを返す
        for (String value : values) {
            if (Arrays.stream(LanguageCode.values())
                    .anyMatch(languageCode -> languageCode.getCode().equals(value))) {
                continue;
            }

            // エラーメッセージ用に引数を格納
            context.unwrap(HibernateConstraintValidatorContext.class).addExpressionVariable("value", value);
            return false;
        }
        return true;
    }
}
