package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements core.basesyntax.List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            ensureCapacity();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }

        if (size == array.length) {
            ensureCapacity();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Index is out of bounds.");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Index is out of bounds.");
        }

        array[index] = value;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
        return element;
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds.");
        }
        T oldValue = (T) array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        size--;
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        int newCapacity = (int) (array.length * 1.5);
        array = Arrays.copyOf(array, newCapacity);
    }
}
