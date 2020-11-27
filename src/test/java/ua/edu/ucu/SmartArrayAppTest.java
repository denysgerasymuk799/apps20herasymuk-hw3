package ua.edu.ucu;

import org.junit.Test;
import ua.edu.ucu.functions.MyComparator;
import ua.edu.ucu.functions.MyFunction;
import ua.edu.ucu.functions.MyPredicate;
import ua.edu.ucu.smartarr.*;


import static org.junit.Assert.*;

/**
 *
 * @author Andrii_Rodionov
 */
public class SmartArrayAppTest {

    /** ================================ Tests for DistinctDecorator ================================ */
    @Test
    public void testDistinctDecorator() {
        Integer[] integers = {-1, -1, 2, 1, 1,  2, 0, 1, -5, 3};

        SmartArray sa = new BaseArray(integers);

        sa = new DistinctDecorator(sa);

        assertArrayEquals(new Integer[]{-1, 2, 1, 0, -5, 3}, sa.toArray());
    }

    @Test
    public void testDistinctDecoratorOneItem() {
        Double[] integers = {1.5, 1.5, 1.5, 1.5, 1.5};

        SmartArray sa = new BaseArray(integers);

        sa = new DistinctDecorator(sa);

        assertArrayEquals(new Double[]{1.5}, sa.toArray());
    }


    @Test
    public void testDistinctDecoratorString() {
        String[] integers = {"1.5", "1.5", "1.5", "1.5", "1.5"};

        SmartArray sa = new BaseArray(integers);

        sa = new DistinctDecorator(sa);

        assertArrayEquals(new String[]{"1.5"}, sa.toArray());
    }

    /** ================================ Tests for FilterDecorator ================================ */
    @Test
    public void testFilterDecorator() {
        Integer[] integers = {-1, -1, 2, 1, 1,  2, 0, 1, -5, 3};

        SmartArray sa = new BaseArray(integers);

        MyPredicate pr = t -> ((Integer) t) > 0;

        sa = new FilterDecorator(sa, pr);


        SmartArray saOther = new BaseArray(integers);

        // check if our SmartArray changes
        // if we change integers array
        for (int i = 0; i < integers.length; i++){
            integers[i] *= 4;
        }

        sa = new FilterDecorator(sa, pr);
        saOther = new FilterDecorator(saOther, pr);

        assertArrayEquals(new Integer[]{2, 1, 1,  2, 1, 3}, sa.toArray());
        assertArrayEquals(new Integer[]{2, 1, 1,  2, 1, 3}, saOther.toArray());
    }


    @Test
    public void testFilterDecoratorNothing() {
        Integer[] integers = {1, 1, 2, 1, 1,  2, 1, 5, 3};

        SmartArray sa = new BaseArray(integers);

        MyPredicate pr = t -> ((Integer) t) > 0;

        sa = new FilterDecorator(sa, pr);

        assertArrayEquals(new Integer[]{1, 1, 2, 1, 1,  2, 1, 5, 3}, sa.toArray());
    }

    @Test
    public void testFilterDecoratorAll() {
        Integer[] integers = {-1, -1, -2, -1, -1,  -2, -1, -5, -3};

        SmartArray sa = new BaseArray(integers);

        MyPredicate pr = t -> ((Integer) t) > 0;

        sa = new FilterDecorator(sa, pr);

        assertArrayEquals(new Integer[]{}, sa.toArray());
    }

    /** ================================ Tests for MapDecorator ================================ */
    @Test
    public void testMapDecorator() {
        Integer[] integers = {-1, -1, 2, 1, -5, 3};

        SmartArray sa = new BaseArray(integers);
        MyFunction func = t -> 2 * ((Integer) t);

        SmartArray saOther = new BaseArray(integers);

        // check if our SmartArray changes
        // if we change integers array
        for (int i = 0; i < integers.length; i++){
            integers[i] *= 4;
        }

        sa = new MapDecorator(sa, func);
        saOther = new MapDecorator(saOther, func);

        assertArrayEquals(new Integer[]{-2, -2, 4, 2, -10, 6}, sa.toArray());
        assertArrayEquals(new Integer[]{-2, -2, 4, 2, -10, 6}, saOther.toArray());
    }


    @Test
    public void testMapDecoratorNothing() {
        Integer[] integers = {-1, -1, 0, 0};

        SmartArray sa = new BaseArray(integers);

        MyFunction func = t -> 2 * ((Integer) t);

        sa = new MapDecorator(sa, func);

        assertArrayEquals(new Integer[]{-2, -2, 0, 0}, sa.toArray());
    }

    /** ================================ Tests for SortDecorator ================================ */
    @Test
    public void testSortDecorator() {
        Integer[] integers = {-1, -1, 2, 1, 1,  2, 0, 1, -5, 3};

        SmartArray sa = new BaseArray(integers);

        MyComparator cmp = (o1, o2) -> ((Integer) o1) - ((Integer) o2);

        sa = new SortDecorator(sa, cmp);

        SmartArray saOther = new BaseArray(integers);

        // check if our SmartArray changes
        // if we change integers array
        for (int i = 0; i < integers.length; i++){
            integers[i] *= 0;
        }

        sa = new SortDecorator(sa, cmp);
        saOther = new SortDecorator(saOther, cmp);

        assertArrayEquals(new Integer[]{-5, -1, -1, 0, 1, 1, 1, 2, 2, 3}, sa.toArray());
        assertArrayEquals(new Integer[]{-5, -1, -1, 0, 1, 1, 1, 2, 2, 3}, saOther.toArray());
    }

    @Test
    public void testSortDecoratorAllEquals() {
        Integer[] numbers = {0, 0, 0, 0, 0, 0};

        SmartArray sa = new BaseArray(numbers);

        MyComparator cmp = (o1, o2) -> ((Integer) o1) - ((Integer) o2);

        sa = new SortDecorator(sa, cmp);

        assertArrayEquals(new Integer[]{0, 0, 0, 0, 0, 0}, sa.toArray());
    }

    @Test
    public void testSortDecoratorEmpty() {
        Double[] numbers = {};

        SmartArray sa = new BaseArray(numbers);

        MyComparator cmp = (o1, o2) -> ((Integer) o1) - ((Integer) o2);

        sa = new SortDecorator(sa, cmp);

        assertArrayEquals(new Double[]{}, sa.toArray());
    }

    @Test
    public void testFilterPositiveIntegersSortAndMultiplyBy2() {
        Integer[] integers = {-1, 2, 0, 1, -5, 3};

        Integer[] res =
                SmartArrayApp.filterPositiveIntegersSortAndMultiplyBy2(integers);
        Integer[] expectedRes = {2, 4, 6};

        assertArrayEquals(expectedRes, res);
    }

    /** Tests for FindDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname */
    @Test
    public void testFindDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname() {
        Student[] students = {
            new Student("Ivar", "Grimstad", 3.9, 2),
            new Student("Ittai", "Zeidman", 4.5, 1),
            new Student("Antons", "Kranga", 4.0, 2),
            new Student("Burr", "Sutter", 4.2, 2),
            new Student("Philipp", "Krenn", 4.3, 3),
            new Student("Tomasz", "Borek", 4.1, 2),
            new Student("Ittai", "Zeidman", 4.5, 1),
            new Student("Burr", "Sutter", 4.2, 2)};

        String[] studentNames =
                SmartArrayApp.findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(students);
        String[] expectedStudentNames = {"Borek Tomasz", "Kranga Antons", "Sutter Burr"};

        assertArrayEquals(expectedStudentNames, studentNames);
    }

    @Test
    public void testFindDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname2() {
        Student[] students = {
            new Student("Ivar", "Grimstad", 3.9, 2),
            new Student("Ittai", "Zeidman", 4.5, 2),
            new Student("Antons", "Kranga", 4.0, 2),
            new Student("Burr", "Sutter", 4.2, 2),
            new Student("Philipp", "Krenn", 4.3, 2),
            new Student("Tomasz", "Borek", 4.1, 2),
            new Student("Ittai", "Zeidman", 4.5, 1),
            new Student("Burr", "Sutter", 4.2, 2)};

        String[] studentNames =
                SmartArrayApp.findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(students);
        String[] expectedStudentNames = {"Borek Tomasz",
                "Kranga Antons", "Krenn Philipp", "Sutter Burr",
                "Zeidman Ittai"};

        assertArrayEquals(expectedStudentNames, studentNames);
    }

    @Test
    public void testFindDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname3() {
        Student[] students = {
            new Student("Ivar", "Grimstad", 3.9, 1),
            new Student("Ittai", "Zeidman", 4.5, 1),
            new Student("Antons", "Kranga", 4.0, 3),
            new Student("Burr", "Sutter", 4.2, 2),
            new Student("Philipp", "Krenn", 4.3, 3),
            new Student("Tomasz", "Borek", 4.1, 2),
            new Student("Ittai", "Zeidman", 4.5, 1),
            new Student("Burr", "Sutter", 4.2, 2)};

        String[] studentNames =
                SmartArrayApp.findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(students);
        String[] expectedStudentNames = {"Borek Tomasz", "Sutter Burr"};

        assertArrayEquals(expectedStudentNames, studentNames);
    }
}