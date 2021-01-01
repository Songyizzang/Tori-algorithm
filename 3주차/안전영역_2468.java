package BaekJoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 안전영역_2468 {
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] map;
	static boolean[][] visited;
	static int N,max_num=0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> cnt_list = new ArrayList<>();
		N = sc.nextInt();

		map = new int[N][N];
		// 데이터 삽입
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				map[i][k] = sc.nextInt();
				max_num = Math.max(map[i][k], max_num);
			}
		}
		Integer cnt;
		for (int a = 1; a <= max_num; a++) {
			visited = new boolean[N][N];
			cnt=0;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < N; k++) {
					if (map[i][k] > a && !visited[i][k]) {
						BFS(a, i, k);
						cnt++;
					}
				}
			}
			cnt_list.add(cnt);
		}
		cnt_list.add(1);
		System.out.println(Collections.max(cnt_list));
	}
	
	public static void BFS(int limit,int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { x, y });
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] location = q.poll();
			for (int i = 0; i < 4; i++) {
				int next_x = location[0] + dx[i];
				int next_y = location[1] + dy[i];
				if (next_x >= 0 && next_y >= 0 && next_x < N && next_y < N) {
					if (!visited[next_x][next_y] && map[next_x][next_y] > limit) {
						q.offer(new int[] { next_x, next_y });
						visited[next_x][next_y]=true;
					}
				}
			}
		}
	}
}