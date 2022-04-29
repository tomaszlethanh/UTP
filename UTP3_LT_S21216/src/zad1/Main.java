/**
 *
 *  @author Le Thanh Tomasz S21216
 *
 */

package zad1;


import java.util.*;


public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when(arg -> arg.startsWith("WAW")
                       )
                       .mapEvery((String arg) -> {
                           String[] splitArr = arg.split(" ");
                           return "to " + splitArr[1] + " - price in PLN: "
                                 + (int)(xrate*Integer.parseInt(splitArr[2]));
                       }
                       );
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
