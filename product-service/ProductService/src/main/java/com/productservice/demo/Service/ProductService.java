package com.productservice.demo.Service;

import com.productservice.demo.Model.Product;
import com.productservice.demo.Model.UpdateProduct;
import com.productservice.demo.Repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found for id : " + id));
  }

  public Product addProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(UpdateProduct updateProduct, long id) {
    Product product =
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

    if (updateProduct.getName() != null) {
      product.setName(updateProduct.getName());
    }

    if (updateProduct.getPrice() != null) {
      product.setPrice(updateProduct.getPrice());
    }

    if (updateProduct.getDescription() != null) {
      product.setDescription(updateProduct.getDescription());
    }

    if (updateProduct.getAvailability() != null) {
      product.setAvailability(updateProduct.getAvailability());
    }

    return productRepository.save(product);
  }

  public String deleteProduct(long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found !"));
    productRepository.delete(product);

    return "Product Deleted Successfully!!";
  }
}
