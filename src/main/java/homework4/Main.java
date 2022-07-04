package homework4;

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
