package workshop.spring5.persistence.springdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
/*
    TODO 2 dodaj adnotacje
            @EnableJpaRepositories - wskazuje na pakiet z interfejsami Author i Book Repository
 */
public class MainConfig {
    /*
        TODO 3 definicja ziarna springa - DataSource
        Metoda zwraca org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
        z ustawionym typem EmbeddedDatabaseType.H2
     */

    /*
        TODO 4 definicja ziarna springa - JpaTransactionManager
        Metoda przyjmuje EntityManagerFactory i zwraca obiekt JpaTransactionManager
        Użyj konstruktora JpaTransactionManager(EntityManagerFactory emf)
     */

    /*
        TODO 5 definicja ziarna springa - JpaVendorAdapter
        W metodzie
                utwórz obiekt HibernateJpaVendorAdapter
                ustaw w nim
                            dataSource na utworzoną wcześniej definicję
                            flagę generateDdl na true
                            showSql na true
                zwróć obiekt
     */
    /*
        TODO 6 definicja ziarna springa - LocalContainerEntityManagerFactoryBean
        W metodzie
                utwórz obiekt LocalContainerEntityManagerFactoryBean
                ustaw w nim (na utworzone wcześniej definicje)
                            DataSource
                            JpaVendorAdapter
                            pakiet do przeskanowania - encje
                zwróć obiekt
    */

}