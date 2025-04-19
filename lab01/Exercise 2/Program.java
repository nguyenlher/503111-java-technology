import vn.edu.tdtu.ArrayHandler;
import vn.edu.tdtu.ArrayOutput;

public class Program {
    public static void main(String[] args) {
        int[] a = {12, 2, 34, 5, 6};
        int[] b = {6, 2, 1, 3, 4};

        ArrayOutput.print(a);
        ArrayHandler.sort(a);
        ArrayOutput.print(ArrayHandler.merge(a, b));
        ArrayOutput.write(a, "output.txt");
    }
}
