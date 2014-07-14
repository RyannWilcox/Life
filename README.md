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
2.If a cell is dead, it will come alive only if it has exactly 2 living neighbors
```
Many interesting and complex patterns emerge from having the grid follow these simple rules.

====
v. 4.0:
I'm re-working the way classes are set up.

v. 3.2:
User can zoom in and out when viewing the game.  

v. 3.1:
You can now select between colors for live cells.  Now adding zoom functionality

v. 3.0:
You can now Pick different patterns and change the speed of generation iterations.  Colors coming soon....


v. 2.0:
You can now click to manipulate cell placement.  More functionality coming soon..


v. 1.0: 
This is a working version.  You just can't click to add living cells yet. </br>

