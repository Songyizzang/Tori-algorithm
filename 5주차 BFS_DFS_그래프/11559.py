import sys
from collections import deque

sys. stdin = open('input.txt', 'r')

dr = [-1,1,0,0]
dc = [0,0,-1,1]

def is_wall(r, c, N, M):
    return r < 0 or c < 0 or r >= N or c >= M

def bfs(r, c):
    puyo_list = deque()
    start_pt = [r,c]
    que = deque([start_pt])
    cnt = 0
    while que:
        cnt = cnt+1
        r, c = que.popleft()
        puyo_list.append([r,c])
        visited[r][c] = 1
        for i in range(4):
            nr = r+dr[i]
            nc = c+dc[i]
            if is_wall(nr,nc,12,6):
                continue
            else:
                if visited[nr][nc] == 0 and game_map[nr][nc] == game_map[r][c]:
                    que.append([nr,nc])
    if cnt >= 4:
        while puyo_list:
            r, c = puyo_list.pop()
            game_map[r][c] = "."
        return 1
    else:
        return 0

def move():
    for i in range(10, -1, -1):
        for j in range(6):
            if game_map[i][j] != '.' and game_map[i+1][j] == '.':
                for k in range(i+1, 12):
                    if k == 11 and game_map[k][j] == '.':
                        game_map[k][j] = game_map[i][j]
                    elif game_map[k][j] != '.':
                        game_map[k-1][j] = game_map[i][j]
                        break
                game_map[i][j] = '.'


#main start
game_map = [list(map(str, input().strip())) for _ in range(12)]
answer = 0

while(1):
    flag = 0
    visited = [[0 for _ in range(6)] for _ in range(12)]
    for i in range(12):
        for j in range(6):
            if game_map[i][j] != '.':
                temp = bfs(i,j)
                if temp == 1:
                    flag = 1

    if(flag == 0):
        break
    else:
        # map 재배열
        move()
        answer = answer + 1

print(answer)
