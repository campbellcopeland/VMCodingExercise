package uk.co.herkessoft.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.herkessoft.services.TransactionService;
import uk.co.herkessoft.web.model.TransactionDto;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("transactions/{category}")
    public ResponseEntity<Collection<TransactionDto>> getTransactionsByCategory(@PathVariable("category") String category){

        Collection<TransactionDto> transactionDtos = transactionService.listTransactions(category);
        if (transactionDtos.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
        }
    }
}
