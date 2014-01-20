Simplex project
---------------
Author  : Richard Douglas
Email   : doug3230@mylaurier.ca
Language: Python

Problem Description: I'm taking a Linear Optimization course which involves
the Simplex algorithm for a good chunk of it. Lots of my homework involves taking
a Simplex tableau (which is really just a special kind of matrix) and performing 
"pivots" on the tableau to carry out the algorithm (or do something else.)

Doing this by hand is tedious, especially when the number of columns of the tableau
grows large. Thus I want my computer to do the pivots for me.

Of course, I could have used something like Maple or Matlab for this, but I felt that
it would be more fun to reinvent the wheel. This program is also handy for solving systems
of equations since "pivoting" is the same thing as doing a step of Gaussian Elimination.