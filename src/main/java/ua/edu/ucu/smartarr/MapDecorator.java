package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyFunction;

// Map every element to another object using MyFunction
public class MapDecorator extends SmartArrayDecorator {

    public MapDecorator(SmartArray array, MyFunction myFunc) {
        super(array);

        Object[] newSmartArr = new Object[smartArray.size()];
        Object[] objectsSmartArr = smartArray.toArray();

        int idxNewArr = 0;
        for (int i = 0; i < smartArray.size(); i++) {
            newSmartArr[idxNewArr++] = myFunc.apply(objectsSmartArr[i]);
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
        return "Map Decorator changed smartArray due to MyFunction.apply";
    }

    @Override
    public int size() {
        return smartArray.size();
    }
}
