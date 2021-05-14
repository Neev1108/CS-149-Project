import java.util.concurrent.*;

public class QuickSortTask extends RecursiveAction {
    int[] array;
    int left;
    int right;
    int threshold;

    public QuickSortTask(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
        this.threshold = array.length/5;
    }

    protected void compute() {
        if (right - left <= threshold) {
            InsertionSort(array);
        } else {
            if (left < right){
            int pivot = partition(array, left, right);

            QuickSortTask subTask1 = new QuickSortTask(array, left, pivot - 1);
            QuickSortTask subTask2 = new QuickSortTask(array, pivot + 1, right);

            invokeAll(subTask1,subTask2);
        }

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

      public void InsertionSort(int [] arr){
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
    }

}
