import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2667 {
	private static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	private static int[][] map;
	private static boolean[][] visited;
	private static ArrayList<Integer> list;
	private static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();

			for (int j = 0; j < s.length(); j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
				}
			}
		}

		System.out.println(list.size());
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}

	private static int[] dr = { 0, 0, -1, 1 };
	private static int[] dc = { 1, -1, 0, 0 };

	public static void bfs(int nr, int nc) {

		Queue<Node> que = new LinkedList<Node>();

		que.add(new Node(nr, nc));
		visited[nr][nc] = true;
		int cnt = 0;
		while (!que.isEmpty()) {
			
			Node cur = que.poll();
			int r = cur.r;
			int c = cur.c;			
			cnt++;
			for (int i = 0; i < 4; i++) {
				if (r + dr[i] >= 0 && r + dr[i] < N && c + dc[i] >= 0 && c + dc[i] < N
						&& !visited[r + dr[i]][c + dc[i]]) {
					if (map[r + dr[i]][c + dc[i]] == 1) {
						visited[r + dr[i]][c + dc[i]] = true;
						que.add(new Node(r + dr[i], c + dc[i]));
					}
				}
			}
		}
		
		list.add(cnt);
	}

}
