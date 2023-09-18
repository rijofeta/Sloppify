package Sloppify.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SloppifyUserRepository extends JpaRepository<SloppifyUser, Long> {

    Optional<SloppifyUser> findByEmail(String email);
}
