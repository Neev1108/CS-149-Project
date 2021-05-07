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
        int middle = (end + start) / 2;
        int n1 = middle - start + 1;
        int n2 = end - middle;
        int[] L = new int[n1];
        int[] R = new int[n2];
        int[] sorted_array = new int[end + 1];

        for (int i = 0; i < n1; ++i)
            L[i] = array[start + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[middle + 1 + j];

        System.out.println("Left Subarray (QuickSort) before Sorted: \n");
        printArray(L);
        QuickSortTask task1 = new QuickSortTask(L, 0, L.length - 1);
        System.out.println("Right Subarray (MergeSort) before Sorted: \n");
        printArray(R);
        MergeSortTask task2 = new MergeSortTask(R, 0, R.length - 1);

        pool.execute(task1);
        task1.join();
        pool.execute(task2);
        task2.join();

        System.out.println("Left Subarray (QuickSort) after Sorted: \n");
        printArray(L);

        System.out.println("Right Subarray (MergeSort) after Sorted: \n");
        printArray(R);

        result = mergetwoArrays(L, R, sorted_array);
    }

    public int[] mergetwoArrays(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0;
        
        int middle = (end + start) / 2;
        int n1 = middle - start + 1;
        int n2 = end - middle;
        // Initial index of merged subarry array
        int k = 0;
        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                arr3[k] = arr1[i];
                i++;
            }
            else {
                arr3[k] = arr2[j];
                j++;
            }
            k++;
        }
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr3[k] = arr2[i];
            i++;
            k++;
        }
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr3[k] = arr2[j];
            j++;
            k++;
        }
        return arr3;
    }

    public int[] InsertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println('\n');
    }

}