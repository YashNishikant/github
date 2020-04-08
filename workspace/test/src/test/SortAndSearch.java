package test;
/*  Modify the code below to add a sort algorithm (selection or insertion)
    Then write a Binary Search and print out how many comparisons it takes
    to find 13.  I have already written code to print the original array,
    the sorted array, and the end result.
*/
public class SortAndSearch{

	public static void main(String[]args){
		int[] arr = {13,7,9,15,4,18,2,7,14,6,1,17,0,23};


		System.out.print("Original Array:\t"); // Print original array
		for (int i = 0; i <arr.length; i++)
			System.out.print(arr[i]+" ");
		System.out.println();


		/***   Write a SELECTION SORT or INSERTION SORT Here.  Be sure to indicate which one you choose ***/

		//SELECTION SORT (increasing)
	
		for(int i = 0; i <arr.length - 1; i++) {
			
			int temp = arr[i];			
			
			int valuemin = arr[i + 1];
			int indexmin = i + 1;
			for(int j = i + 1; j <arr.length; j++) {
			
				if(arr[j] < valuemin) {		
					valuemin = arr[j];
					indexmin = j;	
				}
			}
			
			if(arr[i] > valuemin) {
			arr[i] = valuemin;	
			arr[indexmin] = temp;
			}
			
		}
		
		
		//END
		System.out.print("Sorted Array:\t");  // Print again
				for (int k = 0; k <arr.length; k++)
					System.out.print(arr[k]+" ");
		System.out.println();



		/***   Write a BINARY SEARCH Here TO FIND '13'.  Count the number of times you try to find it
	           In oher words, increment a counter each time before the line  if (array[mid] == 13 )   ***/

		int count = 0;
		int target = 13;
		int low = 0;
		int mid = 0;
		int high = arr.length-1;
		
		while(low < high) {
		
			mid = ((low + high)/2);
			count++;
			if(target == arr[mid]) {		
				break;
			}

			if(target > arr[mid]) {				
			low = mid + 1;				
			}

			if(target < arr[mid]) {		
			high = mid - 1;					
			}
			
		}	
		
		if(count > 1) {
		System.out.print("Found 13 after "+count+" steps ");
		System.out.println("\n");
		}
		
		if(count == 1) {	
		System.out.print("Found 13 after "+count+" step ");
		System.out.println("\n");	
		}
		
		/* Upload completed code with screenshot when completed  */
	}

}