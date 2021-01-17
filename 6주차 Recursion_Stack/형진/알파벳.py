import sys
sys.stdin = open("input.txt", "r")


R, C = map(int, input().split())
alphabet = [list(input().strip()) for _ in range(R)]

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
ans = 0

que = set([(0,0,1,alphabet[0][0])])

while que:
    r, c, cnt, list_alphabet = que.pop()

    if cnt > ans:
        ans = cnt

    for i in range(0, 4):
        nr, nc = r + dr[i], c + dc[i]
        if 0 <= nr < R and 0 <= nc < C and alphabet[nr][nc] not in list_alphabet:
            que.add((nr, nc, cnt+1, alphabet[nr][nc] + list_alphabet))


print(ans)


