
import org.junit.Assert;

import java.util.concurrent.ForkJoinPool;

import org.junit.Test;

public class algo_test {
    @Test
    public void test_multithreaded_qs(){
        ForkJoinPool pool = new ForkJoinPool();
        int [] before_sorted = new int[]{9,3,2,4,7,8,1,0,6,5};
        int [] after_sorted = new int[]{0,1,2,3,4,5,6,7,8,9};

        QuickSortTask task = new QuickSortTask(before_sorted, 0, before_sorted.length-1);
        pool.execute(task);
        task.join();

        Assert.assertArrayEquals(before_sorted,after_sorted);
    }

    @Test 
    public void test_normal_qs(){
        int [] before_sorted = new int[]{9,3,2,4,7,8,1,0,6,5};
        int [] after_sorted = new int[]{0,1,2,3,4,5,6,7,8,9};
        quickSort(before_sorted, 0, before_sorted.length-1);
        Assert.assertArrayEquals(before_sorted,after_sorted);
    }

    @Test
    public void test_multithreaded_ms(){
        ForkJoinPool pool = new ForkJoinPool();
        int [] before_sorted = new int[]{9,3,2,4,7,8,1,0,6,5};
        int [] after_sorted = new int[]{0,1,2,3,4,5,6,7,8,9};

        MergeSortTask task = new MergeSortTask(before_sorted, 0, before_sorted.length-1);
        pool.execute(task);
        task.join();

        Assert.assertArrayEquals(before_sorted,after_sorted);
    }

    @Test 
    public void test_normal_ms(){
        int [] before_sorted = new int[]{9,3,2,4,7,8,1,0,6,5};
        int [] after_sorted = new int[]{0,1,2,3,4,5,6,7,8,9};
        quickSort(before_sorted, 0, before_sorted.length-1);
        Assert.assertArrayEquals(before_sorted,after_sorted);
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
}
