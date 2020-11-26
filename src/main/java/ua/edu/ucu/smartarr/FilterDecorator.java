package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyPredicate;

// Tests every element and removes it if it doesn't satisfy MyPredicate
public class FilterDecorator extends SmartArrayDecorator{
    public FilterDecorator(SmartArray array, MyPredicate predicate) {
        super(array);

        Object[] newSmartArr = new Object[smartArray.size()];
        Object[] objectsSmartArr = smartArray.toArray();

        int idxNewArr = 0;
        for (int i = 0; i < smartArray.size(); i++) {
            if (predicate.test(objectsSmartArr[i])){
                newSmartArr[idxNewArr++] = objectsSmartArr[i];
            }
        }

        smartArray = new BaseArray(newSmartArr, idxNewArr);
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
        return "Filter Decorator filtered due to MyPredicate.test";
    }

    @Override
    public int size() {
        return smartArray.size();
    }
}
