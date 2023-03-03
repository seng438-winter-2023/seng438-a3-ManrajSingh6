package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.chart.util.ParamChecks;
import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }

    //CONTAINS() TESTING

    // testing a positive number thats inside the range and checking that it returns true
    @Test
    public void testContainsPositiveInsideRange() {
    	assertTrue(exampleRange.contains(0.34));
    }
    
    // testing a negative number thats inside the range and checking that it returns true
    @Test
    public void testContainsNegativeInsideRange() {
    	assertTrue(exampleRange.contains(-0.34));
    }
    
    // testing a positive number thats outside the range and checking that it returns false
    @Test
    public void testContainsPositiveOutsideRange() {
    	assertFalse(exampleRange.contains(12.32));
    }
    
    // testing a negative number thats outside the range and checking that it returns false
    @Test
    public void testContainsNegativeOutsideRange() {
    	assertFalse(exampleRange.contains(-12.32));
    }
    
    //INTERSECTS() TESTING
    
    //testing a range that's within the range being tested completely and verifying that it returns true
    @Test
    public void testIntersectsOverlappingSmaller() {
    	assertTrue(exampleRange.intersects(-0.5, 0.5));
    }
    
    //testing a range that overlaps completely over the range being tested and verifying that it returns true
    @Test
    public void testIntersectsOverlappingBigger() {
    	assertTrue(exampleRange.intersects(-1.5, 1.5));
    }
    
    //testing a range that has only its upper bound within the range being tested and verifying that it returns true
    @Test
    public void testIntersectsOverlappingLower() {
    	assertTrue(exampleRange.intersects(-0.5, 1.5));
    }
    
    //testing a range that has only its lower bound within the range being tested and verifying that it returns true
    @Test
    public void testIntersectsOverlappingUpper() {
    	assertTrue(exampleRange.intersects(-1.5, 0.5));
    }
    
    //testing a range that doesn't intersect and verifying that it returns false
    @Test
    public void testIntersectsNotOverlapping() {
    	assertFalse(exampleRange.intersects(1, 1.5));
    }
    
    
    //COMBINE() TESTING
    
    //combining two ranges and verifying that the upper and lower bound of the new range are accurate
    @Test
    public void testCombineTwoRanges() {
    	Range range_one = new Range(-3, 3);
    	Range range_two = new Range(-4, 5);
    	
    	Range new_range = Range.combine(range_one, range_two);
    	
    	double lower_bound = new_range.getLowerBound();
    	double upper_bound = new_range.getUpperBound();

    	
    	assertEquals("Range 1 is (-3, 3), Range_2 is (-4, 5), the lower bound should be -4.0", -4.0, lower_bound, 0.00000001d);
    	assertEquals("Range 1 is (-3, 3), Range_2 is (-4, 5), the upper bound should be 5.0", 5.0, upper_bound, 0.00000001d);
    	
    }
    
    //combining one range with a null range and verifying that it returns the range
    @Test
    public void testCombineOneNull() {
    	Range range_one = new Range(-3, 3);
    	Range range_two = null;
    	
    	Range new_range = Range.combine(range_one, range_two);
    	
    	double lower_bound = new_range.getLowerBound();
    	double upper_bound = new_range.getUpperBound();

    	
    	assertEquals("Range 1 is (-3, 3), Range_2 is null, the lower bound should be -3.", -3.0, lower_bound, 0.00000001d);
    	assertEquals("Range 1 is (-3, 3), Range_2 is null, the upper bound should be 3.", 3.0, upper_bound, 0.00000001d);
    	
    }
    
    //combining two null ranges and verifying that it returns null
    @Test
    public void testCombineTwoNulls() {
    	Range range_one = null;
    	Range range_two = null;
    	
    	Range new_range = Range.combine(range_one, range_two);

    	
    	assertNull("Range 1 is null, Range_2 is null, new_range should be null.", new_range);
    	
    }
    
    //GETLOWERBOUND() TESTING
    //creating a range and verifying that the lower bound is the same value of the method
    @Test
    public void testLowerBound() {
    	assertEquals(-1, exampleRange.getLowerBound(), 0.00000001d);
    }
    
    //GETUPPERBOUND() TESTING
    //creating a range and verifying that the upper bound is the same value of the method
    @Test
    public void testUpperBound() {
    	assertEquals(1, exampleRange.getUpperBound(), 0.00000001d);
    }
    
    //CONSTRAIN() TESTING
    //entering a value close to the upper bound of the range and verifying it
    @Test
    public void constrainShouldBeOne() {
    	
        assertEquals("In the range -1 to 1, the closest value to 2 is 1.",
        1, exampleRange.constrain(2), .000000001d);
    }
    
    //entering a value close to the lower bound of the range and verifying it
    @Test
    public void constrainShouldBeNegOne() {
        assertEquals("In the range -1 to 1, the closest value to -2 is -1.",
        -1, exampleRange.constrain(-2), .000000001d);
    }
    
    //entering a value equal to the middle of the range and verifying that it returns the same value
    @Test
    public void constrainShouldBeZero() {
        assertEquals("In the range -1 to 1, the closest value to 0 is 0.",
        0, exampleRange.constrain(0), .000000001d);
    }
    
    //SHIFT() TESTING
    //shifting the range by 1 then verifying the lower bound of the range is correct
    @Test
    public void beginningShiftTest() {
        assertEquals("The beginning of the range [-1, 1] shifted right by 1 should be 0.",
        0, Range.shift(exampleRange, 1).getLowerBound(), .000000001d);
    }
    
    //shifting the range by 1 then verifying the upper bound of the range is correct
    @Test
    public void endShiftTest() {
        assertEquals("The end of the range [-1, 1] shifted right by 1 should be 2.",
        2, Range.shift(exampleRange, 1).getUpperBound(), .000000001d);
    }
    
    @Test
    public void expandLowerTest()
    {
    	Range newRange = Range.expand(exampleRange, 1, 0);
    	assertEquals(-3, newRange.getLowerBound(), 0.00000001d);
    }
    
    @Test
    public void expandUpperTest()
    {
    	Range newRange = Range.expand(exampleRange, 0, -5);
    	assertEquals(-5, newRange.getUpperBound(), 0.00000001d);
    }
    
    @Test
    public void toStringTest()
    {
    	Range stringRange = new Range(0, 1);
    	String expected = "Range[0.0,1.0]";
    	assertEquals(expected, stringRange.toString());
    }
    
    @Test
    public void equalsTrueSameObjectTest()
    {
    	assertTrue(exampleRange.equals(exampleRange));
    }
    
    @Test
    public void equalsTrueSameNumberTest()
    {
    	Range newRange = new Range(-1, 1);
    	assertTrue(exampleRange.equals(newRange));
    }
    
    @Test
    public void equalsFalseLowerTest()
    {
    	Range newRange = new Range(0, 1);
    	assertFalse(exampleRange.equals(newRange));
    }
    
    @Test
    public void equalsFalseDifferentObjectTest()
    {
    	int number = 5;
    	assertFalse(exampleRange.equals(number));
    }
    
    @Test
    public void equalsFalseUpperTest()
    {
    	Range newRange = new Range(-1, 2);
    	assertFalse(exampleRange.equals(newRange));
    }
    
    
    @Test
    public void hashCodeTest()
    {
    	assertEquals(-31457280, exampleRange.hashCode(), 0.00000001d);
    }
    
    @Test
    //double checking that the value returns is equal to calculated value
    public void getCentralValueTest() {
        assertEquals("The central value of [-1, 1] is 0.",
        0, exampleRange.getCentralValue(), .000000001d);
    }
   
    
    //GETLENGTH() TESTING
    @Test
    //double checking that the value returns is equal to calculated value
    public void getLengthTest() {
        assertEquals("The length of [-1, 1] is 2.",
        2, exampleRange.getLength(), .000000001d);
    }
    
    //testing a range that doesn't intersect and verifying that it returns false
    @Test
    public void testIntersects() {
    	Range exRange = new Range(1, 1.5);
    	assertFalse(exampleRange.intersects(exRange));
    }
    
    @Test
    public void scaleUpperTest()
    {
    	Range exRange = new Range(0, 1);
    	
    	Range scaledRange = Range.scale(exRange, 2);
    	
    	assertEquals(2.0, scaledRange.getUpperBound(), .000000001d);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void scaleExceptionTest()
    {
    	Range exRange = new Range(0, 1);
    	Range.scale(exRange, -7);
    }
    
    @Test
    public void ExpandToIncludeNullTest()
    {
    	Range nullRange = null;
    	Range newRange = Range.expandToInclude(nullRange, 1);
    	
    	assertEquals(1, newRange.getLowerBound(), .000000001d);
    	assertEquals(1, newRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void ExpandToIncludeLessThanLowerTest()
    {
    	Range newRange = Range.expandToInclude(exampleRange, -2);
    	
    	assertEquals(-2, newRange.getLowerBound(), .000000001d);
    	assertEquals(1, newRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void ExpandToIncludeBiggerThanUpperTest()
    {
    	Range newRange = Range.expandToInclude(exampleRange, 7);
    	
    	assertEquals(-1, newRange.getLowerBound(), .000000001d);
    	assertEquals(7, newRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void ExpandToIncludeEqualTest()
    {
    	Range equalRange = new Range(1, 1);
    	Range newRange = Range.expandToInclude(equalRange, 1);
    	
    	assertEquals(1, newRange.getLowerBound(), .000000001d);
    	assertEquals(1, newRange.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void shiftAllowZeroCrossing() {
    	Range newRange = Range.shift(exampleRange, 0, true);
    	assertEquals(-1, newRange.getLowerBound(), .000000001d);
    	assertEquals(1, newRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void shfitWithNoZeroCrossingEqualZeroTest()
    {
    	Range zeroRange = new Range(0, 0);
    	Range newRange = Range.shift(zeroRange, 0.0, false);
    	assertEquals(0, newRange.getLowerBound(), .000000001d);
    	assertEquals(0, newRange.getUpperBound(), .000000001d);
    }
   
    @Test (expected = IllegalArgumentException.class)
    public void testLowerBound2() {
    	Range exampleRange2 = new Range(1, -1);
    	exampleRange2.getUpperBound();
    }
    
    //COMBINEIGNORINGNAN() TESTING
    
    
    @Test
    public void combaineWithoutNaNNonNullTest()
    {
    	Range ex1 = new Range(0.0, 1.0);
    	Range ex2 = new Range(-2.0, 3.0);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(-2.0, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(3.0, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNaNFirstNullTest()
    {
    	Range ex1 = null;
    	Range ex2 = new Range(-2.0, 3.0);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(-2.0, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(3.0, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNaNSecondtNullTest()
    {
    	Range ex1 = new Range(0.0, 1.0);
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(0.0, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(1.0, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNaNBothNullTest()
    {
    	Range ex1 = null;
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertNull(newRange);
    }
    
    @Test
    public void combaineWithoutNaNNaNNullTest()
    {
    	Range ex1 = new Range(Float.NaN, Float.NaN);
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertNull(newRange);
    }
    
    @Test
    public void combaineWithoutNaNNaNNull2Test()
    {
    	Range ex1 = new Range(2, Float.NaN);
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(2, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(Float.NaN, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNaNNaNNull3Test()
    {
    	Range ex1 = new Range(Float.NaN, 2);
    	Range ex2 = null;
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(Float.NaN, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(2, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineWithoutNulLNaNTest()
    {
    	Range ex1 = null;
    	Range ex2 = new Range(Float.NaN, Float.NaN);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertNull(newRange);
    }
    
    @Test
    public void combaineBothNaNTest()
    {
    	Range ex1 = new Range(Float.NaN, Float.NaN);
    	Range ex2 = new Range(Float.NaN, Float.NaN);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertNull(newRange);
    }
    
    @Test
    public void combaineBothNaN2Test()
    {
    	Range ex1 = new Range(2, Float.NaN);
    	Range ex2 = new Range(Float.NaN, Float.NaN);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(2, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(Float.NaN, newRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void combaineBothNaN3Test()
    {
    	Range ex1 = new Range(Float.NaN, 2);
    	Range ex2 = new Range(Float.NaN, Float.NaN);
    	
    	Range newRange = Range.combineIgnoringNaN(ex1, ex2);
    	
    	assertEquals(Float.NaN, newRange.getLowerBound(), 0.000000001d);
    	assertEquals(2, newRange.getUpperBound(), 0.000000001d);
    }
    
    
    
//    @Test
//    //combining a null with a valid range
//    public void combineWithoutNaNTest() {
//    	Range ex1 = null;
//    	Range ex2 = new Range(0.0, 1.0);
//    	assertEquals(0, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), .000000001d);
//    }
//    
//    @Test
//    //combining a valid range with a null
//    public void combineWithoutNaNTest2() {
//        Range ex1 = new Range(0.0, 1.0);
//        Range ex2 = null; 
//        assertEquals("The new range is [0.0, 1.0]",
//                0.0, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), .000000001d);
//    }
//
// 
//    @Test
//    //combining a null with a NaN range
//    public void combineWithoutNaNTest3() {
//        Range ex1 = null;
//        double x = 0.0d / 0.0;
//        Range ex2 = new Range(x, x);
//        assertNull(Range.combineIgnoringNaN(ex1, ex2).getLowerBound());
//    }
//
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest4() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(x, x);
//        Range ex2 = new Range(x, x);
//        assertNull(Range.combineIgnoringNaN(ex1, ex2).getLowerBound());
//    }
//
//
//    @Test
//    public void combineWithoutNaNTest5() {
//    Range ex1 = null;
//    Range ex2 = null;
//    assertNull(Range.combineIgnoringNaN(ex1, ex2).getLowerBound());
//}
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest6() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(x, x);
//        Range ex2 = new Range(x, x);
//        assertNull(Range.combineIgnoringNaN(ex1, ex2).getLowerBound());
//    }
//    
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest7() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(x, 4);
//        Range ex2 = new Range(x, 4);
//        assertEquals(Float.NaN, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), 0.000000001d);
//    }
//    
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest8() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(4, x);
//        Range ex2 = new Range(x, 4);
//        assertEquals(4, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), 0.000000001d);
//    }
//    
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest9() {
//        double x = 0.0d / 0.0;
//        Range ex1 = new Range(4, x);
//        Range ex2 = new Range(x, 4);
//        assertEquals(4, Range.combineIgnoringNaN(ex1, ex2).getLowerBound(), 0.000000001d);
//    }
//    
//    @Test
//    //combining a NaN range with a null range
//    public void combineWithoutNaNTest10() {
//        Range ex1 = new Range(4, 7);
//        Range ex2 = new Range(3, 10);
//        
//        assertEquals(4, ex1.getLowerBound(), 0.000000001d);
//        assertEquals(7, ex1.getUpperBound(), 0.000000001d);
//    }
    
    @Test
    public void intersectsBiggerThanLowerTest()
    {
    	boolean intersect = exampleRange.intersects(-5, -3);
    	assertFalse(intersect);
    }
    
    @Test
    public void intersectsLessThanUpperTest()
    {
    	boolean intersect = exampleRange.intersects(2, 5);
    	assertFalse(intersect);
    }
    
    @Test
    public void intersectsEqualTest()
    {
    	boolean intersect = exampleRange.intersects(0, 2);
    	assertTrue(intersect);
    }
    
    @Test
    public void constrainBiggerThanLowerTest()
    {
    	Range newRange = new Range(0, 15);
    	exampleRange.constrain(0.1);
    }
   

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
