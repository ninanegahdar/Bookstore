package backend.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.CategoryRepository;


@Controller
public class BookController {

@Autowired
private BookRepository bookRepository;

@Autowired
private CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/booklist")
    public String booklist(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @RequestMapping("/books")
    public @ResponseBody List<Book> findAllBooks(){
        return (List<Book>) bookRepository.findAll();
    }
    
    @RequestMapping("/books/{id}")
    public @ResponseBody Optional<Book> getOneBook(@PathVariable(name = "id") Long Id){
        return bookRepository.findById(Id);
    }

    @RequestMapping(value = "/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/add")
    public String saveBook(Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/editbook/{id}")
    public String getEditBook(@PathVariable(name="id") Long id, Model model) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }

    @PostMapping("/updatebook")
    public String updateBook(Book book) {
    bookRepository.save(book);
    return "redirect:/booklist";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
