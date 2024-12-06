package com.example.backend.controller;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.RegisterRequest;
//import com.example.backend.service.UserService;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins =" http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(RegisterRequest registerRequest ) {
    if(userRepository.findByUsername(registerRequest.getUsername()) != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");

    }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword()); // Pamiętaj o szyfrowaniu hasła
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
}
}





//    public ProductController(ProductService productService) {
//        this.productService = productService;
//
//    }
//
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        List<Product> products = productService.findAll();
//        return ResponseEntity.ok(products);
//    }
//
////    public @ResponseBody ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
////                                                                      @RequestParam(defaultValue = "10") int size,
////                                                                      @RequestParam(defaultValue = "product_id") String sortBy,
////                                                                      @RequestParam(defaultValue = "asc") String direction) {
//
//
//    @GetMapping("/products/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable long id) {
//        Product product = productService.findById(id);
//        return ResponseEntity.ok(product);
//    }
//
//    //        TODO poprawienie tej metody wyrzuca błedy nie działa
//    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
//    private ResponseEntity<Page<Product>> getProductsWithPaginationAndSort(
//            @PathVariable int offset,
//            @PathVariable int pageSize,
//            @PathVariable String field) {
//        Page<Product> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
//        return new ResponseEntity<>(productsWithPagination, HttpStatus.OK);
//    }
//
//
//}