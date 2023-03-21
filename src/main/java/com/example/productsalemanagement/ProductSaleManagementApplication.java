package com.example.productsalemanagement;

import com.example.productsalemanagement.entity.*;
import com.example.productsalemanagement.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Optional;

@SpringBootApplication
public class ProductSaleManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductSaleManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository
            , CartRepository cartRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
		return args -> {
            categoryRepository.save(new Category("Vegetable", true));
            categoryRepository.save(new Category("Fruit", true));
            Optional<Category> category = categoryRepository.findById(1);
            Optional<Category> category2 = categoryRepository.findById(2);
			Product product_1 = productRepository.save(new Product("Cam sành vắt nước túi 3kg (18 - 24 trái)", "Cam sành là trái cây rất thông dụng trong đời sống, được ưu tiên chọn mua bởi tác dụng thần kỳ của nó mang lại cho sức khỏe chúng ta. Đặc điểm vỏ cam sành màu xanh đến xanh vàng khi chín, sần và dày, tép màu vàng cam đậm, nhiều nước, vị ngọt chua mùi rất thơm và khá nhiều hạt. Phù hợp để vắt nước.", "https://cdn.tgdd.vn/Products/Images/8788/302675/bhx/cam-sanh-vat-nuoc-tui-3kg-18-24-trai-202302151633475672.jpg", new Date(System.currentTimeMillis()),10000, 50,  12, true, category.get(), "Việt Nam"));
            Product product_2 = productRepository.save(new Product("Táo Koru New Zealand thùng 18kg", "Táo Koru là trái cây nhập khẩu New Zealand, có vỏ ngoài màu đỏ trên nền vàng, có giòn, mọng nước, có vị ngọt tự nhiên. Đặc biệt táo Koru có hương vị của mật ong. Táo là loại trái cây được nhiều người yêu thích chọn mua.", "https://cdn.tgdd.vn/Products/Images/8788/301997/bhx/tao-koru-new-zealand-thung-18kg-202301170811583594.jpg", new Date(System.currentTimeMillis()),5000, 30,  12, true, category.get(), "New Zealand"));
            Product product_3 = productRepository.save(new Product("Lê đường Trung Quốc thùng 9kg", "Lê đường là trái cây nhập khẩu Trung Quốc được nhiều người Việt Nam ưa chuộng. Lê đường có vị ngọt đậm hơn lê đá, nhiều nước và thịt ít sạn cát hơn nên khi ăn cảm thấy mềm và xốp có màu vàng tươi, rất bắt mắt.", "https://cdn.tgdd.vn/Products/Images/8788/295167/bhx/le-duong-thung-9kg-202210250813494774.jpg", new Date(System.currentTimeMillis()),1000, 20,  12, true, category2.get(), "Trung Quốc"));

            Role role_admin = roleRepository.save(new Role("ADMIN", "ROLE_ADMIN"));
            Role role_user = roleRepository.save(new Role("USER", "ROLE_USER"));

            Cart cart = cartRepository.save(new Cart());

            User admin = userRepository.save(new User("haih", "123", "Manh Hai", "PN", "0123123123", "hai@gmail.com", true, role_admin, null));
            User user = userRepository.save(new User("thain", "123", "Minh Thai", "Q9", "0123123123", "thai@gmail.com", true, role_user, cart));

//            Order order = orderRepository.save(new Order("PN", user));
//            orderDetailRepository.save(new OrderDetail(product_1, order, 2));
//            orderDetailRepository.save(new OrderDetail(product_2, order, 4));

		};
	}


}
