import java.util.concurrent.*;
import java.util.*;

public class SortTest {
    
    public static void main(String args[]){
        ForkJoinPool pool = new ForkJoinPool(2);
        int [] arr = randomArray(1000);

        System.out.print("\nArray before sorted: \n\n");
        printArray(arr);
        
        SortingThreads task = new SortingThreads(arr, 0, arr.length-1, pool);
        task.sort();

        System.out.println("Array after full sorting: \n");
        printArray(task.result);

        
    }

    public static int[] randomArray(int n) {
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    public static void printArray(int [] array){
        for (int i= 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("\n");
    }
}
