package backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;
import backend.bookstore.domain.User;
import backend.bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository categoryRepository, UserRepository userRepository) {
	return (args) -> {
		log.info("Save sample categories:");
		Category drama = new Category("Drama");
		Category scifi = new Category("Scifi");
        Category comic = new Category("Comic");

		categoryRepository.save(drama);
		categoryRepository.save(scifi);
		categoryRepository.save(comic);

		log.info("Save books:");
		Book b1 = new Book("1984", "George Orwell", 1949, "9780451524935", 9.99, scifi);
		Book b2 = new Book("Brave New World", "Aldous Huxley", 1932, "9780060850524", 8.99, scifi);
		Book b3 = new Book("Fahrenheit 451", "Ray Bradbury", 1953, "9781451673319", 10.99, drama);

		repository.save(b1);
        repository.save(b2);
        repository.save(b3);

		log.info("Fetch all categories");
			for (Category category : categoryRepository.findAll()) {
			log.info(category.toString());
			}

		log.info("Fetch all books:");
	        for (Book book : repository.findAll()) {
	        log.info(book.toString());
			}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user1 = new User("user", passwordEncoder.encode("user123"), "user@example.com", "USER");
        User user2 = new User("admin", passwordEncoder.encode("admin123"), "admin@example.com", "ADMIN");

        userRepository.save(user1);
        userRepository.save(user2);

        log.info("Sample users saved:");
        for (User u : userRepository.findAll()) {
            log.info(u.toString());
        }
	};
}
}