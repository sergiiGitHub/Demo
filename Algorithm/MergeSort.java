public class MergeSort {
	public void sort(int arr[]) {
		mergeSort(arr, 0, arr.length - 1);
	}

	private void mergeSort(int[] arr, int l, int r) {
		if (r - l < 1) {
			return;
		}
		int m = (l + r) / 2;
		mergeSort(arr, l, m);
		mergeSort(arr, m + 1, r);

		merge(arr, l, m, r);
	}

	private void merge(int[] arr, int l, int m, int r) {

		// System.out.println("merge: l: " + l + "; r: " + r + "; m " );
		// find size
		int lSize = m - l + 1;
		int rSize = r - m;
		// System.out.println("merge: lSize: " + lSize + "; rSize: " + rSize );

		// create sub array copy
		int lArr[] = new int[lSize];
		int rArr[] = new int[rSize];

		// fill it
		for (int i = 0; i < lSize; ++i) {
			lArr[i] = arr[l + i];
		}
		for (int i = 0; i < rSize; ++i) {
			rArr[i] = arr[m + 1 + i];// need check
		}

		// sort insert
		int lIndex = 0;
		int rIndex = 0;
		int index = l;
		while (lIndex < lSize && rIndex < rSize) {
			if (rArr[rIndex] <= lArr[lIndex]) {
				arr[index] = rArr[rIndex];
				++rIndex;
			} else {
				arr[index] = lArr[lIndex];
				++lIndex;
			}
			++index;
		}
		// copy remain
		while (lIndex < lSize) {
			arr[index] = lArr[lIndex];
			++lIndex;
			++index;
		}
		while (rIndex < rSize) {
			arr[index] = rArr[rIndex];
			++rIndex;
			++index;
		}
	}

}
