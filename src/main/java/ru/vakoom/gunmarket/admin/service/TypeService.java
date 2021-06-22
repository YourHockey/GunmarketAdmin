package ru.vakoom.gunmarket.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vakoom.gunmarket.admin.repo.TypeRepo;
import ru.vakoom.gunmarket.commondatalayer.model.Type;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepo typeRepo;

    public Optional<Type> findByShowName(String showName) {
        return typeRepo.findByShowName(showName);
    }
}
