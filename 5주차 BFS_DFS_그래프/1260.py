import sys
from collections import deque
sys.stdin = open('input.txt', 'r')

def dfs(v):
    print(v, end=" ")
    visited[v] = 1
    for i in range(1, N+1):
        if visited[i] != 1 and graph_map[v][i] == 1:
            dfs(i)

def bfs(v):
    que = deque()
    que.append(v)
    visited[v] = 1
    while que:
        cur = que.popleft()
        print(cur, end=" ")
        for i in range(1, N+1):
            if visited[i] != 1 and graph_map[cur][i] == 1:
                que.append(i)
                visited[i] = 1

#main start
N, M, V = list(map(int, input().split()))
graph = [list(map(int, input().split())) for _ in range(M)]
graph_map = [[0] * (N+1) for _ in range(N+1)]

for i in range(M):
    graph_map[graph[i][0]][graph[i][1]] = 1
    graph_map[graph[i][1]][graph[i][0]] = 1

visited = [0] * (N+1)
dfs(V)
print()
visited = [0] * (N+1)
bfs(V)
