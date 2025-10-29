package backend.bookstore;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;
import backend.bookstore.domain.User;
import backend.bookstore.domain.UserRepository;

@DataJpaTest
public class BookstoreRepositoryTest {

@Autowired
private BookRepository bookRepository;

@Autowired
private CategoryRepository categoryRepository;

@Autowired
private UserRepository userRepository;

    @Test
    public void createNewBook() { //ID luotu ja oikea kategoria
        Category category = new Category("Fantasy");
        categoryRepository.save(category);

        Book book = new Book("Harry Potter and the Goblet of Fire", "J.K. Rowling", 2000, "ISBN12345", 15.99, category);
        bookRepository.save(book);

        assertThat(book.getId()).isNotNull();
        assertThat(book.getCategory().getName()).isEqualTo("Fantasy");
    }
    @Test
    public void findByTitleShouldReturnBook() { //oikea kirjialija
        Category category = new Category("Fantasy");
        categoryRepository.save(category);

        Book book = new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999, "ISBN54321", 15.99, category);
        bookRepository.save(book);

        List<Book> books = bookRepository.findByTitle("Harry Potter and the Prisoner of Azkaban");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("J.K. Rowling");
    }

    @Test
    public void deleteBook() { //ei löydy tietokannasta
        Category category = new Category("Fantasy");
        categoryRepository.save(category);

        Book book = new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998, "ISBN77777", 15.99, category);
        bookRepository.save(book);
        Long id = book.getId();

        bookRepository.deleteById(id);
        Optional<Book> deletedBook = bookRepository.findById(id);
        assertThat(deletedBook).isEmpty();
    }


    @Test
    public void createNewCategory() { //ID luotu
        Category category = new Category("Adventure");
        categoryRepository.save(category);
        assertThat(category.getCategoryid()).isNotNull();
    }

    @Test
    public void findByNameShouldReturnCategory() { //löytyy oikealla nimellä
        Category category = new Category("Fantasy");
        categoryRepository.save(category);

        List<Category> categories = categoryRepository.findByName("Fantasy");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Fantasy");
    }

    @Test
    public void deleteCategory() { //ei löydy enää tietokannasta
        Category category = new Category("Mystery");
        categoryRepository.save(category);
        Long id = category.getCategoryid();

        categoryRepository.deleteById(id);
        Optional<Category> deleted = categoryRepository.findById(id);
        assertThat(deleted).isEmpty();
    }

    @Test
    public void createNewUser() {
        User user = new User("testuser", "password123", "test@user.com", "USER");
        userRepository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void findByUsernameShouldReturnUser() {
        User user = new User("uniqueuser", "adminpass", "unique@bookstore.com", "ADMIN");
        userRepository.save(user);

        User found = userRepository.findByUsername("uniqueuser");
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("unique@bookstore.com");
    }

    @Test
    public void deleteUser() {
        User user = new User("harry", "magic123", "harry@hogwarts.com", "USER");
        userRepository.save(user);
        Long id = user.getId();

        userRepository.deleteById(id);
        Optional<User> deletedUser = userRepository.findById(id);
        assertThat(deletedUser).isEmpty();
    }
}