public interface List<T> {
    public void add(T value);

    /**
     * @param index of element to return, 0 through to size() - 1
     * @return element at given index
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public T get(int index);

    /**
     * @return size of the list
     */
    public int size();

    /**
     * @param index of element to remove
     * @return removed element at index
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public T remove(int index);

    /**
     * Reverse the list in-place
     */
    public void reverse();

    /**
     * NOTE: added so that our HashTable implementation is neater
     * @return true if list contains value
     */
    public boolean contains(T value);

    /**
     * Index of first occurrence of value in list
     * NOTE: added so that our HashTable implementation is neater
     * @param value to find index for
     * @return index of value, or -1 if value not in list
     */
    public int indexOf(T value);
}
