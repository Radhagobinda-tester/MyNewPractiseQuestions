package RealExamplePractiseQuestions;

public class RemoveDuplicatesfromSortedArrayII {
	
	// Method to remove extra duplicates allowing at most two occurrences
    public int removeDuplicates(int[] nums) {

        // If array has less than 3 elements, return length directly (no extra duplicates possible)
        if (nums.length < 3) return nums.length;

        // Initialize a pointer 'n' starting from index 2 (first two elements always allowed)
        int n = 2;

        // Traverse the array from index 2 onwards
        for (int i = 2; i < nums.length; i++) {

            // If current element is not equal to the element at position n-2
            // That means it has appeared less than twice so far
            if (nums[i] != nums[n - 2]) {

                // Copy current element to position 'n'
                nums[n] = nums[i];

                // Increment the position pointer
                n++;
            }
        }

        // Return the new length of the modified array
        return n;
    }

    // Main method to test the removeDuplicates method
    public static void main(String[] args) {

        // Create an object of the Solution class
    	RemoveDuplicatesfromSortedArrayII solution = new RemoveDuplicatesfromSortedArrayII();

        // Sample input array (must be sorted)
        int[] nums = {1, 1, 1, 2, 2, 3};

        // Call the method and store the new length
        int k = solution.removeDuplicates(nums);

        // Print the number of elements after removing extra duplicates
        System.out.println("Length after removing extra duplicates: " + k);

        // Print the modified array (only first k elements are valid)
        System.out.print("Modified array: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }

}
