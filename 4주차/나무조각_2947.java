package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무조각_2947 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); // 선언
		String s = bf.readLine();
		String str_arr[] = s.split(" ");
		int[] arr = Arrays.stream(str_arr).mapToInt(Integer::parseInt).toArray();

		while (true) {
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					arr = swap(arr, i, i + 1);
					print_arr(arr);
				}
			}
			if(is_sorted(arr)) {
				break;
			}
		}
	}

	public static int[] swap(int[] arr, int front, int end) {
		int temp = arr[front];
		arr[front] = arr[end];
		arr[end] = temp;
		return arr;
	}
	public static boolean is_sorted(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				return false;
			}
		}
		return true;
	}
	public static void print_arr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
