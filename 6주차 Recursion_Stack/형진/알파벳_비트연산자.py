from collections import deque
import sys
from collections import defaultdict

def display_arr(arr):
    for ii in range(len(arr)):
        for jj in range(len(arr[ii])):
            print(arr[ii][jj], end=' ')
        print()
    print()

sys.stdin = open("input.txt", "r")

row, col = map(int, input().split())
board = [list(input()) for _ in range(row)]
visited = [[0] * col for _ in range(row)]
#visited 의 역할은 동일 depth에서(BFS에서 동일 depth) 같은 경로(CAB = BAC) 인 경우는 뭘 하든 개수가 같기 때문에 미리 쳐냅니다.
#안 그러면 메모리 초과가 납니다!!
for i in range(row):
    for j in range(col):
        val = ord(board[i][j]) - 65
        board[i][j] = 1 << val
        print(val, 1<<val)
display_arr(board)
queue = deque()
queue.append([0, 0, board[0][0], 1])

result = 0
while queue:
    print(queue)
    y, x, value, cnt = queue.popleft()
    result = max(result, cnt)
    if result == 26:
        break

    for dy, dx in ((-1, 0), (0, 1), (1, 0), (0, -1)):
        new_y, new_x = y + dy, x + dx
        if 0 <= new_y < row and 0 <= new_x < col:
            if not board[new_y][new_x] & value and visited[new_y][new_x] != value + board[new_y][new_x]:
                visited[new_y][new_x] = value + board[new_y][new_x]
                queue.append([new_y, new_x, value + board[new_y][new_x], cnt + 1])

print(result)
