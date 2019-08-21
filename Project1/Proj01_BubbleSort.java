import java.util.Arrays;

public class Proj01_BubbleSort implements Proj01_Sort {

	private boolean debug = false;

	public Proj01_BubbleSort(boolean debug) {
		this.debug = debug;
	}

	@Override
	public void sort(Comparable[] arr) {
		Comparable temp = null;
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 0; i < arr.length - 1; i++) {
				// If current element is out of order, swap with the element after it

				if (arr[i].compareTo(arr[i + 1]) > 0) {
					if (debug) {
						String debugStr = "";
						for (int x = 0; x < arr.length; x++) {
							if (x == i || x == i + 1) {
								debugStr += "(" + arr[x] + ") ";
							} else {
								debugStr += arr[x] + " ";
							}
						}
						System.out.println(debugStr);
					}
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
					swapped = true;

				}
			}
		}

	}

}
