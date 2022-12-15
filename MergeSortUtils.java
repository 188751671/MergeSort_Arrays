/**
 * @author Dear Ace
 */
public class MergeSortUtils
{
    /*
     * merge method for Task 1.1
     */
    public static int[] merge(int[] a, int[] b) {

        // new merged array
        int[] c = new int[a.length+b.length];

        /* Merge the arrays */
        int c_index = 0;
        int a_index = 0, b_index = 0;
        while (a_index < a.length && b_index < b.length) {
            if (a[a_index] <= b[b_index]) {
                c[c_index] = a[a_index];
                a_index++;
            }
            else {
                c[c_index] = b[b_index];
                b_index++;
            }
            c_index++;
        }



        /* Copy remaining elements of a[] if any */
        while (a_index < a.length) {
            c[c_index] = a[a_index];
            a_index++;
            c_index++;
        }

        /* Copy remaining elements of b[] if any */
        while (b_index < b.length) {
            c[c_index] = b[b_index];
            b_index++;
            c_index++;
        }

        return c;
    }
    /*
     * mergesort method for Task 1.2
     */
    public static int[] mergesort(int[] arr) {

        if (arr.length == 1)
            return arr;

        // divide into a[]  b[]
        int mid = arr.length / 2;
        int[] a = new int[mid];
        int[] b = new int[arr.length-mid];

        for (int i = 0; i < mid; i++) {
            a[i] = arr[i];
        }

        for (int i = 0; i < arr.length - mid; i++) {
            b[i] = arr[i+mid];
        }

        return merge( mergesort(a) , mergesort(b) );
    }
    private static int compare(int a1, int a2){
        if (a1<=a2){
            return a1;
        }else {
            return a2;
        }
    }
    private static int compare(int a1, int a2, int a3){
        int result = compare(a1,a2);
        if (result<=a3)
            return result;
        else
            return a3;
    }
    private static void copyRemainder(int[] fromArray, int index_From, int[] toArray, int index_ToArray){
        for (int i=index_From; i<fromArray.length; i++){
            toArray[index_ToArray] = fromArray[i];
        }
    }
    /*
     * merge3 method for Task 1.3
     */
    public static int[] merge3(int[] a, int[] b, int[] c) {

        // new merged array
        int[] d = new int[a.length+b.length+c.length];

        // Merge the arrays
        int a_index = 0, b_index = 0, c_index =0;
        for (int d_index = 0; d_index < d.length; d_index++) {

            if (a_index != a.length) {
                if (b_index != b.length) {
                    if (c_index != c.length) {
                        d[d_index] = compare(a[a_index], b[b_index], c[c_index]);    //  A B C
                        if (d[d_index] == a[a_index])
                            a_index++;
                        else if (d[d_index] == b[b_index])
                            b_index++;
                        else
                            c_index++;
                    } else {
                        d[d_index] = compare(a[a_index], b[b_index]);                       //  A B
                        if (d[d_index] == a[a_index])
                            a_index++;
                        else
                            b_index++;
                    }
                } else {
                    if (c_index != c.length) {
                        d[d_index] = compare(a[a_index], c[c_index]);                      // A C
                        if (d[d_index] == a[a_index])
                            a_index++;
                        else
                            c_index++;
                    } else {
                        copyRemainder(a, a_index,d,d_index);                                               // loop A
                    }
                }
            } else {
                if (b_index != b.length) {
                    if (c_index != c.length) {
                        d[d_index] = compare(b[b_index], c[c_index]);                      // B C
                        if (d[d_index] == b[b_index])
                            b_index++;
                        else
                            c_index++;
                    } else {
                        copyRemainder(b, b_index,d,d_index);                                               // loop B
                    }
                } else {
                    copyRemainder(c, c_index,d,d_index);                                                   // loop C
                }
            }

        }
        return d;
    }

    /*
     * mergesort3 method for Task 1.4
     */
    public static int[] mergesort3(int[] arr) {

        if (arr.length == 1)
            return arr;
        if (arr.length == 2){
            if (arr[0] > arr[1]){
                int temp = arr[0];
                arr[0] = arr[1];
                arr[1] = temp;
                return arr;
            }else return arr;
        }


        // divide into a[]  b[]  c[]
        int a_Length = arr.length / 3;
        int b_Length = (arr.length-a_Length)/2;
        int c_Length = arr.length - a_Length - b_Length;

        int[] a = new int[a_Length];
        int[] b = new int[b_Length];
        int[] c = new int[c_Length];

        for (int i = 0; i < a_Length; i++) {
            a[i] = arr[i];
        }

        for (int i = 0; i < b_Length; i++) {
            b[i] = arr[a_Length + i];
        }

        for (int i = 0; i < c_Length; i++) {
            c[i] = arr[arr.length - c_Length + i];
        }

        return merge3( mergesort(a) , mergesort(b) , mergesort(c));
    }

    /*
     * mergeAll method for Task 1.5
     */
    public static int[] mergeAll(int[][] arrays) {              // mergesort a number of arrays ( every array has the same length )

        int numberOfArrays = arrays.length;
        int arrayLength = arrays[0].length;
        int[] all = new int[numberOfArrays * arrayLength];
        int[] indices = new int[numberOfArrays];                    // 为每个数组创建一个 index下标 (从0往上走到array.length)
                                                                    // 当index==arrayslength时, 表示该数组 已经完成了 不参与后续的比较 (element已经全部被复制到 新数组了)
        for (int i=0; i< all.length; i++){

            for (int j=0; j<numberOfArrays; j++){
                boolean foundOne = true;

                if (indices[j] == arrayLength) continue;
                for (int k=0; k<numberOfArrays; k++){
                    if (j==k) continue;
                    if (indices[k] == arrayLength) continue;
                    if(arrays[j][indices[j]] > arrays[k][indices[k]]){
                        foundOne = false;
                        break;
                    }
                }
                if (foundOne == true){
                    all[i] = arrays[j][indices[j]];
                    indices[j]++;
                    break;
                }
            }

        }

        return all;
    }

    /*
     * mergesortK method for Task 1.6
     */
    public static int[] mergesortK(int[] arr, int k) {
        if (arr.length == 1)
            return arr;
        if (arr.length < k){
            return mergesort(arr);
        }

        int[] length_table = new int[k];

        int quotient = arr.length / k;
        int remainder = arr.length % k;

        for (int i=0; i<k; i++){
            if (remainder!=0) {
                length_table[i] = quotient + 1;         // Equally distribute remainder
                remainder--;
            } else
                length_table[i] = quotient;
        }

        // Create arrays and Copy data
        int[][] arrays = new int[length_table.length][];    // create 1-dimension array
        int index_of_arr=0;
        for (int i=0; i<length_table.length; i++){
            arrays[i] = new int[length_table[i]];           // create 2-dimension array
            for (int j=0; j<length_table[i]; j++){
                arrays[i][j] = arr[index_of_arr];
                index_of_arr++;
            }
        }


        for (int i=0; i<k; i++){
            arrays[i] = mergesortK(arrays[i],k);
        }
        return mergeAll(arrays);
    }

    /*
     * maxResources method for Task 2.1
     */
    private static int maxResources(int[][] mine, int x, int y){
        int length = mine[0].length;  // 5
        int depth = mine.length;      // 5

        int left,right;
        if (x>0 && y+1<depth){                          // get the left-below
            left = maxResources(mine,x-1,y+1);
        }else
            left=-1;

        if (x+1<length && y+1<depth){                   // get the right-below
            right = maxResources(mine,x+1,y+1);
        }else
            right=-1;


        if (left!=-1 && right!=-1){     // both Not-1
            if (left>right)
                return mine[y][x] + left;
            else
                return mine[y][x] + right;
        }else {
            if (left==-1 && right==-1)   return mine[y][x];          // both -1
            else if (left==-1)  return mine[y][x] + right;           // only left -1
            else return mine[y][x] + left;                           // only right-1
        }

    }
    public static int maxResources(int[][] mine) {

        int[] results = new int[mine.length];
        for (int i=0; i<mine.length;i++){
            results[i] = maxResources(mine,i,0);
        }

        // find the biggest one in results
        for (int i=0; i<results.length; i++){
            if (i==results.length-1)   return results[results.length-1]; // reaching the last item, no others to compare with

            boolean foundOne = true;
            for (int k=i+1; k<results.length; k++){
                if (results[i]<results[k]){
                    foundOne = false;
                }
            }
            if (foundOne==true){
                return results[i];
            }
        }
        return -1; // something wrong if reaching here
    }

    /*
     * maxResources method for Task 2.2
     *   0      [91,82,55,25,10]
         1      [99,75,49,37,21]
         2      [80,63,32,48,51]
         3      [40,36,47,52,64]
         4      [12,27,33,71,82]
     */
    private static int maxResourcesM(int[][] mine, int[][] sumsMap, int x, int y){
        int length = mine[0].length;  // 5
        int depth = mine.length;      // 5

        int left,right;
        if (x>0 && y+1<depth){                          // get the left-below
            if (sumsMap[x-1][y+1]!=-1)
                left = sumsMap[x-1][y+1];
            else{
                left = maxResources(mine,x-1,y+1);
                sumsMap[x-1][y+1] = left;
            }
        }else
            left=-1;

        if (x+1<length && y+1<depth){                   // get the right-below
            if (sumsMap[x+1][y+1]!=-1)
                right = sumsMap[x+1][y+1];
            else{
                right = maxResources(mine,x+1,y+1);
                sumsMap[x+1][y+1] = left;
            }
        }else
            right=-1;


        if (left!=-1 && right!=-1){     // both Not-1
            if (left>right)
                return mine[y][x] + left;
            else
                return mine[y][x] + right;
        }else {
            if (left==-1 && right==-1)   return mine[y][x];          // both -1
            else if (left==-1) {
                sumsMap[y][x] = mine[y][x] + right;
                return sumsMap[y][x];                          // only left -1
            } else {
                sumsMap[y][x] = mine[y][x] + left;
                return sumsMap[y][x];                           // only right-1
            }
        }
    }
    public static int maxResourcesM(int[][] mine) {

        int[][] sumsMap = new int[mine.length][mine[0].length];
        for (int i=0; i<sumsMap.length; i++)
            for (int k=0;k<sumsMap[0].length;k++)
                sumsMap[i][k] = -1;

        int[] results = new int[mine.length];
        for (int i=0; i<mine.length;i++){
            results[i] = maxResourcesM(mine,sumsMap,i,0);
        }

        // find the biggest one in results
        for (int i=0; i<results.length; i++){
            if (i==results.length-1)   return results[results.length-1]; // reaching the last item, no others to compare with

            boolean foundOne = true;
            for (int k=i+1; k<results.length; k++){
                if (results[i]<results[k]){
                    foundOne = false;
                }
            }
            if (foundOne==true){
                return results[i];
            }
        }
        return -1; // something wrong if reaching here
    }

    /*
     * Main method for testing
     * Includes some basic tests for each of the methods required by tasks
     * You can use this method to check your methods before submitting
     */
    public static void main(String[] args) {
        System.out.println("Beginning testing");

        System.out.println("   Testing merge method:");

        try {
            int[] a = new int[] {1,3,5};
            int[] b = new int[] {2,4,6};
            int[] expected = new int[] {1,2,3,4,5,6};
            int[] result = MergeSortUtils.merge(a, b);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Inputs were [1,3,5] and [2,4,6]");
                System.out.println("     Expected output [1,2,3,4,5,6]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing mergesort method:");

        try {
            int[] arr = new int[] {3,6,4,1,5,2};
            int[] expected = new int[] {1,2,3,4,5,6};
            int[] result = MergeSortUtils.mergesort(arr);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [3,6,4,1,5,2]");
                System.out.println("     Expected output [1,2,3,4,5,6]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing merge3 method:");

        try {
            int[] a = new int[] {1,4,7};
            int[] b = new int[] {2,5,8};
            int[] c = new int[] {3,6,9};
            int[] expected = new int[] {1,2,3,4,5,6,7,8,9};
            int[] result = MergeSortUtils.merge3(a, b, c);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Inputs were [1,4,7], [2,5,8] and [3,6,9]");
                System.out.println("     Expected output [1,2,3,4,5,6,7,8,9]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing mergesort3 method:");

        try {
            int[] arr = new int[] {8,3,6,2,9,5,1,7,4};
            int[] expected = new int[] {1,2,3,4,5,6,7,8,9};
            int[] result = MergeSortUtils.mergesort3(arr);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [8,3,6,2,9,5,1,7,4]");
                System.out.println("     Expected output [1,2,3,4,5,6,7,8,9]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing mergeAll method:");

        try {
            int[] a = new int[] {1,6,11,16,21};
            int[] b = new int[] {2,7,12,17,22};
            int[] c = new int[] {3,8,13,18,23};
            int[] d = new int[] {4,9,14,19,24};
            int[] e = new int[] {5,10,15,20,25};
            int[][] arrs = new int[][] {a,b,c,d,e};
            int[] expected = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
            int[] result = MergeSortUtils.mergeAll(arrs);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Inputs were [1,6,11,16,21], [2,7,12,17,22], [3,8,13,18,23], [4,9,14,19,24] and [5,10,15,20,25]");
                System.out.println("     Expected output [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing mergesortK method:");

        try {
            int[] arr = new int[] {10,4,11,15,2,14,6,8,1,12,3,16,7,9,13,5};
            int[] expected = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
            int[] result = MergeSortUtils.mergesortK(arr,4);
            boolean testPassed = expected.length == result.length;
            if(testPassed)
                for(int i = 0; i < expected.length; i++)
                    if(expected[i] != result[i]) {
                        testPassed = false;
                        break;
                    }
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [10,4,11,15,2,14,6,8,1,12,3,16,7,9,13,5] with K=4");
                System.out.println("     Expected output [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]");
                System.out.print("     But got [");
                for(int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if(i < result.length - 1)
                        System.out.print(",");
                }
                System.out.println("]");
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing maxResources method:");

        try {
            int[][] mine = new int[][] {new int[] {91,82,55,25,10},
                    new int[] {99,75,49,37,21},
                    new int[] {80,63,32,48,51},
                    new int[] {40,36,47,52,64},
                    new int[] {12,27,33,71,82}
            };
            int expected = 362;
            int result = MergeSortUtils.maxResources(mine);
            boolean testPassed = expected == result;
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [[91,82,55,25,10]");
                System.out.println("                [99,75,49,37,21]");
                System.out.println("                [80,63,32,48,51]");
                System.out.println("                [40,36,47,52,64]");
                System.out.println("                [12,27,33,71,82]]");
                System.out.println("     Expected output 362");
                System.out.println("     But got " + result);
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("   Testing maxResourcesM method:");

        try {
            int[][] mine = new int[][] {new int[] {91,82,55,25,10},
                    new int[] {99,75,49,37,21},
                    new int[] {80,63,32,48,51},
                    new int[] {40,36,47,52,64},
                    new int[] {12,27,33,71,82}
            };
            int expected = 362;
            int result = MergeSortUtils.maxResourcesM(mine);
            boolean testPassed = expected == result;
            if(testPassed)
                System.out.println("     [V] Test passed!");
            else {
                System.out.println("     [X] Test failed.");
                System.out.println("     Input was [[91,82,55,25,10]");
                System.out.println("                [99,75,49,37,21]");
                System.out.println("                [80,63,32,48,51]");
                System.out.println("                [40,36,47,52,64]");
                System.out.println("                [12,27,33,71,82]]");
                System.out.println("     Expected output 362");
                System.out.println("     But got " + result);
            }
        }
        catch(Exception e) {
            String exceptionName = e.getClass().getSimpleName();
            System.out.println("     [X] Test failed with " + exceptionName);
        }
        catch(StackOverflowError e){
            System.out.println("     [X] Test failed with a StackOverflowError");
        }

        System.out.println("Finished testing");
    }


}