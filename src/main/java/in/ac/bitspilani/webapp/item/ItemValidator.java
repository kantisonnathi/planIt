package in.ac.bitspilani.webapp.item;

import in.ac.bitspilani.webapp.item.Item;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ItemValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public void validate(Object obj, Errors errors) {
        //Category category = (Category) obj;
        Item item = (Item) obj;
        String name = item.getName();
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }
    }

    /**
     * This Validator validates *just* Item instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

}

