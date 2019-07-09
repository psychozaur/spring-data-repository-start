package workshop.spring5.persistence.springdata.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import workshop.spring5.persistence.springdata.model.Author;
import workshop.spring5.persistence.springdata.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/*
    TODO 8 dodaj do klasy adnotacje @RunWith i @ContextConfiguration
    Dodaj do metod testowych (metod z adnotacją @Test) adnotację @DirtiesContext
 */
public class BookServiceImplTest {
    /*
        TODO 9 Wstrzyknij repozytorium
     */
    @Autowired
    private BookRepository bookRepository;

    /*
        TODO 10 zaimplementuj test
     */
    @Test
    @DirtiesContext
    public void shouldSaveAbook() {
        fail();
    }

    /*
        TODO 11 zaimplementuj test
    */
    @Test
    @DirtiesContext
    public void shouldNotFindAbook() {
        fail();
    }

    /*
        TODO 12 zaimplementuj test
    */
    @Test
    @DirtiesContext
    public void deleteById() {
        fail();
    }

    /*
        TODO 16 zaimplementuj test
     */
    @Test
    @DirtiesContext
    public void shouldFindAllUsingQueryFromRepository() {
        fail();
    }

    /*
        TODO 17 zaimplementuj test
     */
    @Test
    @DirtiesContext
    public void selectAllBooksUsingQueryFromEntity() {
        fail();
    }

    //=========================================================    Helper methods
    private Book createBookWithAuthor(int no) {
        Author author = new Author("Author_name_"+no, "Author_lastName_"+no, null);
        Book book = new Book("Tytuł_"+no, "ISBN_"+no, author);
        List<Book> books = new ArrayList<Book>();
        books.add(book);
        author.setBooks(books);
        return book;
    }

    private List<Book> createBooksWithAuthor(int howMany) {
        List<Book> books = new ArrayList<>();
        for(int i = 1; i <= howMany; i++) {
            books.add(createBookWithAuthor(i));
        }
        return books;
    }

    private <T> List<T> convertIterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}