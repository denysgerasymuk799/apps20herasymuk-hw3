package ua.edu.ucu;

import java.util.Arrays;
import java.util.LinkedHashSet;

import ua.edu.ucu.functions.MyComparator;
import ua.edu.ucu.functions.MyFunction;
import ua.edu.ucu.functions.MyPredicate;
import ua.edu.ucu.smartarr.*;

public class SmartArrayApp {
    public static Integer[]
            filterPositiveIntegersSortAndMultiplyBy2(Integer[] integers) {

        // lambda replacement
        MyPredicate pr = t -> ((Integer) t) > 0;
        MyComparator cmp = (o1, o2) -> ((Integer) o1) - ((Integer) o2);
        MyFunction func = t -> 2 * ((Integer) t);

        // Input: [-1, 2, 0, 1, -5, 3]
        SmartArray sa = new BaseArray(integers);

        sa = new FilterDecorator(sa, pr); // Result: [2, 1, 3];
        sa = new DistinctDecorator(sa); // Result: [2, 1, 3];
        sa = new SortDecorator(sa, cmp); // Result: [1, 2, 3]
        sa = new MapDecorator(sa, func); // Result: [2, 4, 6]

        // Alternative
//        sa = new MapDecorator(
//                    new SortDecorator(
//                        new FilterDecorator(sa, pr),
//                    cmp),
//                func);
        Object[] result = sa.toArray();
        return Arrays.copyOf(result, result.length, Integer[].class);
    }

    public static String[]
            findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(Student[] students) {

        MyPredicate pr = t -> {
            int searchYear = 2;
            int minGPA = 4;
            Student st = (Student) t;
            return (st.getYear() == searchYear && st.getGPA() >= minGPA);
        };

        SmartArray sa = new BaseArray(students);
        sa = new FilterDecorator(sa, pr);

        MyComparator cmp = (obj1, obj2) -> {
            Student st1 = (Student) obj1;
            Student st2 = (Student) obj2;
            return (st1.getSurname().compareTo(st2.getSurname()));
        };

        MyFunction func = t -> {
            Student st = (Student) t;
            return st.getSurname() + " " + st.getName();
        };

        sa = new DistinctDecorator(sa);
        sa = new SortDecorator(sa, cmp);
        sa = new MapDecorator(sa, func);

        // Hint: to convert Object[] to String[] - use the following code
        Object[] result = sa.toArray();
        return Arrays.copyOf(result, result.length, String[].class);
    }
}
