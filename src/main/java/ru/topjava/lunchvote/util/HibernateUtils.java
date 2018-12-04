package ru.topjava.lunchvote.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.topjava.lunchvote.model.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Антон on 14.07.2018.
 */
public class HibernateUtils {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");

    public static EntityManagerFactory getEntityManagerFactory()
    {
        return factory;
    }

}
