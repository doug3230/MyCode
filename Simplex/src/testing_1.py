"""
-------------------------------------------------------
[program file name]
[description of main program]
-------------------------------------------------------
Author:  Richard Douglas
Email:   doug3230@mylaurier.ca
Version: 2014-01-18
-------------------------------------------------------
"""
from matrix import Matrix

A = Matrix([[1, 2], [3, 4]])
B = Matrix([[5, 6], [7, 8]])
C = Matrix([[9, 10, 11], [12, 0, 14]])

print("Matrix A:")
print(A)
print()
print("Matrix B:")
print(B)
print()
print("Matrix C:")
print(C)
print()

print("A + B =")
print(A.add(B))
print()
print("B + A =")
print(B.add(A))
print()
print("A - B =")
print(A.subtract(B))
print()
print("B - A =")
print(B.subtract(A))
print()

for i in range(A.rows()):
    for j in range(A.cols()):
        if (A.entry(i, j) == 0): continue
        print("Pivot on A at ({0}, {1})".format(i, j))
        print(A.pivot(i, j))
        print()
        
for i in range(B.rows()):
    for j in range(B.cols()):
        if (B.entry(i, j) == 0): continue
        print("Pivot on B at ({0}, {1})".format(i, j))
        print(B.pivot(i, j))
        print()

for i in range(C.rows()):
    for j in range(C.cols()):
        if (C.entry(i, j) == 0): continue
        print("Pivot on C at ({0}, {1})".format(i, j))
        print(C.pivot(i, j))
        print()
