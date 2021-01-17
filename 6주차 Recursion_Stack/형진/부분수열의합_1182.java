package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 부분수열의합_1182 {
	static int N, S;
	static ArrayList<Integer> dataList = new ArrayList<>();
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] indexNum = br.readLine().split(" ");
		N = Integer.parseInt(indexNum[0]);
		S = Integer.parseInt(indexNum[1]);
		
		String[] data = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			dataList.add(Integer.parseInt(data[i]));
		}
		backTracking(0, 0);
		if (S == 0)
			count--;
		System.out.println(count);
	}

	public static void backTracking(int index, int sum) {
		// index를 넘어가면
		if (index >= N) {
			if (sum == S) {
				count++;
			}
			return;
		}
		// 부분 수열의 합을 구하는 것이므로
		// 값을 더하는 경우, 그 값을 제외하고 넘어가는 경우의 수가 존재한다.
		backTracking(index + 1, sum + dataList.get(index));
		backTracking(index + 1, sum);
	}
}
