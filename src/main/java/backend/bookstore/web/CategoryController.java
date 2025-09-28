package backend.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;


@Controller
public class CategoryController {

@Autowired
private CategoryRepository categoryRepository;

@GetMapping("/categorylist")
public String categorylist(Model model) {
model.addAttribute("categories", categoryRepository.findAll());
return "categorylist";
    }

@GetMapping(value = "/addcategory")
    public String addCategory(Model model){
    model.addAttribute("category", new Category());
    return "addcategory";
    }

@PostMapping("/addcategory")
public String saveCategory(Category category) {
    categoryRepository.save(category);
    return "redirect:/categorylist";
    }
}
