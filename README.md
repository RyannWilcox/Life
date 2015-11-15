Life
====

version of Conway's Game of Life.
This is a kind of cellular automaton.  There is a grid and each square can either be 'alive' or 'dead'.
If the sqaure is alive it will be painted a certain color.  If the square is dead it will not be painted any color.
Each grid square must follow a set of rules to decide whether it will be alive or dead.
The Rules are:
```
1.  If the cell has < 2 living neighbors it dies
2.  If the cell has 2 or 3 living neighbors it will live on.
3.  If the cell has 3 > live neighbors it will die
4.  If any dead cell has exactly 3 live neighbors it will come alive

High Life rule: 
If the cell is dead and has exactly 6 neighbors it will become alive

Seed Rule Set:
1. If a cell is alive, it will die
2. If a cell is dead, it will come alive only if it has exactly 2 living neighbors
```
Many interesting and complex patterns emerge from having the grid follow these simple rules.

====
I'm starting to update how some of these classes are put together...

