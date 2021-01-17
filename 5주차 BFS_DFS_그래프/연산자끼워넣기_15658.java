package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 연산자끼워넣기_15658 {
	private static int N;
	private static int[] operator_data;
	private static int[] num_data;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		String[] num = br.readLine().split(" ");
		num_data = new int[num.length];
		for (int i = 0; i < num.length; i++) {
			num_data[i] = Integer.parseInt(num[i]);
		}

		String[] operator = br.readLine().split(" ");
		operator_data = new int[operator.length];
		for (int i = 0; i < operator.length; i++) {
			operator_data[i] = Integer.parseInt(operator[i]);

		}

		DFS(num_data[0], 0, operator_data[0], operator_data[1], operator_data[2], operator_data[3]);
		System.out.println(max);
		System.out.println(min);
	}

	// 연산자 우선순위 무시해도 되니까 괜찮음
	// 값, 탈출 인덱스, plus, minus, mul, div
	public static void DFS(int result, int index, int plus, int minus, int mul, int div) {
		if (index == (N - 1)) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		if (plus != 0) {
			DFS((result + num_data[index + 1]), index + 1, plus - 1, minus, mul, div);
		}
		if (minus != 0) {
			DFS((result - num_data[index + 1]), index + 1, plus, minus - 1, mul, div);
		}
		if (mul != 0) {
			DFS((result * num_data[index + 1]), index + 1, plus, minus, mul - 1, div);
		}
		if (div != 0) {
			DFS((result / num_data[index + 1]), index + 1, plus, minus, mul, div - 1);
		}
	}
}
