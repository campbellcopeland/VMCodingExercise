package uk.co.herkessoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.herkessoft.domain.Transaction;
import uk.co.herkessoft.domain.TransactionTypeEnum;
import uk.co.herkessoft.web.mappers.TransactionMapper;
import uk.co.herkessoft.web.model.TransactionDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionMapper transactionMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

    @Override
    public Collection<TransactionDto> listTransactions(String category) {

        Collection<Transaction> transactions = buildTransactions();
        transactions.removeIf(t -> !t.getCategory().equalsIgnoreCase(category));

        List<TransactionDto> transactionDtos = transactions.stream().map(transactionMapper::transactionToTransactionDto).collect(Collectors.toList());
        Collections.sort(transactionDtos, Collections.reverseOrder(new SortByDate()));
        return transactionDtos;
    }

    private Collection<Transaction> buildTransactions() {
        Collection<Transaction> transaction = new ArrayList<>();
        transaction.add(Transaction.builder()
                .transactionDate(LocalDate.parse("01/Nov/2020", formatter))
                .vendor("Morrisons")
                .type(TransactionTypeEnum.CARD)
                .amount(new BigDecimal(10.40))
                .category("Groceries")
                .build());

        transaction.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Oct/2020", formatter))
                .vendor("CYBG")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(new BigDecimal(600))
                .category("MyMonthlyDD")
                .build());

        transaction.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Oct/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(new BigDecimal(40))
                .category("MyMonthlyDD")
                .build());

        transaction.add(Transaction.builder()
                .transactionDate(LocalDate.parse("01/Oct/2020", formatter))
                .vendor("M&S")
                .type(TransactionTypeEnum.CARD)
                .amount(new BigDecimal(5.99))
                .category("Groceries")
                .build());

        transaction.add(Transaction.builder()
                .transactionDate(LocalDate.parse("30/Sept/2020", formatter))
                .vendor("McMillan")
                .type(TransactionTypeEnum.INTERNET)
                .amount(new BigDecimal(10))
                .category("")
                .build());

        return transaction;
    }
}

class SortByDate implements Comparator<TransactionDto>
{
    public int compare(TransactionDto a, TransactionDto b)
    {
        return a.getTransactionDate().compareTo(b.getTransactionDate());
    }
}
