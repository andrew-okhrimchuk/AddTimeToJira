package top.okhrimchuk.addTimeToJira.data;

import java.util.Scanner;

public class Data {
   public String getdata() {
       Scanner sc = new Scanner(System.in);
       System.out.println("Please enter data in Json format, for example: {\"id\": \"10000\", \"description\": \"Bla-bla-bla tyk-tyk\", \"time\": \"2H 25M\"}");
       return sc.nextLine();
   }
}
