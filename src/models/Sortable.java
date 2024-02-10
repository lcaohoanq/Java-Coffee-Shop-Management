package models;

import java.util.List;

public interface Sortable<T> {
    void sortAscending(List<T> list);
    void sortDescending(List<T> list);
}
