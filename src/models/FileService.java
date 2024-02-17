package models;

public interface FileService {
    void loadData(String path);
    void saveData(String path);
    void loadDataObject(String path);
    void saveDataObject(String path);
}
