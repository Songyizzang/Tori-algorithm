

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15591 {
	private static int N;
	private static int Q;
	private static ArrayList<Node>[] USADO;
	private static boolean[] visited;
	
	private static class Node {
		int v;
		int r; // min
		public Node(int v, int r) {
			this.v = v;
			this.r = r;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
	
		USADO  = new ArrayList[N+1];
		
		for (int i = 0; i < N+1; i++) {
			USADO[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			USADO[p].add(new Node(q, r));
			USADO[q].add(new Node(p, r));
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			visited = new boolean[N+1];
			
			int ans = bfs(K,V);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	public static int bfs(int K, int V) {
		Queue<Node> que = new LinkedList<>();
		que.add(new Node(V, Integer.MAX_VALUE));
		visited[V] = true;
		int cnt = 0;
		while(!que.isEmpty()) {
			Node cur = que.poll();
			
			for(int i = 0; i < USADO[cur.v].size(); i++) {
				Node temp = USADO[cur.v].get(i);
				int node = temp.v;
				int dist = temp.r;
				
				if(visited[node]) continue;
				
				if(dist > cur.r) { // 앞에가 작은 것!
					if(cur.r >= K) {
						cnt++;
					}
					que.add(new Node(node, cur.r));
					
				} else {
					if(dist >= K) {
						cnt++;
					}
					
					que.add(new Node(node, dist));
				}
				
				visited[node] = true;
			}
			
		}
	
		return cnt;
	}

}
