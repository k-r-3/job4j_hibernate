package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.models.Brand;
import ru.job4j.hibernate.models.CarType;
import ru.job4j.hibernate.models.Role;
import ru.job4j.hibernate.models.User;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Brand brand = Brand.of("Chevrolet");
            List<CarType> types = new ArrayList<>();
            types.add(CarType.of("Lacetti"));
            types.add(CarType.of("Aveo"));
            types.add(CarType.of("Tahoe"));
            types.add(CarType.of("Camaro"));
            types.add(CarType.of("Niva"));
            for (int i = 0; i < types.size(); i++) {
                session.save(types.get(i));
                brand.addType(session.load(CarType.class, i + 1));
            }
            session.save(brand);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
