import java.util.*;

public class Main {

    public static void processIterator(String[] array) {
        // write your code here
        List<String> list = new ArrayList<>();
        String prueba= "";

        for (int i = 0; i< array.length; i++) {
            list.add(array[i]);
        }
        //Iterator siempre después de que la lista tenga datos sino dará un error el método
        // next()
        ListIterator<String> iterator = list.listIterator();

        while(iterator.hasNext()) {
            prueba = iterator.next();
            if (prueba.matches("^[^J]*")) {
                iterator.set(" ");
            } else {
                iterator.set(prueba.replaceFirst("^J", ""));
            }
        }

        while(iterator.hasPrevious()) {
            prueba = iterator.previous();
            if (prueba != " ") {
                System.out.println(prueba);
            }
        }

    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        processIterator(scanner.nextLine().split(" "));
    }
}