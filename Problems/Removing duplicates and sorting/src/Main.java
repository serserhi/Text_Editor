import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);
        SortedSet<String> sortedSet = new TreeSet<>();
        int tamaño = scanner.nextInt();
        for (int i = 0; i <= tamaño; i++) {
            sortedSet.add(scanner.nextLine());
        }
        for (String name : sortedSet) {
            System.out.println(name);
        }

    }
}