def is_safe(board, row, col):
    for i in range(col):
        if board[row][i] == 'Q':
            return False
    for i, j in zip(range(row, -1, -1), range(col, -1, -1)):
        if board[i][j] == 'Q':
            return False
    for i, j in zip(range(row, len(board), 1), range(col, -1, -1)):
        if board[i][j] == 'Q':
            return False
    return True

def solve_n_queens(n):
    def solve(board, col, counter):
        if col >= len(board):
            print(f"Solution {counter[0]}:")
            for row in board:
                print(" ".join(row))
            print("\n" + "-" * 15 + "\n")
            counter[0] += 1
            return

        for i in range(len(board)):
            if is_safe(board, i, col):
                board[i][col] = 'Q'
                solve(board, col + 1, counter)
                board[i][col] = '.'

    board = [['.' for _ in range(n)] for _ in range(n)]
    counter = [1]
    solve(board, 0, counter)

solve_n_queens(8)
