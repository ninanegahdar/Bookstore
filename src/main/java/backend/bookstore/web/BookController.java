package backend.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;

@Controller
public class BookController {

@Autowired
private BookRepository bookRepository;

    @GetMapping("/index")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/booklist")
    public String booklist(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/add")
    public String saveBook(Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
}

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
}
}
