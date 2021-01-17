package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 조건 여러그룹이 동시에 터지더라도 뿌요는 한번 터진다.
 * loop
	 * 1. BFS로 puyo가 일어날 수 있는 그룹 찾기.
	 * 2. Puyo!해주고  .으로 변환
	 * 3. Puyo 된 데이터 중력 적용
	 * 4. Puyo가 한번이라도 발생했다면 다시 전체 좌표 탐색
 */
public class PuyoPuyo_11559 {
	private static class Node {
		int x;
		int y;
		char c;

		public Node(int x, int y, char c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static char[][] puyoPuyoMap = new char[12][6];
	static boolean[][] puyoPuyoVisited = new boolean[12][6];
	static int puyo_answer = 0;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) {
			String s = bf.readLine();
			for (int k = 0; k < 6; k++) {
				puyoPuyoMap[i][k] = s.charAt(k);
			}
		}

		// 연쇄 반응이 더이상 없을 때까지 돌린다.
		while (true) {
			boolean puyoTrueFalse = false;
			boolean puyoFlag = false;
			loop: for (int i = 0; i < 12; i++) {
				for (int k = 0; k < 6; k++) {
					if (puyoPuyoMap[i][k] != '.') {
						// .이 아닌 모든 데이터를 다 BFS 태운다.
						puyoTrueFalse = bfs_find_puyo(new Node(k, i, puyoPuyoMap[i][k]));

						for (int t = 0; t < 12; t++) {
							Arrays.fill(puyoPuyoVisited[t], false);
						}
						if (puyoTrueFalse && !puyoFlag) {
							puyo_answer++;
							puyoFlag = true;
						}
					}
				}
			}
			fall_puyo();
			if (!puyoFlag) {
				break;
			}
		}
		System.out.println(puyo_answer);
	}

	// 같은 문자열 그룹만 확인한다.
	public static boolean bfs_find_puyo(Node node) {
		Queue<Node> internal_q = new LinkedList<>();
		Queue<Node> puyo = new LinkedList<>(); // puyo일 경우 좌표값

		internal_q.add(node);
		puyo.add(new Node(node.x, node.y));

		int group_cnt = 1;
		while (!internal_q.isEmpty()) {
			Node node_data = internal_q.poll();
			puyoPuyoVisited[node_data.y][node_data.x] = true;
			for (int i = 0; i < 4; i++) {
				int next_x = node_data.x + dx[i];
				int next_y = node_data.y + dy[i];
				if (next_x >= 0 && next_x < 6 && next_y >= 0 && next_y < 12) {
					if (puyoPuyoMap[next_y][next_x] == node.c && !puyoPuyoVisited[next_y][next_x]) {
						internal_q.add(new Node(next_x, next_y, node.c));
						puyo.add(new Node(next_x, next_y));
						group_cnt++;
					}
				}
			}
		}
		if (group_cnt >= 4) {
			pang_pang(puyo);
			return true;
		} else {
			return false;
		}
	}

	public static void pang_pang(Queue<Node> pang_data) {
		while (!pang_data.isEmpty()) {
			Node node = pang_data.poll();
			puyoPuyoMap[node.y][node.x] = '.';
		}
	}

	public static void fall_puyo() {
		ArrayList<Character> line_data = new ArrayList<>();
		for (int x = 0; x < 6; x++) {
			for (int y = 11; y >= 0; y--) {
				if (puyoPuyoMap[y][x] != '.') {
					line_data.add(puyoPuyoMap[y][x]);
					puyoPuyoMap[y][x] = '.';
				}
			}
			int indexArrayList = 0;
			for (int i = 11; i > (11 - line_data.size()); i--) {
				puyoPuyoMap[i][x] = line_data.get(indexArrayList);
				indexArrayList++;
			}
			line_data.clear();
		}
	}

	public static void printData() {
		System.out.println();
		for (int i = 0; i < 12; i++) {
			System.out.println();
			for (int k = 0; k < 6; k++) {
				System.out.print(puyoPuyoMap[i][k] + " ");
			}
		}
	}
}
