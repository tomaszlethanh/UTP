/**
 *
 *  @author Le Thanh Tomasz S21216
 *
 */

package zad1;


import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {

    List<Purchase> purchaseList;

    public void readFile(String fname) {
        try {
            purchaseList = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String s;
            while ((s = br.readLine()) != null) {
                String[] splitArr = s.split(";|\\s");
                Purchase purchase = new Purchase(splitArr[0],
                        splitArr[1],
                        splitArr[2],
                        splitArr[3],
                        Double.parseDouble(splitArr[4]),
                        Double.parseDouble(splitArr[5]));
                purchaseList.add(purchase);
            }
        } catch (IOException e){
            System.out.println("Error while reading file");
        }
    }

    public void showSortedBy(String cond){
        Comparator<Purchase> byLastName = Comparator.comparing(Purchase::getLastName).thenComparing(Purchase::getIndex);
        Comparator<Purchase> byTotalPrice = Comparator.comparing(Purchase::getTotal).thenComparing(Purchase::getIndex).reversed();
        switch (cond) {
            case "Nazwiska":
                System.out.println(cond);
                purchaseList.sort(byLastName);
                for (Purchase p : purchaseList)
                    System.out.println(p);
                System.out.println();
                break;
            case "Koszty":
                System.out.println(cond);
                purchaseList.sort(byTotalPrice);
                for (Purchase p : purchaseList)
                    System.out.println(p + "(koszt: " + p.getTotal() + ")");
                System.out.println();
                break;
        }
    }

    public void showPurchaseFor(String index){
        System.out.println("Klient " + index);
        for (Purchase p : purchaseList)
            if (p.getIndex().equals(index))
                System.out.println(p);
        System.out.println();
    }
}
