package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.models.*;

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
            session.createSQLQuery("alter books_id_seq restart with 1");

            Book book1 = Book.of("Java: эффективное программирование");
            Book book2 = Book.of("Java.Библиотека профессионала");
            Book book3 = Book.of("Java SE 8. Базовый курс");
            Book book4 = Book.of("Параллельное программирование в JAVA на практике");

            Author author1 = Author.of("Д.Блох");
            author1.getBooks().add(book1);
            author1.getBooks().add(book4);
            session.persist(author1);

            Author author2 = Author.of("К.Хортсман");
            author2.getBooks().add(book2);
            author2.getBooks().add(book3);
            session.persist(author2);

            Author author3 = Author.of("К.Гари");
            author3.getBooks().add(book2);
            session.persist(author3);

            Author author4 = Author.of("Б.Готц");
            Author author5 = Author.of("Т.Пайерлс");
            Author author6 = Author.of("Д.Боубир");
            Author author7 = Author.of("Д.Холмс");
            Author author8 = Author.of("Д.Ли");
            author4.getBooks().add(book4);
            author5.getBooks().add(book4);
            author6.getBooks().add(book4);
            author7.getBooks().add(book4);
            author8.getBooks().add(book4);

            session.persist(author4);
            session.persist(author5);
            session.persist(author6);
            session.persist(author7);
            session.persist(author8);

            Author authorToRemove1 = session.get(Author.class, 7L);
            Author authorToRemove2 = session.get(Author.class, 8L);
            session.remove(authorToRemove1);
            session.remove(authorToRemove2);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }
}
