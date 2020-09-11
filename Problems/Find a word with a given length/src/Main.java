import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();

        // write your code
        boolean  condicion = false;
        int aux = 0;
        String[] palabras = line.split("[^[a-zA-Z]]");
        int[] cantidad = new int[palabras.length];
        for (int i = 0 ; i < palabras.length; i++) {
            cantidad[i] = palabras[i].length();
        }
        
        for (int i = 0; i< cantidad.length; i++) {
            if (cantidad[i] == size) {
                condicion = true;
                break;
            }
        }
        if (condicion) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}