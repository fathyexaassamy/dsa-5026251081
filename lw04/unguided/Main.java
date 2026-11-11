import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(
                Main.class.getResourceAsStream("parcels.txt")
        );

        int n = sc.nextInt();
        int[] parcels = new int[n];

        for (int i = 0; i < n; i++) {
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        int[] afterTwoPasses = new int[n];

        bubbleSortDescending(parcels, afterTwoPasses);

        System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);

        System.out.println();
        System.out.println("==== Fully Sorted ====");
        printArray(parcels);

        System.out.println();
        System.out.println("==== Search Results ====");

        for (int i = 0; i < m; i++) {
            int[] result = binarySearchDescending(parcels, targets[i]);
            int index = result[0];
            int steps = result[1];

            if (index != -1) {
                System.out.println(targets[i] + ": FOUND at index "
                        + index + " (steps: " + steps + ")");
            } else {
                System.out.println(targets[i] + ": NOT FOUND (steps: "
                        + steps + ")");
            }
        }
    }
    
    public static void bubbleSortDescending(
            int[] arr, int[] afterTwoPasses) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (i == 1) {
                for (int k = 0; k < n; k++) {
                    afterTwoPasses[k] = arr[k];
                }
            }
        }
    }

    public static int[] binarySearchDescending(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (arr[mid] == target) {
                return new int[] { mid, steps };
            }

            if (target > arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[] { -1, steps };
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}
