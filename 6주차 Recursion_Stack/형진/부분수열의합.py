import sys
sys.stdin = open("input.txt", "r")

import itertools

N, S = map(int, input().split())
numbers = list(map(int, input().split()))
ans = 0
for i in range(1, N+1):
    nCr = itertools.combinations(numbers, i)
    nCr_list = list(nCr)
    for i in range(len(nCr_list)):
        temp = nCr_list[i]
        cnt = 0
        for j in range(len(nCr_list[i])):
            cnt += nCr_list[i][j]
        if cnt == S:
            ans += 1
            #print(nCr_list[i])

print(ans)




