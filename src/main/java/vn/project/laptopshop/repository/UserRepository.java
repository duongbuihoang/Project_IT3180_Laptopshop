package vn.project.laptopshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.project.laptopshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User duongbui);

    List<User> findByEmail(String email);
    // List<User> findByEmailAndAddress(String email, String address);
}
