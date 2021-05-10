package top.okhrimchuk.addTimeToJira.exeption;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ValidationExeption extends Exception {
    private  Exception e;

    public ValidationExeption(String msg, Exception e) {
        super(msg);
        this.e = e;
    }
}
