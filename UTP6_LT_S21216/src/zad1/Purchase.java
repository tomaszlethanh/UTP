/**
 *
 *  @author Le Thanh Tomasz S21216
 *
 */

package zad1;


public class Purchase {

    private final String index;
    private final String name;
    private final String lastName;
    private final String item;
    private final double quantity;
    private final double price;
    private final double total;

    public Purchase(String index, String lastName, String name, String item, double quantity, double price){
        this.index = index;
        this.name = name;
        this.lastName = lastName;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.total = price * quantity;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getItem() {
        return item;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "" + index + ';' +
                lastName + ' ' +
                name + ';' +
                item + ';' +
                quantity + ";" +
                price;
    }
}
