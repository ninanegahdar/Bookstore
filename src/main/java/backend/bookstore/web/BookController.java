package backend.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
