package top.okhrimchuk.addTimeToJira.parser;

import top.okhrimchuk.addTimeToJira.exeption.ValidationExeption;
import java.util.Map;

public interface ParserI {
    Map parse (String param) throws ValidationExeption;
}
