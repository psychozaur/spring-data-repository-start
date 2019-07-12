package workshop.spring5.persistence.springdata.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import workshop.spring5.persistence.springdata.config.MainConfig;
import workshop.spring5.persistence.springdata.model.Author;
import workshop.spring5.persistence.springdata.model.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfig.class)
public class AuthorServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImplTest.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DirtiesContext
    public void shouldSaveAListOfAuthors() {
        // Given
        int authorsToCreate = 5;
        List<Author> authors = createAuthorsWithABook(5);
        // When
        authorRepository.saveAll(authors);
        List<Author> result = convertIterableToList(authorRepository.findAll());
        // Then
        for (Author author : result){
            logger.info(author.getName());
        }
        assertEquals(authorsToCreate, authorRepository.count());
    }

    @Test
    @DirtiesContext
    public void shouldSaveAnAuthor() {
        // Given
        int authorsInDb = (int) authorRepository.count();
        Author author = createAuthorWithABook(authorsInDb + 1);
        // When
        authorRepository.save(author);
        // Then
        assertEquals(1L, authorRepository.count());
    }

    @Test
    @DirtiesContext
    public void shouldNotFindAnAuthor() {
        // Given
        long nonExistingId = 24364325345L;
        int authorsInDb = (int) authorRepository.count();
        Author author = createAuthorWithABook(authorsInDb + 1);
        long savedId = authorRepository.save(author).getId();

        // When
        Author maybeAuthor = authorRepository.findById(nonExistingId).orElse(null);
        // Then
        assertNull(maybeAuthor);
    }

    @Test
    @DirtiesContext
    public void shouldDeleteById() {
        // Given
        int authorsInDb = (int) authorRepository.count();
        Author author = createAuthorWithABook(authorsInDb + 1);
        long savedId = authorRepository.save(author).getId();
        // When
        authorRepository.delete(author);
        // Then
        assertFalse(authorRepository.existsById(savedId));
    }

    @Test
    @DirtiesContext
    public void shouldFindAllUsingQueryFromRepository() {
        // Given
        int authorsInDb = (int) authorRepository.count();
        Author author = createAuthorWithABook(authorsInDb + 1);
        long savedId = authorRepository.save(author).getId();
        // When
        List<Author> result = authorRepository.selectAllAuthorsUsingQueryFromRepository();
        // Then
        assertEquals(authorRepository.findById(savedId).orElse(new Author()).getName(),result.get(0).getName());
    }

    @Test
    @DirtiesContext
    public void shouldFindAllUsingQueryFromEntity() {
        // Given
        int authorsInDb = (int) authorRepository.count();
        Author author = createAuthorWithABook(authorsInDb + 1);
        long savedId = authorRepository.save(author).getId();
        // When
        List<Author> result = authorRepository.selectAllAuthorsUsingQueryFromEntity();
        // Then
        assertEquals(authorRepository.findById(savedId).orElse(new Author()).getName(),result.get(0).getName());
    }

    //=========================================================    Helper methods
    private Author createAuthorWithABook(int no) {
        Book book = new Book("Tytuł_"+no,"ISBN_"+no, null);
        List<Book> books = new ArrayList<>();
        books.add(book);

        Author author = new Author("Author_name_"+no,"Author_last_name_"+no,books);
        book.setAuthor(author);

        return author;
    }

    private List<Author> createAuthorsWithABook(int howMany) {
        List<Author> authors = new ArrayList<>();
        for(int i = 1; i <= howMany; i++) {
            authors.add(createAuthorWithABook(i));
        }
        return authors;
    }

    private <T> List<T> convertIterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}
