# sudokuAssignment

<b>Group:</b> Kenny Dong, Collin Inns

<b>Runtime Complexity</b>: In solver(), the most runtime-heavy function, there are 9 possible choices for EACH space; so it should be O(9^(n\*n)) where n is the length of the board. In the recursion tree, T(n^2) = 9 \* T(n^2 - 1) + O(1), so O(9^(n\*n)) is confirmed.

<b>Space Complexity</b>: The largest space used is the board itself, so the space complexity should be O(n*n)
