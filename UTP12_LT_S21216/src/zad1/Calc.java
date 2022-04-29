/**
 *
 *  @author Le Thanh Tomasz S21216
 *
 */

package zad1;


import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;


public class Calc {
    HashMap<String, String> op = new HashMap<>();

    public Calc() {
        op.put("+", "add");
        op.put("-", "subtract");
        op.put("*", "multiply");
        op.put("/", "divide");
    }

    public String doCalc(String cmd)  {
        String[] splitArr = cmd.split("\\s");
        String operation = op.get(splitArr[1]);
        BigDecimal bd1 = new BigDecimal(splitArr[0]);
        BigDecimal bd2 = new BigDecimal(splitArr[2]);
        BigDecimal bdresult;
        String result = null;
        try {
            Method m = BigDecimal.class.getDeclaredMethod(operation, BigDecimal.class, MathContext.class);
            bdresult = (BigDecimal)m.invoke(bd1, bd2, new MathContext(7, RoundingMode.HALF_DOWN));
            result = bdresult.toPlainString();
        } catch (Exception e) {
            return "Invalid command to calc";
        }
        return result;
    }
}  
