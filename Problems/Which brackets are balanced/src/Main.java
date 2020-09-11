import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        char[] caracteres = line.toCharArray();
        char ultimo;
        boolean balanceado = true;

        ArrayDeque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < caracteres.length; i++) {
            if ((caracteres[i] == '(') ||
                    (caracteres[i] == '{') ||
                    (caracteres[i] == '[')) {
                deque.offer(caracteres[i]);
            } else {
                if (deque.isEmpty() == true) {
                    balanceado = false;
                } else {
                    ultimo = deque.pollLast();
                    if (((ultimo != '(') && caracteres[i] == ')') ||
                            ((ultimo != '{') && caracteres[i] == '}') ||
                            ((ultimo != '[') && caracteres[i] == ']')) {
                        balanceado = false;
                        break;
                    }
                }
            }
        }
        if (deque.isEmpty() == false) {
            balanceado = false;
        }
        System.out.println(balanceado);

    }
}