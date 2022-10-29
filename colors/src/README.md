# Nearest color

## Introduction
A color is composed by an amount of red, green and blue.

In computer science, a way to represent a color is to compose between these three amount of colors.

Each amount of color have a presence from 0 (the color is absent) to 255 (full presence of color).

But, because some developers love hexadecimal, they decided to use numbers between 0 and F. So, 00 means 0 in decimal and FF means 255 in decimal.

For color composition, the first pair of digits are used for the red, the second pair for the green and last pair for the blue.

The hexadecimal representation of the colors looks like:

FF0000 for red
00FF00 for green
0000FF for blue

## Part 1: nearest color
The idea is to use a set of colors (FF0000, 00FF00, 0000FF) and guess the nearest color from the set.

Example: the nearest color of F40203 is FF0000.

## Part 2: farthest color
The idea is to use a set of colors (FF0000, 00FF00, 0000FF) and guess the farthest color from the set.

Example: the nearest color of F40203 is 00FF00.
