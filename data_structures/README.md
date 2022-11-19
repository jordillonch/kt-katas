# Sum pairs

## Problem

Given an array of integers, and a number N, find the only 2 integers in the array whose sum is N,  null  is returned if there is no such a pair.
For example, for N=13 and the array a=[3,5,2,8,7] your algorithm should return [5,8] because they are the only pair that adds up to 13.
But for the case of N=4 and a=[3,5,2,8,7] we will return null as there is not a pair of numbers that adds up to 4.

Assumptions:
  - n is an integer with the range  [0..2^31-1]
  - a is an array with len */
  - Examples:
    - n=2, a=[1, 3]
    - n=8, a=[1, 2, 7, 9]
