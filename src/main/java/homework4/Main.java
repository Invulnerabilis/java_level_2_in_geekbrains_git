package homework4;

/*
Java. Уровень 2. Урок 4. "Многопоточность"

1. Необходимо написать два метода, которые делают следующее:
1) Создают одномерный длинный массив, например:

static final int size = 10000000;
static final int h = size / 2;
float[] arr = new float[size];

2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);

Отличие первого метода от второго:
Первый просто бежит по массиву и вычисляет значения.
Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

Пример деления одного массива на два:

System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

Пример обратной склейки:

System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

Примечание:
System.arraycopy() – копирует данные из одного массива в другой:
System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
По замерам времени:
Для первого метода надо считать время только на цикл расчета:

for (int i = 0; i < size; i++) {
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}

Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
*/

public class Main {

    static final int SIZE = 10000000;

    public static void main(String[] args) {
        withMultithreading();
        withoutMultithreading();
    }

    public static void withMultithreading() {
        float[] arr = createArray();

        long start = System.currentTimeMillis();

        try {
            int middle = arr.length / 2;
            float[] first = new float[middle];
            float[] second = new float[middle];
            System.arraycopy(arr, 0, first, 0, middle);
            System.arraycopy(arr, middle, second, 0, middle);

            Runnable runnable1 = () -> {
                for (int i = 0; i < middle; i++) {
                    first[i] = getNextNumber(first[i], i);
                }
            };

            Runnable runnable2 = () -> {
                for (int i = 0; i < middle; i++) {
                    second[i] = getNextNumber(second[i], i + middle);
                }
            };

            Thread thread1 = new Thread(runnable1);
            Thread thread2 = new Thread(runnable2);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            System.arraycopy(first, 0, arr, 0, middle);
            System.arraycopy(second, 0, arr, middle, middle);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        System.out.println(System.currentTimeMillis() - start);
    }

    public static void withoutMultithreading() {
        float[] arr = createArray();

        long start = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = getNextNumber(arr[i], i);
        }
        System.out.println(System.currentTimeMillis() - start);

    }

    private static float[] createArray() {
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        return arr;
    }

    private static float getNextNumber(float lastNumber, int i) {
        return (float) (lastNumber * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}
