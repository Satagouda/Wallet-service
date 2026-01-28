package wallet.project.itkedu.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<String> notFound() {
        return ResponseEntity.status(404).body("Wallet not found");
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> insufficientFunds() {
        return ResponseEntity.badRequest().body("Insufficient funds");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> invalidRequest() {
        return ResponseEntity.badRequest().body("Invalid request");
    }
}
