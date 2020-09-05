# sudokuAssignment

<b>Group:</b> Kenny Dong, Collin Inns

<b>Runtime Complexity</b>: In solver(), the most runtime-heavy function, there are 9 possible choices for EACH space; so it should be O(9<sup>n\*n</sup>) where n is the length of the board. In the recursion tree, T(n<sup>2</sup>) = 9 \* T(n<sup>2</sup> - 1) + O(1), so O(9<sup>n\*n</sup>) is confirmed.

<b>Space Complexity</b>: The largest space used is the board itself, so the space complexity should be O(n\*n)
