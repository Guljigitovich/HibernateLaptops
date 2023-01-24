package dao;

import configuration.DataBaseConnection;
import enums.OperationSystem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import model.Laptop;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LaptopImpl implements LaptopDao,AutoCloseable{
    private EntityManagerFactory entityManagerFactory = DataBaseConnection.createEntityManagerFactory();
    @Override
    public Laptop saveLaptop(Laptop laptop) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(laptop);
        entityManager.getTransaction().commit();
        entityManager.close();

        return laptop;
    }

    @Override
    public List<Laptop> saveAll(List<Laptop> laptops) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Laptop l:laptops) {
            entityManager.persist(l);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        return laptops;
    }

    @Override
    public Laptop deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Laptop laptop = (Laptop) entityManager.createQuery("delete from Laptop where :id")
                .setParameter("id",id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();

        return laptop;
    }

    @Override
    public void deleteAll() {
      EntityManager entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();
      entityManager.createQuery("delete from Laptop ");
      entityManager.getTransaction().commit();
      entityManager.close();
      
    }

    @Override
    public List<Laptop> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
       List<Laptop>laptopList = entityManager.createQuery("select a from Laptop a",Laptop.class).getResultList();
         entityManager.getTransaction().commit();
         entityManager.close();
        return laptopList;
    }

    @Override
    public Laptop update(Long id, Laptop laptop) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Laptop laptop1 = (Laptop) entityManager.createQuery("update Laptop set id=?",Laptop.class)
                .setParameter("id",id).setParameter("laptop",laptop).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return laptop1;
    }

    @Override
    public Map<OperationSystem, List<Laptop>> groupBy() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        entityManager.createQuery("select operationSystem,count(p) from Laptop p group by operationSystem",Laptop.class)
//                        .getResultList().stream().collect(Collectors.toMap(laptop -> ((Number) laptop.get("operationSystem")).intValue(),
//                                                                            laptop -> (Number) laptop.get("List<Laptop>>")
        Map<OperationSystem, List<Laptop>> listMap = entityManager.createQuery("select l from Laptop l", Laptop.class)
                .getResultStream()
                .collect(Collectors.groupingBy(Laptop::getOperationSystem));
        entityManager.getTransaction().commit();
        entityManager.close();
        return listMap;
    }

    @Override
    public List<Laptop> sortByDifferentColumn(String column, String ascOrDesc) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("from Laptop l order by l.id desc ,l.id asc ");
        List<Laptop> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        entityManager.getTransaction().commit();
        entityManager.close();

        return resultList;
    }

    @Override
    public void close() throws Exception {
    entityManagerFactory.close();
    }
}
