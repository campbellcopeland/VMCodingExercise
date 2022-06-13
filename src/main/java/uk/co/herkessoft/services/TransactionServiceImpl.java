package uk.co.herkessoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.herkessoft.domain.Transaction;
import uk.co.herkessoft.repositories.TransactionRepository;
import uk.co.herkessoft.web.mappers.TransactionMapper;
import uk.co.herkessoft.web.model.OutgoingDto;
import uk.co.herkessoft.web.model.TransactionDto;

import java.time.Year;
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
        Collection<OutgoingDto> outgoingDtos = categories.stream().map(category -> getTotalOutgoings(category)).collect(Collectors.toList());

        return outgoingDtos;
    }

    @Override
    public OutgoingDto getTotalOutgoings(String category) {
        Collection<Transaction> transactions = transactionRepository.findByCategory(category);
        if (transactions.isEmpty()) {
            return null;
        } else {
            return OutgoingDto.builder()
                    .category(category)
                    .amount(transactions.stream().mapToDouble(Transaction::getAmount).sum())
                    .build();
        }
    }

    @Override
    public TransactionDto getHighestOutgoings(String category, Year year) {
        Collection<Transaction> transactions = transactionRepository.findByCategoryAndYear(category, year);
        List<TransactionDto> transactionDtos = transactions.stream().map(transactionMapper::transactionToTransactionDto).sorted(Collections.reverseOrder(new SortByAmount())).collect(Collectors.toList());
        if (transactionDtos.isEmpty()) {
            return null;
        } else {
            return transactionDtos.get(0);
        }
    }

    @Override
    public TransactionDto getLowestOutgoings(String category, Year year) {
        Collection<Transaction> transactions = transactionRepository.findByCategoryAndYear(category, year);
        List<TransactionDto> transactionDtos = transactions.stream().map(transactionMapper::transactionToTransactionDto).sorted(new SortByAmount()).collect(Collectors.toList());
        if (transactionDtos.isEmpty()) {
            return null;
        } else {
            return transactionDtos.get(0);
        }
    }

    @Override
    public OutgoingDto getAverageOutgoings(String category, Year year) {
        Collection<Transaction> transactions = transactionRepository.findByCategoryAndYear(category, year);
        return OutgoingDto.builder()
                .category(category)
                .amount(transactions.stream().mapToDouble(Transaction::getAmount).sum() / 12)
                .build();
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
