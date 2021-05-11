package top.okhrimchuk.addTimeToJira;

import lombok.extern.slf4j.Slf4j;
import top.okhrimchuk.addTimeToJira.data.Data;
import top.okhrimchuk.addTimeToJira.exeption.ValidationExeption;
import top.okhrimchuk.addTimeToJira.parser.Parser;
import top.okhrimchuk.addTimeToJira.parser.Parsers;
import top.okhrimchuk.addTimeToJira.http.SenderHttp;
import top.okhrimchuk.addTimeToJira.validation.Validator;
import top.okhrimchuk.addTimeToJira.validation.Validators;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class App {

    public static void main(String[] args) throws ValidationExeption {
        Data data = new Data();
        Parsers parser = new Parser();
        Validators validators = new Validator();
        SenderHttp sender = new SenderHttp();

        String line = data.getdata();
        boolean isValid = validators.validate(line);

        if (isValid) {
            try {
                Map <String, String> map = parser.parse(line);
                boolean res = sender.send(map);
                log.info(res ? "Added succes": "Added unsucces");
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.toString());
            }
        }
    }
}
