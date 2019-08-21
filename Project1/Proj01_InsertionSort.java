
public class Proj01_InsertionSort implements Proj01_Sort {

	private boolean debug;

	public Proj01_InsertionSort(boolean debug) {
		this.debug = debug;
	}

	@Override
	public void sort(Comparable[] arr) {
		sortSection(arr, 0, arr.length - 1);
	}

	public void sortSection(Comparable[] arr, int startIndex, int endIndex) {
		Comparable temp;
		int j;
		for (int i = startIndex; i <= endIndex; i++) {
			j = i;
			if (debug) {
				System.out.println("Examining " + arr[i]);
			}
			while (j > startIndex && arr[j].compareTo(arr[j - 1]) < 0) {
				if (debug) {
					String debugStr = "";
					for (int x = startIndex; x < endIndex; x++) {
						if (x == j || x == j - 1) {
							debugStr += "(" + arr[x] + ") ";
						} else {
							debugStr += arr[x] + " ";
						}
					}
					System.out.println(debugStr);
				}
				temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
				j--;
			}
		}

	}

}
