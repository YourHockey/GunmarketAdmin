package ru.vakoom.gunmarket.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vakoom.gunmarket.commondatalayer.model.Type;

import java.util.Optional;

public interface TypeRepo extends JpaRepository<Type, Long> {
    Optional<Type> findByShowName(String showName);
}
