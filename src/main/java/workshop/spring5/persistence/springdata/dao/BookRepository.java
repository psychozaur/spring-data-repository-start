package workshop.spring5.persistence.springdata.dao;

import org.springframework.data.jpa.repository.Query;
import workshop.spring5.persistence.springdata.model.Book;

import java.util.List;

/*
    TODO 7 zmień interfejs na repozytorium Springow'e
    Interfejs rozszerza org.springframework.data.repository.CrudRepository
    <T, ID> : typ encji, typ klucza w encji
 */
public interface BookRepository {
    /*
        TODO 14 zadeklaruj metodę List<Book> selectAllBooksUsingQueryFromEntity()
        Użyj adnotacji @Query -  wykorzystaj definicję z encji
    */


    /*
        TODO 15 zadeklaruj metodę List<Book> selectAllBooksUsingQueryFromRepository()
        Użyj adnotacji @Query(value=("..."))
    */

}
