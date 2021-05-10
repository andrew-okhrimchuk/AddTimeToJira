package top.okhrimchuk.addTimeToJira;

import lombok.extern.slf4j.Slf4j;
import top.okhrimchuk.addTimeToJira.data.Data;
import top.okhrimchuk.addTimeToJira.exeption.ValidationExeption;
import top.okhrimchuk.addTimeToJira.parser.Parser;
import top.okhrimchuk.addTimeToJira.parser.ParserI;
import top.okhrimchuk.addTimeToJira.send.SenderApacheHttp;
import top.okhrimchuk.addTimeToJira.validation.JsonValidator;
import top.okhrimchuk.addTimeToJira.validation.Validator;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class App {

    public static void main(String[] args) throws ValidationExeption {
        Data data = new Data();
        ParserI parser = new Parser();
        Validator validator = new JsonValidator();
        SenderApacheHttp sender = new SenderApacheHttp();

        String line = data.getdata();
        boolean isValid = validator.validate(line);

        if (isValid) {
            try {
                Map map = parser.parse(line);
                boolean res = sender.send(map);
                log.info(res ? "Added succes": "Added unsucces");
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.toString());
            }
        }
    }
}
