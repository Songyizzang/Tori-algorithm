package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 에너지모으기_16198 {
	static int N;
	static ArrayList<Integer> data = new ArrayList<>();
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			data.add(Integer.parseInt(temp[i]));
		}
		DFS(0, 0);
		System.out.println(MAX);
	}

	public static void DFS(int index, int sum) {
		// 아웃 조건
		if (data.size() == 2) {
			MAX = Math.max(sum, MAX);
			return;
		}
		for (int i = 1; i < data.size() - 1; i++) {
			int temp_sum = sum + data.get(i - 1) * data.get(i + 1);
			int removed_data = data.remove(i);
			DFS(i, temp_sum);
			data.add(i, removed_data);
		}

	}
}
