package ru.vakoom.gunmarket.admin.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@NoArgsConstructor
public class TypeNotFoundException extends RuntimeException {
    public TypeNotFoundException(String message) {
        super(message);
    }
}
