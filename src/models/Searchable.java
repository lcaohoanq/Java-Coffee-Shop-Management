package models;

public interface Searchable<T> {
    boolean checkToExist(String code);
    int searchIndexByCode(String code);
    T searchObjectByCode(String code);
    int searchIndexByName(String name);
    T searchObjectByName(String name);
}
