package com.n01635700.n01635700_hirparatest3ims.controller;

import com.n01635700.n01635700_hirparatest3ims.entity.Product;
import com.n01635700.n01635700_hirparatest3ims.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false, name = "search") String search, Model model) {
        if (search != null) {
            try {
                Long id = Long.parseLong(search);
                Product product = productService.getProductById(id);
                model.addAttribute("products", product != null ? List.of(product) : List.of());
            } catch (NumberFormatException e) {
                model.addAttribute("error", "Invalid ID format");
                model.addAttribute("products", productService.getAllProducts());
            }
        } else {
            model.addAttribute("products", productService.getAllProducts());
        }
        return "listing";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/store")
    public String store(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create";
        }
        productService.saveProduct(product);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("error", "Product not found");
            return "listing";
        }
        model.addAttribute("product", product);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit";
        }
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/products/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/";
    }
}
