package workshop.spring5.persistence.springdata.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import workshop.spring5.persistence.springdata.model.Author;

import java.util.List;

/*
    TODO 15 analogicznie do BookRepository - implementacja metod serwisowych i ich testy
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query(name = "getAllAuthors")
    public List<Author> selectAllAuthorsUsingQueryFromEntity();

    @Query("SELECT a FROM Author a")
    public List<Author> selectAllAuthorsUsingQueryFromRepository();
}
