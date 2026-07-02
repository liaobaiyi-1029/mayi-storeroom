package com.example.mysmartshop.controller;

import com.example.mysmartshop.entity.Product;
import com.example.mysmartshop.service.CategoryService;
import com.example.mysmartshop.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer categoryId,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) BigDecimal minPrice,
                       @RequestParam(required = false) BigDecimal maxPrice,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       Model model) {
        PageInfo<Product> pageInfo = productService.findByCondition(categoryId, name, minPrice, maxPrice, pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("name", name);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("categoryList", categoryService.findAll());
        return "product/list";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("categoryList", categoryService.findAll());
        return "product/add";
    }

    @PostMapping("/add")
    public String add(Product product) {
        productService.insert(product);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryService.findAll());
        return "product/edit";
    }

    @PostMapping("/edit")
    public String edit(Product product) {
        productService.update(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
}