package top.okhrimchuk.addTimeToJira.validation;

public class Validator implements Validators {

    public boolean validate(String param) {
        if (param == null) {
            throw new IllegalArgumentException("argument cannot be null.");
        }
        if (param.isEmpty()) {
            throw new IllegalArgumentException("argument cannot be Empty.");
        }
        return true;
    }
}
