package com.productservice.demo.Controller;

import com.productservice.demo.Model.Product;
import com.productservice.demo.Model.UpdateProduct;
import com.productservice.demo.Service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/getAllProducts")
  public List<Product> getAllPRoducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable long id) {
    return productService.getProductById(id);
  }

  @PostMapping("/addProduct")
  public Product addProduct(@RequestBody Product product) {
    return productService.addProduct(product);
  }

  @PatchMapping("/updateProduct/{id}")
  public Product updateProduct(@RequestBody UpdateProduct updateProduct, @PathVariable long id) {
    return productService.updateProduct(updateProduct, id);
  }

  @DeleteMapping("/DeleteProduct/{id}")
  public String deleteProduct(@PathVariable long id) {
    return productService.deleteProduct(id);
  }
}
