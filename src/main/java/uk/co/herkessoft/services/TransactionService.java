package uk.co.herkessoft.services;

import uk.co.herkessoft.web.model.OutgoingDto;
import uk.co.herkessoft.web.model.TransactionDto;

import java.time.Year;
import java.util.Collection;

public interface TransactionService {
    Collection<TransactionDto> listTransactions(String category);

    Collection<OutgoingDto> getTotalOutgoings();

    OutgoingDto getTotalOutgoings(String category);

    TransactionDto getHighestOutgoings(String category, Year year);

    TransactionDto getLowestOutgoings(String category, Year year);

    OutgoingDto getAverageOutgoings(String category, Year year);
}
