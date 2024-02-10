package models;

public interface Searcher<T> {
    boolean checkToExist(String code);
    int searchIndexByCode(String code);
    T searchObjectByCode(String code);
    int searchIndexByName(String name);
    T searchObjectByName(String name);
}
