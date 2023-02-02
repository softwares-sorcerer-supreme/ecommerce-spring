package com.example.productsalemanagement;

import com.example.productsalemanagement.entity.*;
import com.example.productsalemanagement.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Optional;

@SpringBootApplication
public class ProductSaleManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductSaleManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository
            , OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, PasswordEncoder encoder) {
		return args -> {
            categoryRepository.save(new Category("Vegetable", true));
            categoryRepository.save(new Category("Fruit", true));
            Optional<Category> category = categoryRepository.findById(1);
            Optional<Category> category2 = categoryRepository.findById(2);
			Product product_1 = productRepository.save(new Product("Rau", "Rau nay de an", "123", new Date(System.currentTimeMillis()),10000, 50,  new Date(System.currentTimeMillis()), true, category.get()));
            Product product_2 = productRepository.save(new Product("Cu", "Cu nay de nau", "123", new Date(System.currentTimeMillis()),5000, 30,  new Date(System.currentTimeMillis()), true, category.get()));
            Product product_3 = productRepository.save(new Product("Qua", "Qua nay de nhin", "123", new Date(System.currentTimeMillis()),1000, 20,  new Date(System.currentTimeMillis()), true, category2.get()));

            Role role_admin = roleRepository.save(new Role("ROLE_ADMIN", "Admintration"));
            Role role_user = roleRepository.save(new Role("ROLE_USER", "User"));

            User admin = userRepository.save(new User("haih", encoder.encode("123"), "Manh Hai", "PN", "0123123123", "hai@gmail.com", true, role_admin));
            User user = userRepository.save(new User("thain", encoder.encode("123"), "Minh Thai", "Q9", "0123123123", "thai@gmail.com", true, role_user));

            Order order = orderRepository.save(new Order("PN", admin));
            orderDetailRepository.save(new OrderDetail(product_1, order, 2));
            orderDetailRepository.save(new OrderDetail(product_2, order, 4));

		};
	}


}
