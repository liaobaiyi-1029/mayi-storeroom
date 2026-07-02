package com.example.mysmartshop.controller;

import com.example.mysmartshop.entity.Category;
import com.example.mysmartshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("categoryList", categoryService.findAll());
        return "category/list";
    }

    @GetMapping("/add")
    public String addPage() {
        return "category/add";
    }

    @PostMapping("/add")
    public String add(Category category) {
        categoryService.insert(category);
        return "redirect:/category/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "category/edit";
    }

    @PostMapping("/edit")
    public String edit(Category category) {
        categoryService.update(category);
        return "redirect:/category/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return "redirect:/category/list";
    }
}
