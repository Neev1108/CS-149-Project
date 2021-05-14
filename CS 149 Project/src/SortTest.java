import java.util.concurrent.*;

import java.util.*;

public class SortTest {
    
    public static void main(String args[]){
        ForkJoinPool pool = new ForkJoinPool();
        int [] arr = randomArray(55000);
        int [] before_array = arr;
        int [] before_before_array = arr;


        System.out.print("\nArray before sorted: \n\n");
        // printArray(arr);
        
        SortingThreads task = new SortingThreads(arr, 0, arr.length-1, pool);
        task.sort();

        long begin1 = System.currentTimeMillis();
        MergeSort(before_array, 0, arr.length-1);
        long finish1 = System.currentTimeMillis();
        long timeElapsed1 = finish1 - begin1;
        System.out.println("Standard Merge Sort Time: " + timeElapsed1 + "ms\n");
        
        long begin2 = System.currentTimeMillis();
        quickSort(before_before_array, 0, before_before_array.length-1);
        long finish2 = System.currentTimeMillis();
        long timeElapsed2 = finish2 - begin2;
        System.out.println("Standard Quick Sort Time: " + timeElapsed2 + "ms\n");
        
    }

    public static int[] randomArray(int n) {
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static void printArray(int [] array){
        for (int i= 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("\n");
    }


    public static int[]  merge(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
 
        int L[] = new int[n1];
        int R[] = new int[n2];
        int [] result = new int[arr.length];
 
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
            int i = 0, j = 0;
            int k = 0;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    result[k] = L[i];
                    i++;
                }
                else {
                    result[k] = R[j];
                    j++;
                }
                k++;
            }
            /* Copy remaining elements of L[] if any */
            while (i < n1) {
                result[k] = R[i];
                i++;
                k++;
            }
            /* Copy remaining elements of R[] if any */
            while (j < n2) {
                result[k] = R[j];
                j++;
                k++;
            }
            return result;
    }
 
    public static void MergeSort(int arr[], int l, int r)
    {
        if (l < r) {
            int m =l+ (r-l)/2;
            MergeSort(arr, l, m);
            MergeSort(arr, m + 1, r);
            arr = merge(arr, l, m, r);
        }
    }

    static void quickSort(int[] arr, int low, int high)
{
    if (low < high)
    {
        int pivot = partition(arr, low, high);
        quickSort(arr, low, pivot - 1);
        quickSort(arr, pivot + 1, high);
    }
}

static int partition(int[] arr, int low, int high)
{
    int pivot = arr[high];
    int i = (low - 1);
 
    for(int j = low; j <= high - 1; j++)
    {
        if (arr[j] < pivot)
        {
            i++;
            swap(arr, i, j);
        }
    }
    swap(arr, i + 1, high);
    return (i + 1);
}

static void swap(int[] arr, int i, int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}


    }   



