package uk.co.herkessoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.herkessoft.domain.Transaction;
import uk.co.herkessoft.repositories.TransactionRepository;
import uk.co.herkessoft.web.mappers.TransactionMapper;
import uk.co.herkessoft.web.model.TransactionDto;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

    @Override
    public Collection<TransactionDto> listTransactions(String category) {

        Collection<Transaction> transactions = transactionRepository.findByCategory(category);
        return transactions.stream().map(transactionMapper::transactionToTransactionDto).sorted(Collections.reverseOrder(new SortByDate())).collect(Collectors.toList());
    }

    @Override
    public Double getOutgoings(String category) {
        Collection<Transaction> transactions = transactionRepository.findByCategory(category);
        if (transactions.isEmpty()) {
            return Double.valueOf(-1);
        } else {
            return transactions.stream().mapToDouble(Transaction::getAmount).sum();
        }
    }
}

class SortByDate implements Comparator<TransactionDto>
{
    public int compare(TransactionDto a, TransactionDto b)
    {
        return a.getTransactionDate().compareTo(b.getTransactionDate());
    }
}
