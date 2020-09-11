import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        StringReverser reverser = new StringReverser() {
            @Override
            public String reverse(String str) {
                String aux = "";
                for (int i= line.length() - 1 ; i >= 0 ; i--) {
                    aux += line.substring(i,i+1);
                };
                return aux;
            }
        };/* create an instance of an anonymous class here,
                                     do not forget ; on the end */
        /* Tambien podemos poner simplemente esto:

        StringReverser reverser = new StringReverser() {

            @Override
            public String reverse(String str) {
                return new StringBuilder(str).reverse().toString();
            }
        };
        *
        * */

        System.out.println(reverser.reverse(line));
    }

    interface StringReverser {

        String reverse(String str);
    }

}