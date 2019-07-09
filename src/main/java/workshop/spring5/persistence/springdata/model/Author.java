package workshop.spring5.persistence.springdata.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="author")
public class Author {
    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String lastName;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL
    )
    private List<Book> books;

    public Author() {
    }

    public Author(String name, String lastName, List<Book> books) {
        this.name = name;
        this.lastName = lastName;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(name, author.name) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(books, author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, books);
    }
}
