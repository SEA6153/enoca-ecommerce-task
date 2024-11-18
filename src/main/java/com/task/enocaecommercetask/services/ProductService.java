package com.task.enocaecommercetask.services;

import com.task.enocaecommercetask.dto.ProductDTO;
import com.task.enocaecommercetask.model.Product;
import com.task.enocaecommercetask.repostories.CartRepository;
import com.task.enocaecommercetask.repostories.OrderItemRepository;
import com.task.enocaecommercetask.repostories.OrderRepository;
import com.task.enocaecommercetask.repostories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {

        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductStock(productDTO.getProductStock());
        productRepository.save(product);

        return productDTO;
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setProductName(productDTO.getProductName());
            product.setProductPrice(productDTO.getProductPrice());
            product.setProductStock(productDTO.getProductStock());
            productRepository.save(product);
            return productDTO;
        }
        else {
            throw new RuntimeException("Product not found!");
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO getProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductName(product.getProductName());
                    productDTO.setProductPrice(product.getProductPrice());
                    productDTO.setProductStock(productDTO.getProductStock());
                    return productDTO;
                }).orElseThrow(() -> new RuntimeException("Product not found!"));
    }
}
