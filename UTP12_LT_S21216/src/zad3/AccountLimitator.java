package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class AccountLimitator implements VetoableChangeListener {

    private double limit;

    AccountLimitator(int limit) {
        this.limit = limit;
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        double val = (double)evt.getNewValue();
        if (val < limit)
            throw new PropertyVetoException("Unacceptable value change: " + val, evt);
    }
}
