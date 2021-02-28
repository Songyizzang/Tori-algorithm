from collections import deque

def solution(board, moves):
    answer = 0
    len_board = len(board)
    pocket = deque() # stack
    for idx in moves:
        for nr in range(0, len_board):
            if board[nr][idx-1] != 0:
                pocket.append(board[nr][idx-1])
                board[nr][idx-1] = 0
                break

        pocket_size = len(pocket)
        if pocket_size > 1 and pocket[pocket_size-1] == pocket[pocket_size-2]:
            answer += 2
            pocket.pop()
            pocket.pop()

    return answer


board = [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]]
moves = [1,5,3,5,1,2,1,4]

ans = solution(board, moves)
print(ans)