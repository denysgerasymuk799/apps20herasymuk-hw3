package ua.edu.ucu.smartarr;

import java.util.Arrays;

// Base array for decorators
public class BaseArray implements SmartArray {
    private Object[] array;

    public BaseArray(Object[] array) {
        if (array == null) {
            throw new NullPointerException();
        }

        this.array = new Object[array.length];
        System.arraycopy(array, 0,
                this.array, 0, array.length);
    }

    public BaseArray(Object[] array, int size) {
        if (array == null) {
            throw new NullPointerException();
        }

        this.array = new Object[size];
        System.arraycopy(array, 0,
                this.array, 0, size);
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size()];
        System.arraycopy(array, 0,
                newArray, 0, size());
        return newArray;
    }

    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public String operationDescription() {
        return "BaseArray created";
    }

    @Override
    public int size() {
        return array.length;
    }
}
