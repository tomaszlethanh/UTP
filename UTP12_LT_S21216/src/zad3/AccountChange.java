package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccountChange implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        double oldVal = (double)evt.getOldValue(),
                newVal = (double)evt.getNewValue();
        System.out.println("Value changed from " + oldVal + " to " + newVal);
    }
}
