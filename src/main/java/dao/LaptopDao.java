package dao;

import enums.OperationSystem;
import model.Laptop;

import java.util.List;
import java.util.Map;

public interface LaptopDao {
    Laptop saveLaptop(Laptop laptop);

    public List<Laptop> saveAll(List<Laptop> laptops);

    public Laptop deleteById(Long id);

    public void deleteAll();

    public List<Laptop> findAll();

    public Laptop update(Long id, Laptop laptop);

    Map<OperationSystem, List<Laptop>> groupBy();

    List<Laptop> sortByDifferentColumn(String column, String ascOrDesc);
}
