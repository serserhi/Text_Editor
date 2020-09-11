import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        long resultado = 1;
        for (int i = a; i < b ; i++) {
            resultado = resultado * i;
        }
        System.out.println(resultado);
    }
}
