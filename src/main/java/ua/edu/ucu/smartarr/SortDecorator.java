package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyComparator;

import java.util.Arrays;

// Sorts elements using MyComparator to compare them
public class SortDecorator extends SmartArrayDecorator {
    public SortDecorator(SmartArray array, MyComparator cmp) {
        super(array);

        // sort according to cmp function
        Object[] newSmartArr = Arrays.stream(smartArray.toArray())
                .sorted(cmp).toArray();
        smartArray = new BaseArray(newSmartArr);
    }

    @Override
    public Object[] toArray() {
        return smartArray.toArray();
    }

    public String toString() {
        return smartArray.toString();
    }

    @Override
    public String operationDescription() {
        return "Sort Decorator sorted smartArray due to MyComparator";
    }

    @Override
    public int size() {
        return smartArray.size();
    }
}
