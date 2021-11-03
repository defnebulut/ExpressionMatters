import java.util.HashMap;
import java.util.Stack;

/**
 * @author Defne BULUT
 * @since 29.10.2021
 */
public class ExpressionMatters {
    private static Stack<String> operators = new Stack<>();
    private static Stack<Integer> values = new Stack<>();

    public static void main(String[] args) {
        evalExp("14 < 4 - 3 * 2 + 7");
    }
    public static void doOp(){
        int x = values.pop();
        int y = values.pop();
        String op = operators.pop();
        if (op.equals("+")) values.push(y + x);
        else if (op.equals("-")) values.push(y - x);
        else if (op.equals("*")) values.push(y * x);
        else if (op.equals("/")) values.push(y / x);
        else if (op.equals("≤")) System.out.println(y <= x);
        else if (op.equals("≥")) System.out.println(y >= x);
        else if (op.equals("<")) System.out.println(y < x);
        else System.out.println(y > x);
    }
    public static void repeatOps(String refOp){
        if (operators.size() > 0) {
            while (values.size() > 1 && prec(refOp)<=prec(operators.peek())) {
                doOp();
            }
        }
    }
    public static void evalExp(String s){
        String[] ss = s.split(" ");
        for(String c : ss){
            if (isNumber(c)) values.push(Integer.parseInt(c));
            else {
                repeatOps(c);
                operators.push(c);
            }
        }
        repeatOps("$");
        if (values.size() > 0) System.out.println(values.peek());
    }
    public static int prec(String a){
        if (a.equals("+")||a.equals("-"))
            return 1;
        if (a.equals("*")||a.equals("/"))
            return 2;
        return 0;
    }
    public static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
