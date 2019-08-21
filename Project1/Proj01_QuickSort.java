import java.util.Arrays;

public class Proj01_QuickSort implements Proj01_Sort {

	private boolean debug;
	private int baseLen;
	private int mode;
	private Proj01_InsertionSort iSort;

	public Proj01_QuickSort(boolean debug, int mode, int baseLen) {
		this.debug = debug;
		this.mode = mode;
		this.baseLen = baseLen;
		iSort = new Proj01_InsertionSort(false); // Override debug output from InsertionSort
	}

	@Override
	public void sort(Comparable[] arr) {
		if (debug) {
			System.out.println("baseLen: " + baseLen + "\n");
		}
		quickSort(arr, 0, arr.length - 1);
	}

	private void quickSort(Comparable[] arr, int startIndex, int endIndex) {
		// BASE CASE:
		// block is length baseLen or less
		if (endIndex - startIndex <= baseLen) {
			if (debug) {
				System.out.printf("Sorting block from %d to %d\n\n", startIndex, endIndex);
			}
			iSort.sortSection(arr, startIndex, endIndex);
		} else {
			if (debug) {
				printSegment(arr, startIndex, endIndex);
			}
			int low = startIndex;
			int high = endIndex;
			Comparable temp;
			boolean finished = false;
			Comparable pivot = selectPivot(arr, startIndex, endIndex);

			// RECURSIVE CASE:
			while (!finished) {
				// move low and high indices inwards until we find a pair to swap, or low
				// crosses high
				while (arr[low].compareTo(pivot) < 0) {
					low++;
				}
				while (arr[high].compareTo(pivot) > 0) {
					high--;
				}
				if (low >= high) {
					// low and high cross, which means this block is partitioned
					finished = true;
				} else {
					// swap the values we found at low and high
					if (debug) {
						System.out.printf("Swapping elements at %d and %d\n", low, high);
					}
					temp = arr[low];
					arr[low] = arr[high];
					arr[high] = temp;
					low++;
					high--;
				}
			}
			if (debug) {
				System.out.println("Partition complete");
				System.out.printf("LOW (index %d to %d): ", startIndex, high);
				printSegment(arr, startIndex, high);
				System.out.printf("HIGH (index %d to %d): ", high + 1, endIndex);				
				printSegment(arr, high + 1, endIndex);
				System.out.println();
			}

			// low partition
			quickSort(arr, startIndex, high);
			// high partition
			quickSort(arr, high + 1, endIndex);
		}

	}

	private Comparable selectPivot(Comparable[] arr, int startIndex, int endIndex) {
		Comparable pivot;
		// Select a pivot, based on the mode flag (defaults to median-of-three)
		if (mode == 0) {
			pivot = arr[startIndex];
		} else if (mode == 1) {
			pivot = arr[startIndex + ((endIndex - startIndex) / 2)];
		} else {
			// median-of-three
			Comparable[] threeVal = { arr[startIndex], arr[(endIndex - startIndex) / 2], arr[endIndex - 1] };
			iSort.sort(threeVal);
			pivot = threeVal[1];
		}
		if (debug) {
			System.out.printf("pivot (mode %d): %d\n", mode, pivot);
		}
		return pivot;
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
