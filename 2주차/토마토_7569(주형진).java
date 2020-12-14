package BaekJoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토마토_7569 {

	
	
	/**
	 * 1. 익은 토마토는 최소 1개
	 * 2. 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램
	 * 3. 하나의 토마토에 인접한 곳은 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향
	 * 4. 음?
	 * 만약 어떠한 토마토도 갈수 없다면
	 * 만약 하나라도 익지 못한 토마토가 있다면
	 * 5. 정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸
	 * 6. 
	 */

	static int[][][] map;
	static int N,M,H;
	static Queue<int[]> q = new LinkedList<>();
	
	static int[] dx = { 1, -1, 0, 0, 0, 0 };
	static int[] dy = { 0, 0, 1, -1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();

		map = new int[M][N][H];
		
		for (int t = 0; t < H; t++) {
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < M; i++) {
					map[i][k][t] = sc.nextInt();
					//만약 토마토라면
					if (map[i][k][t] == 1) {
						q.offer(new int[] { i, k, t });
					}
				}
			}
		}
		//모든 토마토가 익어있는 상태이면 0을 
		if(q.isEmpty()) {
			System.out.println(0);
			System.exit(0);
		}else {
			int result = BFS();
			//토마토가 모두 익지는 못하는 상황
			if(Check()==false) {
				System.out.println(-1);
			}else {
				System.out.println(result);
			}
		}
	}

	public static int BFS() {
		
		int day = 0;
		if(Check()) {
			return day;
		}
		while (!q.isEmpty()) {
			// 날짜별로 BFS 돌린다.
			int data_size = q.size();
			for (int i = 0; i < data_size; i++) {
				int[] location = q.poll();
				// 6방면 탐색
				for (int k = 0; k < 6; k++) {
					int next_x = location[0] + dx[k];
					int next_y = location[1] + dy[k];
					int next_z = location[2] + dz[k];
					
					if (next_x >= 0 && next_x < M && next_y >= 0 && next_y < N && next_z >= 0 && next_z < H) {
						if (map[next_x][next_y][next_z] == 0) {
							map[next_x][next_y][next_z] = 1;
							q.offer(new int[] { next_x, next_y, next_z });
						}
					}
				}
			}
			day++;
			if(Check()) {
				return day;
			}
		}
		return day;
	}
	
	public static boolean Check() {
		for (int t = 0; t < H; t++) {
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < M; i++) {
					if (map[i][k][t]==0) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
