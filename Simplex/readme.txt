Simplex project
-----------------
Author  : Richard Douglas
Email   : doug3230@mylaurier.ca
Language: Python

Problem 1 Description: I'm taking a Linear Optimization course which involves
the Simplex algorithm for a good chunk of it. Lots of my homework involves taking
a Simplex tableau (which is really just a special kind of matrix) and performing 
"pivots" on the tableau to carry out the algorithm (or do something else.)

Doing this by hand is tedious, especially when the number of columns of the tableau
grows large. Thus I want my computer to do the pivots for me.

Of course, I could have used something like Maple or Matlab for this, but I felt that
it would be more fun to reinvent the wheel (or in this case, reinvent the matrix.)

This program is also handy for solving systems of equations since "pivoting" is the 
same thing as doing a step of Gaussian Elimination.


Problem 2 Description: I solved problem #1 above but found that I wanted a bit more precision
with my results. In particular, I did not like the rounding error I was getting and seeing
a lot of trailing decimals. Thus I decided why not use fractions instead of floating point
numbers? I also needed some more operations for my matrix as matrix multiplication can be
tedious to do by hand especially when you have a lot of matrices.


Results: Throughout this project I have developed
-A handy main program for pivoting Simplex tableaux and solving linear systems.
-A reusable Matrix class
-A reusable Rational class