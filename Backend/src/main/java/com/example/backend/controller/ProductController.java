package com.example.backend.controller;

import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

//    public @ResponseBody ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
//                                                                      @RequestParam(defaultValue = "10") int size,
//                                                                      @RequestParam(defaultValue = "product_id") String sortBy,
//                                                                      @RequestParam(defaultValue = "asc") String direction) {


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
        }

//        TODO poprawienie tej metody wyrzuca błedy nie działa
    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private ResponseEntity<Page<Product>> getProductsWithPaginationAndSort(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String field) {
        Page<Product> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new ResponseEntity<>(productsWithPagination, HttpStatus.OK);
    }


    }


