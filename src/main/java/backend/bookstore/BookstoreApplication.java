package backend.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
	return (args) -> {
			Book b1 =new Book("1984", "George Orwell", 1949, "9780451524935", 9.99);
			Book b2 = new Book("Brave New World", "Aldous Huxley", 1932, "9780060850524", 8.99);
			Book b3 = new Book("Fahrenheit 451", "Ray Bradbury", 1953, "9781451673319", 10.99);

		repository.save(b1);
        repository.save(b2);
        repository.save(b3);
	};
}

}
