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

For the new unit tests, we aim to initially identify the initial statement, branch and condition coverages. After identifying these values, we will write basic test cases for methods that our old code did not test. After we develop these basic tests, we will see the increase in values for the coverages, and add/modify the tests accordingly to improve the values. Furthermore, viewing the initial coverages as a group will allow us to identify where our previous test cases missed statements, and how we could develop further tests to trigger different conditional statements. After this group discussion, we split the two classes amongst two smaller groups (Manraj-Sajan and Noor-Ahmad). Each subgroup took a deeper look at the coverage values provided by EclEmma and split up missed methods to write tests for. After each member was done adding tests, we combined them to see the overall coverage.

Our initial statement, branch and method coverage values (from Assignment 2) were as follows:

Range.combine() method:
- Statement coverage: 11.5%
- Branch coverage: 6.2%
- Condition coverage: 20.0%

DataUtilities.calculateColumnTotal() method:
- Statement coverage: 35.3%
- Branch coverage: 34.1%
- Condition coverage: 43.5%

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

**Manraj and Sajan:**

**1) public void testEqual4()**
By adding this test case, we aimed to improve the line/statement coverage because this method was not being tested in the intial code from Assignment 2. After conducting this test with valid values, we improved line and method coverage but did not improve branch coverage because the if-statements were not being ran. To improve branch coverage, we modified the values of parameters passed to the equals() method (in class DataUtilities), to provide for null values, and when array sizes and array values were differnt. 

**2) public void test1ColumnTotal()**
These functions changed the coverage values because we removed the mock objects, and replaced them with actual dependent objects. This allowed us to improve the line coverage because EclEmma was not registering the proper coverage values due to the mock objects. After we designed the new test code for test1ColumnTotal(), we developed further test cases by modifying the input values to ensure that all branches were targeted and all lines in the method were targeted.

**3) public void getCumulativePercentageTest2()**
This test improved coverage because the original mock objects were not targeting the test properly. By removing the mock objects and replacing them with actual dependent objects, the coverage increased. We also provided null values for the key-value pairs, and that allowed us to trigger some branch statements that greatly improved branch coverage as they were not being targeted intitially. 

**Noor and Ahmad**
**NOOR AND AHMAD ADD YOUR 3 METHODS HERE**

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

**Final Coverages:**

Range.java class:
- Statement/Line:
- Branch:
- Method:

Screenshots:
![](media/rangeMethod.png)

![](media/rangeLine.png)

![](media/rangeBranch.png)

DataUtilities.java:
- Statement/Line: 87.5%
- Branch: 82.8%
- Method: 90%

Screenshots:
![](media/duMethod.png)

![](media/duLine.png)

![](media/duBranch.png)

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
