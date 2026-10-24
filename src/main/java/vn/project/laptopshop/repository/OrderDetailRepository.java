package vn.project.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.project.laptopshop.domain.OrderDetail;
import vn.project.laptopshop.domain.User;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
