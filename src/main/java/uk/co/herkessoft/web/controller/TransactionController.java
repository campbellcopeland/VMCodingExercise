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
import uk.co.herkessoft.web.model.OutgoingDto;
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

    @GetMapping("outgoings/{category}")
    public ResponseEntity<Double> getOutgoingsByCategory(@PathVariable("category") String category){

        Double outgoingsTotal = transactionService.getTotalOutgoings(category);
        if (-1 == outgoingsTotal) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(outgoingsTotal, HttpStatus.OK);
        }
    }

    @GetMapping("outgoings")
    public ResponseEntity<Collection<OutgoingDto>> getOutgoings(){

        Collection<OutgoingDto> outgoingDtos = transactionService.getTotalOutgoings();
        if (outgoingDtos.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(outgoingDtos, HttpStatus.OK);
        }
    }

    @GetMapping("outgoings/{category}/highest/{year}")
    public ResponseEntity<TransactionDto> getHighestOutgoingsByCategory(@PathVariable("category") String category, @PathVariable("year") Integer year){

        TransactionDto transactionDto = transactionService.getHighestOutgoings(category, year);
        if (null == transactionDto) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        }
    }

    @GetMapping("outgoings/{category}/lowest/{year}")
    public ResponseEntity<TransactionDto> getHLowestOutgoingsByCategory(@PathVariable("category") String category, @PathVariable("year") Integer year){

        TransactionDto transactionDto = transactionService.getLowestOutgoings(category, year);
        if (null == transactionDto) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        }
    }
}
