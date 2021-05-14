import java.util.concurrent.*;

// https://www.codejava.net/java-core/concurrency/understanding-java-fork-join-framework-with-examples
public class SortingThreads {
    int[] array;
    int start;
    int end;
    int threshold = 5;
    ForkJoinPool pool;
    int result[];

    SortingThreads(int[] array, int start, int end, ForkJoinPool pool) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.pool = pool;
    }

    protected void sort() {

        int [] before_array = array;
        long begin = System.currentTimeMillis();    
        MergeSortTask task1 = new MergeSortTask(array, 0, array.length - 1);
        pool.execute(task1);
        task1.join();
        long finish = System.currentTimeMillis();

        long timeElapsed = finish - begin;
        System.out.println("Multithreaded Merge Sort (After Sorted): \n");
        //printArray(array);
        System.out.println("Time Elapsed for MultiThreaded Merge Sort: " + timeElapsed + "ms\n");

        long begin1 = System.currentTimeMillis();    
        QuickSortTask task2 = new QuickSortTask(before_array, 0, before_array.length - 1);
        pool.execute(task2);
        task2.join();
        long finish1 = System.currentTimeMillis();
        long timeElapsed1 = finish1 - begin1;
        // System.out.println("Multithreaded Quick Sort (After Sorted): \n");
        // printArray(before_array);
        System.out.println("Time Elapsed for MultiThreaded Quick Sort: " + timeElapsed1 + "ms\n");

    }

    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println('\n');
    }

}