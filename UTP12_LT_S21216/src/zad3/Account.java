package zad3;

import java.beans.*;
import java.io.Serializable;

public class Account implements Serializable {
    private double balance;
    private static double count = 1;
    int id;
    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

    public Account() throws PropertyVetoException {
        this(0);
        id = (int)count;
        count = count + 0.5;
    }

    public Account(double balance) throws PropertyVetoException {
        setBalance(balance);
        id = (int)count;
        count = count + 0.5;
    }


    public void deposit(int amount) throws PropertyVetoException {
        setBalance(getBalance() + amount);
    }

    public void withdraw(int amount) throws PropertyVetoException {
        setBalance(getBalance() - amount);
    }

    public void transfer(Account acc, int amount) throws PropertyVetoException {
        withdraw(amount);
        acc.deposit(amount);
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void setBalance(double number) throws PropertyVetoException {
        double oldValue = balance;
        vetos.fireVetoableChange("balance", oldValue, number);
        balance = number;
        propertyChange.firePropertyChange("balance", oldValue, number);
    }

    @Override
    public String toString() {
        return "Acc " + id + ": " + getBalance();
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChange.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChange.removePropertyChangeListener(l);
    }


    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        vetos.removeVetoableChangeListener(l);
    }
}
