public class Assignment3 {

     /**
     * Input:
     *      array A of at least two integers, integer s
     * Output:
     *    true if Aa[i]+A[j] = s for two distinct i, j 
     *    false otherwise
     * 
     * Implements rigjtmost binary search, because in the case of duplicates
     * I have to be sure that the returned index is of an element that is distinct
     * from the array element i am currently considering
     */
    public static boolean hasMatchingPair(int[] A, int s) {
        if (A.length == 1 && A[0] == s) return true;

        mergeSort(A);
        for (int i=0; i < A.length; i++) {
            int dif = s - A[i];
            int difIndex = binarySearch(A, dif);
            if (difIndex >= 0 && difIndex != i) return true;
        }
        // No matching pair found
        return false;
    }

     /**
     * Input:
     *      array A of integers
     * Output:
     *      number of violations in A
     */
    public static int violations(int[] A){
        return -1;
    }

    /**
     * Input:
     *      array A of integers
     * Output:
     *      maximal stable production for A
     */
    public static int stableProduction(int[] A){
        return stableProduction(A, 0, A.length-1);
    }
    private static int stableProduction(int[] A, int l, int r) {
        if (l == r) return A[l];

        int middle = (l+r) / 2;
        int stableProductionLeft = stableProduction(A, l, middle);
        int stableProductionRight = stableProduction(A, middle+1, r);
        int stableProductionMiddle = stableProductionMiddle(A, middle, l, r);

        int maximumStableProduction =  maximum(stableProductionLeft, stableProductionRight);
        maximumStableProduction = maximum(maximumStableProduction, stableProductionMiddle);

        return maximumStableProduction;
    }
    private static int stableProductionMiddle(int[] A, int middle, int l, int r){
        int maximumStableProduction = 0;
        int middleLeft = middle;
        int middleRight = middle+1;
        int currentMinimumProduction = minimum(A[middleLeft], A[middleRight]);

        while (middleLeft >= l && middleRight <= r) {
            if (A[middleLeft] < currentMinimumProduction) currentMinimumProduction = A[middleLeft];
            if (A[middleRight] < currentMinimumProduction) currentMinimumProduction = A[middleRight];

            int localMaximumStableProduction = (middleRight-middleLeft + 1) * currentMinimumProduction;
            maximumStableProduction = maximum(maximumStableProduction, localMaximumStableProduction);

            if (middleLeft == l) middleRight++;
            else if (middleRight == r) middleLeft--;
            else {
                if (A[middleLeft-1] >= A[middleRight+1]) middleLeft--;
                else middleRight++;
            }
        }

        return maximumStableProduction;
    }


    // Utilities

    /**
     * Finds element in array, rightmost if duplicate,
     * returns -1 if x is not found
     * 
     * Time Complexty: BT(log n)
     */
    public static int binarySearch(int[]A, int x) {
        return binarySearch(A, x, 0, A.length);
    }
    private static int binarySearch(int[] A, int x, int l, int r) {
        if (l >= r) return (r>0 && A[r-1] == x) ? r-1 : -1;

        int m = (l+r) / 2;
        if (A[m] > x) return binarySearch(A, x, l, m);
        return binarySearch(A, x, m+1, r);
    }

    public static void mergeSort(int[] A) {
        mergeSort(A, 0, A.length - 1);
    }
    private static void mergeSort(int[] A, int l, int r) {
        if (l >= r) return;

        int middle = (l+r) / 2;
        mergeSort(A, l, middle);
        mergeSort(A, middle + 1, r);
        merge(A, middle, l, r);
    }
    private static void merge(int[] A, int middle, int l, int r) {
        int[] temp = new int[r-l + 1];

         // Two-pointer sorting
        int leftIndex = l;
        int rightIndex = middle + 1;
        int i = 0;
        while (leftIndex <= middle && rightIndex <= r) {
            if (A[leftIndex] <= A[rightIndex]) {
                temp[i] = A[leftIndex];
                leftIndex++;
            } else {
                temp[i] = A[rightIndex];
                rightIndex++;
            }

            i++;
        };

        // Remaining insertion
        while (leftIndex <= middle) {
            temp[i] = A[leftIndex];
            i++; leftIndex++;
        }
        while (rightIndex <= r) {
            temp[i] = A[rightIndex];
            i++; rightIndex++;
        }

        // Original array replacement
        for(int j=0; j < temp.length; j++) {
            A[l+j] = temp[j];
        }
    }
}
