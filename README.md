# maze
 Find a solution to a maze (labyrinth) given to you

a maze is a character matrix consisting of
four types of blocks:
 The starting point (source) is denoted by \S". There is one and only one source in a maze.
 The end point (destination) is denoted by \D". There is one and only one destination.
 A wall is denoted by \#". A route cannot pass a wall.
 An empty block is denoted by a dot (\."). A solution always begins at the source, passes
zero or more empty blocks, and ends at the destination.
Below shows an example of a 13 by 13 maze. The size of a maze is at most 5,000 by 5,000 i.e., all
inputs in this project contain no more than 5000 lines, and each line contains no more than 5001
characters (including the line separator nn).
#############
S.#.........#
#.#.#######.#
#...#.....#.#
#####.###.#.#
#.....#.#...#
#.#####.#####
#.#...#.....#
#.#.#.#####.#
#...#.#...#.#
#####.#.#.#.#
#.......#...D
#############

Task 1

Read in a maze and determine whether the maze itself is legal. Below are examples of an illegal
maze:
 Contains characters not belonging to the following: \S", \D", \#", \.", and newline (\nn").
 Contains no source or destination.
 Contains multiple sources or destinations.
 Is not an m ∗ n character matrix where n > 0 and m > 0.
Note a legal maze may or may not have a solution. Also a legal m ∗ n maze must contain exactly
m lines, and each line (including the last line) must contain exactly n characters followed by a line
separator, which is nn (0x0A in Linux). There must not be any empty lines in the input.
Your program will read from the standard input (stdin) a maze that is either legal (example shown
above) or illegal. Your program should write to the standard output (stdout) a single line of either
\YES" (without quotes), if the matrix is legal, or \NO", if the matrix is illegal.

Task 2
Read in a legal maze to determine if a solution exists. A solution is a route that always begins at
the source, passes zero or more empty blocks (and never passes walls), and ends at the destination.
An empty block can be stepped on more than once. There are four possible moving directions: up,
down, left, and right. Moving diagonally in one step is prohibitive, and moving off the maze (e.g.,
moving upwards or leftwards from the top-left block) is also impossible. The example above has a
solution, but the one below does not.
##############
#............#
#..###.......#
#..#S#...D...#
#..###.......#
#............#
##############
Your program will read from the standard input (stdin) a legal maze. Your program should write
to the standard output (stdout) a single line of either \YES", if the maze has a solution, or \NO",
if the maze does not have a solution.

Task 3
This task is largely similar to Task 2, except that you are further required to find the optimal
solution i.e., the route with the shortest length, for a maze.
Your program will read from the standard input (stdin) a legal maze. If the maze does not have a
solution, your program should write to the standard output (stdout) a single line of \NO" (same
to Task 2).
If the maze does have a solution, your output needs to contain a single line of the best solution.
A solution is a sequence containing the following characters: \U" (up), \D" (down), \L" (left),
and \R" (right), each corresponding to the move direction of one step. The whole sequence thus
represents a possible route from the source to the destination. You need to find the shortest route
i.e., the sequence with the minimum length. If multiple such routes exist, output any one of them
(but only one).
In the first example, there is only one shortest route whose sequence is:
RDDRRUURRRRRRRRDDDDLLUULLLLDDLLLLDDDDRRUURRDDDDRRUURRDDRRR

Task 4
This task is similar to Task 3. Your program takes a legal maze as input, and outputs \NO" if
solution does not exist, or a single line of the best solution (a move sequence with the shortest
length) whose format is the same as that of Task 3. In other words, your code needs to handle
everything in Task 3, plus a new feature described below.
In this task, we introduce a new type of block called teleporter, which instantaneously transports the
player from one location to another. Teleporters always appear in pairs, and each pair is denoted
by a number from 0 to 9. In other words, there can be at most 10 pairs of teleporters in a maze.
Note teleporters do not appear in Task 1, 2, and 3.
Consider the maze shown below. There are two pairs of teleporters: 1 and 9. Whenever the player
steps on one of the \1" blocks, she immediately moves to the other \1" block. The same happens
to the \9" blocks. The player can thus leverage either teleporter pair to reach the destination. The
move sequence can be LD (if Teleporter pair 1 is used) or RUULLL (if Teleporter pair 9 is used). Your
program must output LD since it is the optimal solution with the shortest sequence length.
##############
#............#
#.#####..1...#
#.#1S9#..D...#
#.#####......#
#...........9#
##############
You can assume the input maze is always legal, and teleporters always appear in pairs.
