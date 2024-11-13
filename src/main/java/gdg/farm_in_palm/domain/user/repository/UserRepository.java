package gdg.farm_in_palm.domain.user.repository;

import gdg.farm_in_palm.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
}
