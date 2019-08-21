import java.util.Arrays;

public class Proj01_SelectionSort implements Proj01_Sort {

	private boolean debug;

	public Proj01_SelectionSort(boolean debug) {
		this.debug = debug;
	}

	@Override
	public void sort(Comparable[] arr) {
		int minIndex;
		Comparable temp = null;
		for (int i = 0; i < arr.length; i++) {
			minIndex = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			if (debug) {
				String debugStr = "[";
				for (int x = 0; x <= i - 1; x++) {
					debugStr += arr[x];
					if (x < i - 1) {
						debugStr += " ";
					}
				}
				debugStr += "] ";
				for (int x = i + 1; x < arr.length; x++) {
					if (x == minIndex) {
						debugStr += "(" + arr[x] + ") ";
					} else {
						debugStr += arr[x] + " ";
					}
				}
				System.out.println(debugStr);
			}

			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}

	}

}
