package vn.project.laptopshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.project.laptopshop.domain.Role;
import vn.project.laptopshop.domain.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role save(Role role);

    void deleteById(long id);

    // List<User> findByEmail(String email);
    // List<User> findByEmailAndAddress(String email, String address);

    User findById(long id);

    Role findByName(String name);
}
