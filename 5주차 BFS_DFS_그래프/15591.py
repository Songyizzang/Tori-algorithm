import sys
from collections import deque, defaultdict
#sys.stdin = open('input.txt', 'r')

def find_video(K, V):
    global graph_dict
    que = deque()
    que.append((V, float('inf')))
    visited = [0] * (N+1)
    visited[V] = 1
    cnt = 0

    while que:
        cur, min_dist = que.popleft()
        for node, dist in graph_dict[cur]:
            if visited[node] == 1:
                continue
            if min_dist > dist:
                que.append((node, dist))
                if dist >= K:
                    cnt += 1
            else:
                que.append((node, min_dist))
                if min_dist >= K:
                    cnt += 1
            visited[node] = 1
    return cnt

#main start
N, Q = list(map(int, input().split()))
USADO = [list(map(int, input().split())) for _ in range(N-1)]
questions = [list(map(int, input().split())) for _ in range(Q)]

graph_dict = defaultdict(list)

for i in range(len(USADO)):
    graph_dict[USADO[i][0]].append((USADO[i][1], USADO[i][2]))
    graph_dict[USADO[i][1]].append((USADO[i][0], USADO[i][2]))

for i in range(0, len(questions)):
    K, V = questions[i][0], questions[i][1]
    print(find_video(K, V))


