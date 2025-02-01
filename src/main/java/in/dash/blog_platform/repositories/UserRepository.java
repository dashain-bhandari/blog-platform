package in.dash.blog_platform.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.dash.blog_platform.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {
Optional<User> findByEmail(String email);
}
