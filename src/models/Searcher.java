package models;

public interface Searcher<T> {
    boolean checkToExist(String code);
    int searchIndex(String code);
    T searchObject(String code);
    int searchIndexByName(String name);
    T searchObjectByName(String name);
}
