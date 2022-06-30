package homework3.task2;

/*
Java. Уровень 2. Урок 3. Задача 2. "Коллекции".

Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны. К каждому имени может быть одновременно множество тел. номеров. Все они должны быть максимально компактно, т.е. не должно быть на каждый номер одного имени новая запись.

Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять ещё дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main() прописывая add() и get().

Важно: в выбранной Вами коллекции не должно быть повторений имён.
*/

import java.util.Random;

public class Task2 {

    static String[] lastnames = {
            "Тимофей",
            "Игорь",
            "Виталий",
            "Кирилл",
            "Артём",
            "Сергей",
            "Алексей"
    };

    static Random random = new Random();

    private static String getRandomPhoneNumber() {
        int countRecord = 7;
        String number = "+7(095)";
        for (int i = 0; i < countRecord; i++) {
            number += random.nextInt(9);
        }
        return number;
    }

    private static String getRandomLastname() {
        return lastnames[random.nextInt(lastnames.length)];
    }

    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        int countRecord = random.nextInt(10);
        for (int i = 0; i < countRecord; i++) {
            phoneDirectory.add(
                    getRandomLastname(),
                    getRandomPhoneNumber()
            );
        }

        String findLastName = lastnames[random.nextInt(lastnames.length)];
        System.out.println(findLastName + " " + "=" + " " + phoneDirectory.get(findLastName));

        System.out.println(phoneDirectory);
    }
}
