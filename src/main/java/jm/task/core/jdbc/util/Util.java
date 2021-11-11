package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static String dbURl = "jdbc:mysql://localhost:3306/jaja";
    private static String dbUser = "vpr";
    private static String dbPass = "piupiu";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getMySQlConnection() {
        Connection connection = null;
        try {
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/jaja?user=vpr&password=piupiu");
            //Class.forName(driver);
            connection = DriverManager.getConnection(dbURl, dbUser, dbPass);
            if (!connection.isClosed()) System.out.println("Connected...");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return connection;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            //like hibernate.cfg.xml
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, driver);
            properties.put(Environment.URL, dbURl);
            properties.put(Environment.USER, dbUser);
            properties.put(Environment.PASS, dbPass);
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            properties.put(Environment.SHOW_SQL, "true");

            //CURRENT_SESSION_CONTEXT_CLASS Определение контекста для SessionFactory.getCurrentSession()обработки.
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            //HBM2DDL_AUTO Автоматический экспорт / обновление схемы с помощью инструмента hbm2ddl.
            properties.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }


}
