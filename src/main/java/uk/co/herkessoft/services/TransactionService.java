package uk.co.herkessoft.services;

import uk.co.herkessoft.web.model.TransactionDto;

import java.util.Collection;

public interface TransactionService {
    Collection<TransactionDto> listTransactions(String category);

}
