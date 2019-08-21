import java.util.Arrays;

public class Proj01_MergeSort implements Proj01_Sort {

	private boolean debug;
	private int baseLen;
	private Proj01_InsertionSort iSort;

	public Proj01_MergeSort(boolean debug, int baseLen) {
		this.debug = debug;
		this.baseLen = baseLen;
		iSort = new Proj01_InsertionSort(false); // Override InsertionSort debug info
	}

	@Override
	public void sort(Comparable[] arr) {
		if (debug) {
			System.out.println("baseLen: " + baseLen + "\n");
		}
		mergeSort(arr, 0, arr.length - 1);
	}

	private void mergeSort(Comparable[] arr, int startIndex, int endIndex) {
		// BASE CASE:
		// if we've got a small enough piece, sort using InsertionSort
		if (endIndex - startIndex <= baseLen) {
			if (debug) {
				System.out.printf("Sorting current block (index %d to %d): ", startIndex, endIndex);
				printSegment(arr, startIndex, endIndex);
			}
			iSort.sortSection(arr, startIndex, endIndex);
		} else {
			// RECURSIVE CASE:
			// Split current piece in half, recurse into new piece. Merge gets called at the
			// bottom

			int splitIndex = (startIndex + endIndex) / 2;
			mergeSort(arr, startIndex, splitIndex);
			mergeSort(arr, splitIndex + 1, endIndex);
			merge(arr, startIndex, splitIndex, endIndex);
		}
	}

	private void merge(Comparable[] arr, int startIndex, int splitIndex, int endIndex) {
		// Create 2 temporary arrays and fill them with the contents of the 2 sorted
		// pieces.
		Comparable[] a1 = new Comparable[splitIndex - startIndex + 1];
		Comparable[] a2 = new Comparable[endIndex - splitIndex];
		int a1Index = 0;
		int a2Index = 0;
		for (int i = startIndex; i <= splitIndex; i++) {
			a1[a1Index] = arr[i];
			a1Index++;
		}
		for (int i = splitIndex + 1; i <= endIndex; i++) {
			a2[a2Index] = arr[i];
			a2Index++;
		}
		if (debug) {
			System.out.println("Merging these two blocks...");
			System.out.println(Arrays.toString(a1));
			System.out.println(Arrays.toString(a2));
		}

		// Iterate through the two temp arrays, merging their values and putting them
		// back into the original
		a1Index = 0;
		a2Index = 0;
		for (int i = startIndex; i <= endIndex; i++) {
			// If we're out of values in one temp array, only get values from the other
			if (a1Index >= a1.length) {
				arr[i] = a2[a2Index];
				a2Index++;
			} else if (a2Index >= a2.length) {
				arr[i] = a1[a1Index];
				a1Index++;
			} else { // Otherwise, put smallest value from either temp back into original array
				if (a1[a1Index].compareTo(a2[a2Index]) < 0) {
					arr[i] = a1[a1Index];
					a1Index++;
				} else {
					arr[i] = a2[a2Index];
					a2Index++;
				}
			}
		}
		if (debug) {
			System.out.println("...into:");
			printSegment(arr, startIndex, endIndex);
			System.out.println();
		}

	}
	
	private void printSegment(Comparable[] arr, int startIndex, int endIndex) {
		String s = "[";
		for (int i = startIndex; i < endIndex; i++) {
			s += arr[i] + ", ";
		}
		s += arr[endIndex] + "]";
		System.out.println(s);
	}

}
