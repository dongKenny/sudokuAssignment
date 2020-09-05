sudokuAssignment (doc specified a txt)

Group: Kenny Dong, Collin Inns

Runtime Complexity: In solver(), the most runtime-heavy function, there are 9 possible choices for EACH space; so it should be O(9^(n*n)) where n is the length of the board. In the recursion tree, T(n*n) = 9 * T(n*n - 1) + O(1) as the problem reduces, so O(9^(n*n)) is confirmed.

Space Complexity: The largest space used is the board itself, so the space complexity should be O(n*n)
