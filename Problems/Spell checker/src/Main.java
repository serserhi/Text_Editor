import java.util.*;


class MainSpellChecker {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);

        int knownWordsLength = Integer.parseInt(scanner.nextLine());

        Set<String> knownWords = new HashSet<>();
        Set<String> wordsToSpell = new HashSet<>();

        for (int i = 0; i < knownWordsLength; i++) {
            knownWords.add(scanner.nextLine().toLowerCase());
        }

        int textLength = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < textLength; i++) {
            String line = scanner.nextLine().toLowerCase();
            wordsToSpell.addAll(Arrays.asList(line.split(" ")));
        }

        wordsToSpell.removeAll(knownWords);

        wordsToSpell.forEach(System.out::println);

    }
}