package uk.co.herkessoft.services;

import uk.co.herkessoft.web.model.OutgoingDto;
import uk.co.herkessoft.web.model.TransactionDto;

import java.util.Collection;

public interface TransactionService {
    Collection<TransactionDto> listTransactions(String category);

    Collection<OutgoingDto> getTotalOutgoings();

    Double getTotalOutgoings(String category);

    TransactionDto getHighestOutgoings(String category, Integer year);

    TransactionDto getLowestOutgoings(String category, Integer year);
}
