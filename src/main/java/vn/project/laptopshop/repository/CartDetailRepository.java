package vn.project.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.project.laptopshop.domain.Cart;
import vn.project.laptopshop.domain.CartDetail;
import vn.project.laptopshop.domain.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    boolean existsByCartAndProduct(Cart cart, Product product);
    CartDetail findByCartAndProduct(Cart cart, Product product);

}
