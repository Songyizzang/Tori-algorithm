package BaekJoon;

import java.util.ArrayList;

public class 크레인_인형뽑기_형진 {

	// 30x30 배열
	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
		System.out.println(solution(board, moves));
	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;
		ArrayList<Integer> stack = new ArrayList<>();

		for (int i = 0; i < moves.length; i++) {
			int result = findTopAndDeleteOrigin(board, moves[i] - 1);
			if (result == -1) {
				continue;
			}
			// stack이 비어있을때
			if (stack.isEmpty()) {
				stack.add(result);
			} else { // 비어있지 않다면
				// 전 데이터랑 같지 않다면
				if (stack.get(stack.size() - 1) != result) {
					stack.add(result);
				} else {
					stack.remove(stack.size() - 1);
					answer += 2;
				}
			}
		}
		return answer;
	}

	public static int findTopAndDeleteOrigin(int[][] board, int idx) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][idx] != 0) {
				int result = board[i][idx];
				board[i][idx] = 0;
				return result;
			}
		}
		return -1;
	}
}
