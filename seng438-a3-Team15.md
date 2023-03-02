**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:  15    |  UCID   |
| -------------- | --- |
| Student Names: |   |
|   Manraj Singh | 30115660    |
|   Noor Nawaz             |  30118937   |
|   Sajan Hayer             | 30114632    |
|   Ahmad Elshiltawi            |  30123883   |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

This lab's objective was to explore further about White-box testing, and specifically how the JUnit framework along with EclEmma could be used to write unit tests. By using test coverage tools, test writers can understand how in-depth their tests are, by specifically looking at parameters such as Statement Coverage, Branch Coverage and Method/Condition Coverage. By understanding how writing different tests can affect the percentage values of these parameters, the tests we write are more thorough and can expose bugs that may not be covered by writing basic, trivial test cases. By having access to the code of the program, test writes can see how differnt functions return results, and write tests accordingly. White-box testing also allows us to determine more edge cases and edge values that may expose bugs or faulty execution of operations. Furthermore, manually calculating for data-flow coverage and calculating for statement, branch and method coverages allows us to determine how different statements of code affect the flow of data, and the values of those parameters.

# 2 Manual data-flow coverage calculations for X and Y methods

## Range combine() Method data-flow coverage

Data Flow Graph:
![](media/Range_Combine_DFG.png)

Def-use sets per statement:

Def-path set:
- du(1, range1) = {[1], [1,3,4], [1,3,5], [1,3,5,6]}
- du(1, range2) = {[1,2], [1,3], [1,3,5], [1,3,5,6]}
- du(5, l) = {[5,6,7]}
- du(6, u) = {[6,7]}

Def-pair set:
- du(1, 4, range1) = {[1,3,4]}
- du(1, 5, range1) = {[1,3,5]}
- du(1, 6, range1) = {[1,3,5,6]}
- du(1, 2, range2) = {[1,2]}
- du(1, 3, range2) = {[1, 3]}
- du(1, 5, range2) = {[1,3,5]}
- du(1, 6, range2) = {[1,3,5, 6]}
- du(5, 7, l) = {[5,6,7]}
- du(6, 7, u) = {[6, 7]}

![](media/Range_Combine_Tables.png)

Calculate DU-Pair Coverage: CU = 10, PU = 4


## DataUtilities calculateColumnTotal() Method data-flow coverage

Data Flow Graph:
![](media/ColTotalDFG.png)

Def-use sets per statement:

Def-path set:
- du(1, data) = {[1]}
- du(3, total) = {[3,4,6], [3,5,9], [3,5,8,10]}
- du(3, rowCount) = {[3],[3,5]}
- du(3, r) = {[3], [3,4,7]}
- du(4, n) = {[4], [4,6]}
- du(5, r2) = {[5], [5,11]}
- du(8, n) = {[8], [8,10]}

Def-pair set:
- du(3, 6, total) = {[3,4,6]}
- du(3, 9, total) = {[3,5,9]}
- du(3, 10, total) = {[3,5,8,10]}
- du(3, 5, rowCount) = {[3,5]}
- du(3, 7, r) = {[3,4,7]}
- du(4, 6, n) = {[4,6]}
- du(5, 11, r2) = {[5,11]}
- du(8, 10, n) = {[8,10]}


![](media/ColTotalTables.png)

Calculate DU-Pair Coverage: CU = 7, PU = 14

# 3 A detailed description of the testing strategy for the new unit test

Text…

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# 6 Pros and Cons of coverage tools used and Metrics you report

Text…

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

Text…

# 8 A discussion on how the team work/effort was divided and managed

Text…

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Text…

# 10 Comments/feedback on the lab itself

Text…
