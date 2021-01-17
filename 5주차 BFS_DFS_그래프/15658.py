import sys

sys. stdin = open('input.txt', 'r')

#main start
N = int(input())
a = list(map(int, input().split()))
ops = list(map(int, input().split())) # + - * / 의 개수
cnt = [0, 0, 0, 0]

min_val = 1000000000
max_val = -1000000000

def dfs(total, idx):
    global min_val, max_val
    if idx == N:
        if total < min_val:
            min_val = total
        if total > max_val:
            max_val = total
        return
    else:
        if cnt[0] < ops[0]:
            cnt[0] = cnt[0]+1
            dfs(total+a[idx], idx+1)
            cnt[0] = cnt[0]-1
        if cnt[1] < ops[1]:
            cnt[1] = cnt[1]+1
            dfs(total-a[idx], idx+1)
            cnt[1] = cnt[1]-1
        if cnt[2] < ops[2]:
            cnt[2] = cnt[2]+1
            dfs(total*a[idx], idx+1)
            cnt[2] = cnt[2]-1
        if cnt[3] < ops[3]:
            cnt[3] = cnt[3]+1
            if total < 0:
                dfs(-(-total//a[idx]), idx+1)
            else:
                dfs(total//a[idx], idx+1)
            cnt[3] = cnt[3]-1


dfs(a[0], 1)

print(2018//5)
print(-2018//5)
print(-(2018//5))
print()
print()
print(max_val)
print(min_val)