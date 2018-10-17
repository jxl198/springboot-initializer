package com.jiangxl.miaosha.validator;

import com.jiangxl.miaosha.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author jiangxl
 * @description  @IsMobile 校验器
 * @date 2018-10-17 16:41
 **/
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            if (!required) {
                return true;
            }
        } else {
            return ValidatorUtil.isMobile(value);
        }

        return false;

    }
}
