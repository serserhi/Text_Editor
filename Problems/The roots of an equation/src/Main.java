import java.util.Scanner;
import java.lang.Math;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextInt();
        double b = scanner.nextInt();
        double c = scanner.nextInt();
        double d = scanner.nextInt();
        int soluciones = 0;
        int x = 0;
        while ((x < 1001)&&(soluciones < 3)) {
            if  ((a*x*x*x + b*x*x + c*x + d) == 0) {
                System.out.println(x);
                soluciones++;
            }
            x++;
        }

        
        
    }
}
