package io.github.felipeduan.sistema_reclamacoes.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHelper {

    public static ResponseEntity<Object> success(String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", true);
        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static ResponseEntity<Object> failure(String message, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", false);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
