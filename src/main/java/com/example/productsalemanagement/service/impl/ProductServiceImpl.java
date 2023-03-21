package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.request.ProductRequestDto;
import com.example.productsalemanagement.entity.Cart;
import com.example.productsalemanagement.entity.Category;
import com.example.productsalemanagement.entity.Product;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.CartRepository;
import com.example.productsalemanagement.repository.CategoryRepository;
import com.example.productsalemanagement.repository.ProductRepository;
import com.example.productsalemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FCMServiceImpl fcmService;
    @Autowired
    private CartRepository cartRepository;


//    @Override
//    public Product saveProduct(ProductRequestDto productRequestDto) {
//
//
//        return productRepository.save(product);
//    }

    public List<Product> getAllProducts(Long cartId, String firebaseToken) throws ExecutionException, InterruptedException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found cart!"));

        if(cart.getCartProducts().size() > 0) {
            fcmService.sendMessageToToken("Cart", "Your Product still in the Cart\nBuy now!!", firebaseToken);
        }

        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id)));
        return product;
    }

    @Override
    public List<Product> addProduct(ProductRequestDto product) {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found")));

        Product saveProduct = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .importDate(new Date(System.currentTimeMillis()))
                .enabled(product.isEnabled())
                .category(category.get())
                .build();
        System.out.println(saveProduct);
        productRepository.save(saveProduct);
        System.out.println("saved");

        return productRepository.findAll();
    }

    @Override
    public List<Product> deleteProduct(Long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id)));
        productRepository.delete(product.get());
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist"));

        product.setName((productRequestDto.getName()));
        product.setDescription(productRequestDto.getDescription());
        product.setImage(productRequestDto.getImage());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setEnabled(productRequestDto.isEnabled());

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public List<Product> getAllProductsByCategory(int id) {
        return productRepository.findByCategory_Id(id);
    }


}

