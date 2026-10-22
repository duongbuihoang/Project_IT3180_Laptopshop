package vn.project.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.project.laptopshop.domain.Cart;
import vn.project.laptopshop.domain.CartDetail;
import vn.project.laptopshop.domain.Product;
import vn.project.laptopshop.domain.User;
import vn.project.laptopshop.repository.CartDetailRepository;
import vn.project.laptopshop.repository.CartRepository;
import vn.project.laptopshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository,
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public Product createProduct(Product pr) {
        return this.productRepository.save(pr);
    }

    public List<Product> fetchProducts() {
        return this.productRepository.findAll();
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public Optional<Product> fetchProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            // check user has cart or not ? -> if not create new cart for user
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);

                cart = this.cartRepository.save(otherCart);
            }

            // save cart detail
            // find product by id
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product realProduct = productOptional.get();

                // check product exists in cart detail or not
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);

                if (oldDetail == null) {

                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(realProduct);
                    cartDetail.setQuantity(1);
                    cartDetail.setPrice(realProduct.getPrice());
                    this.cartDetailRepository.save(cartDetail);

                    // update sum in cart, sum: tổng số loại sản phẩm trong giỏ hàng
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    // oldDetail.setPrice(oldDetail.getPrice() + realProduct.getPrice());
                    this.cartDetailRepository.save(oldDetail);
                }

            }

        }

    }
}
