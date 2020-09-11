import java.util.*;

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        // write your code here
        SortedSet<Integer> aux = new TreeSet<>();
        String[] palabras = str.split(" ");
        for (int i = 0; i < palabras.length; i++) {
            aux.add(Integer.parseInt(palabras[i]));
        }
        return aux;

    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        // write your code here
        SortedSet<Integer> sortedSet = new TreeSet<>();
        sortedSet.addAll(set);
        set.clear();
        set.addAll(sortedSet.headSet(11));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        Set<Integer> set = SetUtils.getSetFromString(numbers);
        SetUtils.removeAllNumbersGreaterThan10(set);
        set.forEach(e -> System.out.print(e + " "));
    }
}