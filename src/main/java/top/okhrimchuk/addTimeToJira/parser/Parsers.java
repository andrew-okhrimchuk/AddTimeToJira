package top.okhrimchuk.addTimeToJira.parser;

import top.okhrimchuk.addTimeToJira.exeption.ValidationExeption;
import java.util.Map;

public interface Parsers {
    Map<String, String> parse (String param) throws ValidationExeption;
}
