package vn.project.laptopshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.project.laptopshop.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User save(User duongbui);
}
