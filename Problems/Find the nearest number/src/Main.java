import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list1 = readArrayList(scanner);
        ArrayList<Integer> list2 = new ArrayList<>(0);
        int numero = scanner.nextInt();
        ArrayList<Integer> result = concatClosestNumbers(list1, list2,  numero);

        result.forEach(n -> System.out.print(n + " "));
    }
    public static ArrayList<Integer> concatClosestNumbers(ArrayList<Integer> l1, ArrayList<Integer> l2, int numero) {
        int diferencia = Math.abs(numero - l1.get(0));
        for (int i = 0; i < l1.size(); i++) {
            if (Math.abs(numero - l1.get(i)) < diferencia) {
                diferencia = Math.abs(numero - l1.get(i));
            }
        }
        for (int i = 0; i < l1.size(); i++) {
            if (Math.abs(numero - l1.get(i)) == diferencia) {
                if (l2.size() == 0) {
                    l2.add(l1.get(i));
                } else {
                    for (int j = 0; j < l2.size(); j++) {
                        if ((l1.get(i) <= l2.get(j))  ) {
                            l2.add(j ,l1.get(i));
                            break;
                        } else {
                            l2.add(l1.get(i));
                            break;
                        }
                    }
                }
            }
        }


        return l2;
    }

    private static ArrayList<Integer> readArrayList(Scanner scanner) {
        return Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}