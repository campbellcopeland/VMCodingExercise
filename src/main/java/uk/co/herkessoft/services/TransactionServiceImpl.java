package uk.co.herkessoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.herkessoft.domain.Transaction;
import uk.co.herkessoft.repositories.TransactionRepository;
import uk.co.herkessoft.web.mappers.TransactionMapper;
import uk.co.herkessoft.web.model.OutgoingDto;
import uk.co.herkessoft.web.model.TransactionDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public Collection<TransactionDto> listTransactions(String category) {

        Collection<Transaction> transactions = transactionRepository.findByCategory(category);
        return transactions.stream().map(transactionMapper::transactionToTransactionDto).sorted(Collections.reverseOrder(new SortByDate())).collect(Collectors.toList());
    }

    @Override
    public Collection<OutgoingDto> getTotalOutgoings() {

        Collection<String> categories = transactionRepository.getCategoryList();
        Collection<OutgoingDto> outgoingDtos = categories.stream().map(category -> OutgoingDto.builder()
                .category(category)
                .totalAmount(getTotalOutgoings(category))
                .build()).collect(Collectors.toList());

        return outgoingDtos;
    }

    @Override
    public Double getTotalOutgoings(String category) {
        Collection<Transaction> transactions = transactionRepository.findByCategory(category);
        if (transactions.isEmpty()) {
            return Double.valueOf(-1);
        } else {
            return transactions.stream().mapToDouble(Transaction::getAmount).sum();
        }
    }

    @Override
    public TransactionDto getHighestOutgoings(String category, Integer year) {
        Collection<Transaction> transactions = transactionRepository.findByCategoryAndYear(category, year);
        List<TransactionDto> transactionDtos = transactions.stream().map(transactionMapper::transactionToTransactionDto).sorted(Collections.reverseOrder(new SortByAmount())).collect(Collectors.toList());
        if (transactionDtos.isEmpty()) {
            return null;
        } else {
            return transactionDtos.get(0);
        }
    }

    @Override
    public TransactionDto getLowestOutgoings(String category, Integer year) {
        Collection<Transaction> transactions = transactionRepository.findByCategoryAndYear(category, year);
        List<TransactionDto> transactionDtos = transactions.stream().map(transactionMapper::transactionToTransactionDto).sorted(new SortByAmount()).collect(Collectors.toList());
        if (transactionDtos.isEmpty()) {
            return null;
        } else {
            return transactionDtos.get(0);
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

class SortByAmount implements Comparator<TransactionDto>
{
    public int compare(TransactionDto a, TransactionDto b)
    {
        return a.getAmount().compareTo(b.getAmount());
    }
}
