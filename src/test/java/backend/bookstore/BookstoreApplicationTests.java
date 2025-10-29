package backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import backend.bookstore.web.BookController;
import backend.bookstore.web.CategoryController;

@SpringBootTest
class BookstoreApplicationTests {

@Autowired
private BookController bookController;

@Autowired
private CategoryController categoryController;

    @Test
    public void contexLoads() throws Exception {
    assertThat(bookController).isNotNull();
	assertThat(categoryController).isNotNull();
    }

	}