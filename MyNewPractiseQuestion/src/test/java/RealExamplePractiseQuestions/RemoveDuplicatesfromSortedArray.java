package RealExamplePractiseQuestions;

public class RemoveDuplicatesfromSortedArray {
	
	// Method to remove duplicates from sorted array
    public int removeDuplicates(int[] nums) {

        // Initialize a pointer to keep track of the last unique element's index
        int expectedNums = 0;

        // Traverse the array starting from the second element
        for (int i = 1; i < nums.length; i++) {

            // If the current element is not equal to the last unique one
            if (nums[expectedNums] != nums[i]) {

                // Move the pointer to the next index
                expectedNums++;

                // Copy the current unique element to the new position
                nums[expectedNums] = nums[i];
            }
        }

        // Return the count of unique elements (index + 1)
        return expectedNums + 1;
    }

    // Main method to test the function
    public static void main(String[] args) {
        // Creating an object of the class
    	RemoveDuplicatesfromSortedArray solution = new RemoveDuplicatesfromSortedArray();

        // Input array (must be sorted)
        int[] nums = {1, 1, 2, 2, 3, 3, 4, 5, 5};

        // Calling the method and storing the result
        int k = solution.removeDuplicates(nums);

        // Printing the number of unique elements
        System.out.println("Number of unique elements: " + k);

        // Printing the unique elements in the modified array
        System.out.print("Unique elements: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}



