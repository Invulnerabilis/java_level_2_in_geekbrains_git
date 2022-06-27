package homework2;

/*
Java. Уровень 2. Урок 2. Исключения.

1. Написать метод, на вход которого подаётся двумерный строковый массив размером 4х4, при подаче массива другого размера необходимо бросить исключение MyArraySizeException.

2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.

3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException, и вывести результат расчёта.

Примечание:
В результате возникнут две исключительные ситуации:
1) При парсинге из строки в число может получиться исключение, т.е. ошибка приведения типов данных.
2) размер массива не 4х4 заранее оговоренный.

Определить, кто ответственен за возникновение исключительных ситуаций. Кто отправляет массив на метод суммирования? Или сам же метод суммирования?
*/

import homework2.exceptions.MyArrayDataException;
import homework2.exceptions.MyArraySizeException;

public class Main {
    public static void main(String[] args) {
        try {
            String[][] successMatrix = {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"}
            };
            String[][] errorSizeMatrix = {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"}
            };
            String[][] errorDataMatrix = {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4qwerty"},
                    {"1", "2", "3", "4"}
            };

            System.out.println(sum(errorDataMatrix));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static int sum(String[][] matrix) {
        checkSizeMatrix(matrix);
        int result = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                try {
                    result += Integer.parseInt(matrix[y][x]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Hello data mistake in (" + x + ", " + y + ") = " + matrix[y][x]);
                }
            }
        }
        return result;
    }

    private static void checkSizeMatrix(String[][] matrix) {
        if (matrix.length != 4) {
            throw new MyArraySizeException("Hello mistake size");
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != 4) {
                throw new MyArraySizeException("Hello mistake size again");
            }
        }
    }
/*
За возникновение исключительных ситуаций ответственен разработчик или оператор, в общем, кто имеет доступ к управлению кодом.
*/
}
