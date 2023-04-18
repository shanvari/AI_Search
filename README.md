# AI_Search

The problem you are facing is a math game. This game consists of an m × n matrix
One of its areas is the starting house of the game and one of its other areas is the target house of the game. At
The starting house is your initial score. You have to start with the beginning of the house, so in the matrix
Move so that when you reach the target house, your score is higher than the score written in the target house.
Also, note that you can only pass through each of the game's matrix pools once. each of
The fields in the matrix are marked with a symbol and each of these symbols has a special meaning
are This issue is examined further

s:The house determines the start of the game

g:home specifies the goal of the game.

w:These houses are game walls and you cannot pass through them.

a:By passing through these houses, the amount of points specified in the mentioned house, up to the target score
can be

b:By passing through these houses, the amount of points specified in the said house will be reduced from the target score limit.

Mathematical operators: By passing through the houses whose symbol is one of the mathematical operators, the operator Math written in said house, according to the score written in that house, apply to your current score can be For example, if you pass a house with the symbol ^3, your current score will increase to the power of three.

For a better understanding of the problem, please look at the sample input and output. In the first line of input, the dimensions of the matrix(m,n) of the game
 are received and in the next line, the levels of the game matrix are received in order. Also, between
There is a space between each line. In the first m line of the output, first the number of actions in the path found for
The solution of that example of the problem (which we represent this number here with the symbol k) is written and in the next k line, respectively
Found, seen, written.
The number of rows and columns of each matrix that is found in the path is written:


# Sample input:
6 6
s1 +9 *1 *1 *1 *1
*5 +5 *1 *1 *1 *1
*2 +15 w0 w0 w0 +10
-50 -50 w0 g100 w0 +10
*1 *1 a10 -10 +10 +10
+10 +10 b10 w0 -10 -100

# Sample output :
12
2 1
2 2
1 2
1 3
1 4
1 5
1 6
2 6
3 6
3 5
4 5
4 4
