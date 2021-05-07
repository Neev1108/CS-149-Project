import java.util.concurrent.*;

public class QuickSortTask extends RecursiveAction {
    int[] array;
    int left;
    int right;
    int threshold = 100;

    public QuickSortTask(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    protected void compute() {
        if (right - left <= threshold) {
            this.array = InsertionSort(array);
        } else {
            int pivot = partition(array, left, right);

            QuickSortTask subTask1 = new QuickSortTask(array, left, pivot - 1);
            QuickSortTask subTask2 = new QuickSortTask(array, pivot + 1, right);
            invokeAll(subTask1, subTask2);
            subTask1.fork();
            subTask2.compute();
            subTask1.join();

        }
    }

    int partition(int[] a, int p, int r) {
        int i = p - 1;
        int x = a[r];
        for (int j = p; j < r; j++) {
          if (a[j] < x) {
            i++;
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
          }
        }
        i++;
        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;
        return i;
      }

      public int[] InsertionSort(int [] arr){
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

}
