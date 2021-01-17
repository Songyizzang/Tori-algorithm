import sys
sys.stdin = open("input.txt", "r")

from collections import deque

N = int(input())

tops = list(map(int, input().split()))

stack = deque()
list_top = []
for i in range(0, N):
    if not stack:
        stack.append(0)
        list_top.append(-1)
    else:
        while stack:
            highest = stack.pop()
            if tops[highest] <= tops[i]:
                continue
            else:
                stack.append(highest)
                stack.append(i)
                list_top.append(highest)
                break

        if not stack:
            stack.append(i)
            list_top.append(-1)

for i in range(0, N):
    print(list_top[i]+1, end=" ")

#
# for i in range(N-1, -1, -1):
#     print(tops[i])

