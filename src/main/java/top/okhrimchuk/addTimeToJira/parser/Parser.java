package top.okhrimchuk.addTimeToJira.parser;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import top.okhrimchuk.addTimeToJira.exeption.ValidationExeption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class Parser implements Parsers {
    public final String format = "H'H' m'M'";

    @Override
    public Map <String, String> parse (String param)  throws ValidationExeption {
        String id;
        JSONObject jsonObj;
        String description;
        LocalTime localTimeObj;

        JSONParser jsonParser = new JSONParser();
        Map <String, String> map = new HashMap<>();

        try {
            jsonObj = (JSONObject) jsonParser.parse(param);
            id = jsonObj.get("id").toString();
            description = jsonObj.get("description").toString();
            String time = jsonObj.get("time").toString();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format,  Locale.ENGLISH);
            localTimeObj = LocalTime.parse(time, formatter);

        } catch (ParseException e) {
            e.printStackTrace();
            log.error(e.toString());
            throw new ValidationExeption("Not valid time!", e);
        }

        map.put("id", id);
        map.put("description", description);
        map.put("time", String.valueOf(localTimeObj.toSecondOfDay()));
        return map;
    }
}
