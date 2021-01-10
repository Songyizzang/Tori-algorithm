package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DFS와DFS구하기_1260 {
	static int[][] map;
	static boolean[] visited;
	static int N,M,V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s=br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		V = Integer.parseInt(s[2]);
		
		map = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < M; i++) {
			String[] row = br.readLine().split(" ");
			int x = Integer.parseInt(row[0])-1;
			int y = Integer.parseInt(row[1])-1;
			map[x][y] = 1;
			map[y][x] = 1;
		}
		DFS(V-1);
		Arrays.fill(visited, false);
		System.out.println();
		BFS(V-1);
	}
	public static void DFS(int start) {
		visited[start] = true;
		System.out.print((start + 1) + " ");
		for (int i = 0; i < N; i++) {
			if (!visited[i] && map[start][i] == 1) {
				DFS(i);
			}
		}
	}
	
	public static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			int temp = q.poll();
			System.out.print((temp + 1) + " ");
			for (int i = 0; i < N; i++) {
				if (!visited[i] && map[temp][i] == 1) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
	}
	
}
