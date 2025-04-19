import java.lang.Math;
public class Main {
    public static void main(String[] args) {
        try {
            double op1 = Double.parseDouble(args[0]);
            double op2 = Double.parseDouble(args[2]);
            String operator = args[1];

            double result;
            switch (operator) {
                case "+":
                    result = op1 + op2;
                    break;
                case "-":
                    result = op1 - op2;
                    break;
                case "x":
                    result = op1 * op2;
                    break;
                case "/":
                    if (op2 == 0) {
                        System.out.println("Error");
                        return;
                    }
                    result = op1 / op2;
                    break;
                case "^":
                    result = Math.pow(op1, op2);
                    break;
                default:
                    System.out.println("Unsupported operator");
                    return;
            }
            System.out.println(result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid expression");
        }
    }
}