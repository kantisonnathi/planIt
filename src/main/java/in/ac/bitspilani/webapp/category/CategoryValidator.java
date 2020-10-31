package in.ac.bitspilani.webapp.category;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CategoryValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public void validate(Object obj, Errors errors) {
        Category category = (Category) obj;
        String name = category.getName();
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }
    }

    /**
     * This Validator validates *just* Pet instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.isAssignableFrom(clazz);
    }

}

