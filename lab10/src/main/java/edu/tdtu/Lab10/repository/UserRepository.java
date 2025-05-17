package edu.tdtu.Lab10.repository;

import edu.tdtu.Lab10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public boolean existsByName(String name);
    public Optional<User> findByName(String name);
}
