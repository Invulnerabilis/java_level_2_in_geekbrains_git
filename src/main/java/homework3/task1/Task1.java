package homework3.task1;

/*
Java. Уровень 2. Урок 3. Задача 1. "Коллекции".

Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
*/

import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>() {{
            add("Iceberg");
            add("Acacia");
            add("Aquamari");
            add("Aquamari");
            add("Aquatoria");
            add("Alligator");
            add("Diamond");
            add("Albatross");
            add("Antarcti");
            add("Diamond");
            add("Anticyclone");
            add("Aquamari");
            add("April");
            add("Assimilation");
            add("atmosphere");
            add("disaster");
            add("Aquamari");
            add("belladonna");
            add("atmosphere");
            add("disaster");
        }};

        printUniqueWords(list);
        System.out.println();
        printCountWords(list);

    }

    private static void printCountWords(List<String> list) {
        for (String word : list) {
            System.out.println(word + " = " + countWord(word, list));
        }
    }

    private static void printUniqueWords(List<String> list) {
        for (String word : list) {
            if (!isContainsDuplicate(word, list)) {
                System.out.println(word);
            }
        }
    }

    private static boolean isContainsDuplicate(String word, List<String> list) {
        int count = 0;
        for (String otherWord : list) {
            if (otherWord.equals(word)) {
                count++;
            }
            if (count > 1) {
                return true;
            }
        }
        return false;
    }

    private static int countWord(String word, List<String> list) {
        int count = 0;
        for (String otherWord : list) {
            if (otherWord.equals(word)) {
                count++;
            }
        }
        return count;
    }
}
