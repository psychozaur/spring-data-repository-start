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
import java.util.Optional;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

/*
    TODO 8 dodaj do klasy adnotacje @RunWith i @ContextConfiguration
    Dodaj do metod testowych (metod z adnotacją @Test) adnotację @DirtiesContext
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfig.class)
public class BookServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImplTest.class);

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
    public void shouldSaveAListOfBooks() {
        // Given
        int booksToCreate = 5;
        List<Book> books = createBooksWithAuthor(booksToCreate);
        // When
        bookRepository.saveAll(books);
        List<Book> result = convertIterableToList(bookRepository.findAll());
        // Then
        for (Book book : result){
            logger.info(book.getTitle());
        }
        assertEquals(booksToCreate, bookRepository.count());
    }

    @Test
    @DirtiesContext
    public void shouldSaveABook() {
        // Given
        int booksInDb = (int) bookRepository.count();
        Book book = createBookWithAuthor(booksInDb + 1);
        // When
        bookRepository.save(book);
        // Then
        assertEquals(1L, bookRepository.count());
    }

    /*
        TODO 11 zaimplementuj test
    */
    @Test
    @DirtiesContext
    public void shouldNotFindABook() {
        // Given
        long nonExistingId = 24364325345L;
        int booksInDb = (int) bookRepository.count();
        Book book = createBookWithAuthor(booksInDb + 1);
        long savedId = bookRepository.save(book).getId();
        // When
        boolean isExisting = bookRepository.existsById(nonExistingId);
        // Then
        assertFalse(isExisting);
    }

    /*
        TODO 12 zaimplementuj test
    */
    @Test
    @DirtiesContext
    public void deleteById() {
        // Given
        int booksInDb = (int) bookRepository.count();
        Book book = createBookWithAuthor(booksInDb + 1);
        long savedId = bookRepository.save(book).getId();
        // When
        bookRepository.deleteById(savedId);
        // Then
        assertFalse(bookRepository.existsById(savedId));
    }

    /*
        TODO 16 zaimplementuj test
     */
    @Test
    @DirtiesContext
    public void shouldFindAllUsingQueryFromRepository() {
        // Given
        int booksInDb = (int) bookRepository.count();
        Book book = createBookWithAuthor(booksInDb + 1);
        long savedId = bookRepository.save(book).getId();
        // When
        List<Book> result = bookRepository.selectAllBooksUsingQueryFromRepository();
        // Then
        assertEquals(bookRepository.findById(savedId).orElse(new Book()).getTitle(), result.get(0).getTitle());
    }

    /*
        TODO 17 zaimplementuj test
     */
    @Test
    @DirtiesContext
    public void selectAllBooksUsingQueryFromEntity() {
        // Given
        int booksInDb = (int) bookRepository.count();
        Book book = createBookWithAuthor(booksInDb + 1);
        long savedId = bookRepository.save(book).getId();
        // When
        List<Book> result = bookRepository.selectAllBooksUsingQueryFromEntity();
        // Then
        assertEquals(bookRepository.findById(savedId).orElse(new Book()).getTitle(), result.get(0).getTitle());
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