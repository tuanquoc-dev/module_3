package service;

import java.util.List;

public interface IService<T>{
    void add(T t);
    void edit(int id, T t);
    void delete(int id);
    List<T> findAll();
    T findById(int id);
}
