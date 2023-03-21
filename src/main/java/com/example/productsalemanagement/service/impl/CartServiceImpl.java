package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.response.ProductCartResponse;
import com.example.productsalemanagement.dto.response.ProductResponseDTO;
import com.example.productsalemanagement.dto.response.SuccessResponse;
import com.example.productsalemanagement.entity.Cart;
import com.example.productsalemanagement.entity.CartProduct;
import com.example.productsalemanagement.entity.Product;
import com.example.productsalemanagement.entity.User;
import com.example.productsalemanagement.exception.BadRequestException;
import com.example.productsalemanagement.exception.OutOfStockException;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.CartProductRepository;
import com.example.productsalemanagement.repository.CartRepository;
import com.example.productsalemanagement.repository.ProductRepository;
import com.example.productsalemanagement.repository.UserRepository;
import com.example.productsalemanagement.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public List<ProductCartResponse> getProductsByCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));

        List<ProductCartResponse> listProduct = new ArrayList<>();
        cartProductRepository.findByCart_Id(id).forEach(item -> {
            listProduct.add(ProductCartResponse.builder()
                    .productId(item.getProduct().getId())
                    .productName(item.getProduct().getName())
                    .imgUrl(item.getProduct().getImage())
                    .quantity(item.getQuantity())
                    .price(item.getProduct().getPrice())
                    .build());
        });

        return listProduct;
    }

    @Override
    public SuccessResponse addProductToCart(Long id, Long productId, int quantity) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

//        if(product.getQuantity() < quantity)
//            throw new OutOfStockException("Not enough quantity for this product!");

//        product.setQuantity(product.getQuantity() - quantity);

//        Product productNew = productRepository.save(product);

        CartProduct cartProduct = cartProductRepository.findByProduct_IdAndCart_Id(productId, cart.getId());

        if (cartProduct == null)
            cartProductRepository.save(CartProduct.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .build());

        else {
            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
            cartProductRepository.save(cartProduct);
        }

        return SuccessResponse.builder()
                .message("SucessfullY!")
                .status(200)
                .build();
    }

    @Override
    public List<ProductCartResponse> deleteProduct(Long id, Long productId) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

        CartProduct cartProduct = cartProductRepository.findByProduct_IdAndCart_Id(product.getId(), cart.getId());

        cartProductRepository.delete(cartProduct);


        return getProductsByCart(id);
    }

    @Override
    public List<ProductCartResponse> updateQuantity(Long id, Long productId, int quantity) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

        CartProduct cartProduct = cartProductRepository.findByProduct_IdAndCart_Id(product.getId(), cart.getId());
        cartProduct.setQuantity(quantity);
        cartProductRepository.save(cartProduct);

        return getProductsByCart(id);
    }
}
