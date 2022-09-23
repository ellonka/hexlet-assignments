package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> fields = new ArrayList<>();
        for (Field field: address.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.getAnnotation(NotNull.class) != null && field.get(address) == null) {
                    fields.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> result = new HashMap<>();
        List<String> valNullWithNotNull = validate(address);
        for (Field field: address.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                MinLength minLength = field.getAnnotation(MinLength.class);

                if (valNullWithNotNull.contains(field.getName())) {
                    result.put(field.getName(), List.of("can not be null"));
                } else if (minLength != null && ((String)field.get(address)).length() < minLength.minLength()) {
                    result.put(field.getName(), List.of("length less than " + minLength.minLength()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
// END
