import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2664 {
	private static int N;
	private static int p1;
	private static int p2;
	private static int M;
	private static int[][] map;
	
	private static class Node {
		int num;
		int cnt;
		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
	
		M = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()); // yÀÇ ºÎ¸ð
			int y = Integer.parseInt(st.nextToken());
			
			
			map[x][y] = 1;
			map[y][x] = 1;
		}
		
		
		Queue<Node> que = new LinkedList<>();
		
		int ans = -1;
		que.add(new Node(p1, 0));
		
		boolean [] visited = new boolean[N+1];		
		while(!que.isEmpty()) {
			
			Node cur = que.poll();
			
			visited[cur.num] = true;
			
			for (int i = 1; i <= N; i++) {
				if(map[cur.num][i] == 1 && !visited[i]) {
					if(i == p2) {
						 ans = cur.cnt+1;
						 break;
					}
					que.add(new Node(i, cur.cnt+1));
				}
			}
		}
		
		
		System.out.println(ans);
		
	}
}
