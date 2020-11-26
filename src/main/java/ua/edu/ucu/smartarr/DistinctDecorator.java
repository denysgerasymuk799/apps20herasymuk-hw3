package ua.edu.ucu.smartarr;

import java.util.Arrays;
import java.util.LinkedHashSet;

// Remove duplicates from SmartArray. Use method equals() to compare objects
public class DistinctDecorator extends SmartArrayDecorator{

    public DistinctDecorator(SmartArray array){
        super(array);

        // LinkedHashSet saves objects in such order,
        // in which they were added
        LinkedHashSet<Object> linkedHashSet = new LinkedHashSet<>(Arrays.asList(smartArray.toArray()));
        smartArray = new BaseArray(linkedHashSet.toArray());
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
        return "Distinct Decorator deleted duplicates";
    }

    @Override
    public int size() {
        return smartArray.size();
    }
}
