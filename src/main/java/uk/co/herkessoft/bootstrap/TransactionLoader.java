package uk.co.herkessoft.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uk.co.herkessoft.domain.Transaction;
import uk.co.herkessoft.domain.TransactionTypeEnum;
import uk.co.herkessoft.repositories.TransactionRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Component
public class TransactionLoader implements CommandLineRunner {

    private final TransactionRepository transactionRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
    
    @Override
    public void run(String... args) {
        
        if(transactionRepository.count() == 0 ) {
            loadTransactionObjects();
        }
    }

    private void loadTransactionObjects() {
        Collection<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("01/Nov/2020", formatter))
                .vendor("Morrisons")
                .type(TransactionTypeEnum.CARD)
                .amount(Double.valueOf("10.40"))
                .category("Groceries")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Oct/2020", formatter))
                .vendor("CYBG")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("600"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Oct/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("01/Oct/2020", formatter))
                .vendor("M&S")
                .type(TransactionTypeEnum.CARD)
                .amount(Double.valueOf("5.99"))
                .category("Groceries")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("30/Sept/2020", formatter))
                .vendor("McMillan")
                .type(TransactionTypeEnum.INTERNET)
                .amount(Double.valueOf("10.99"))
                .category("Shopping")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("30/Sept/2021", formatter))
                .vendor("McMillan")
                .type(TransactionTypeEnum.INTERNET)
                .amount(Double.valueOf("11.77"))
                .category("Shopping")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("30/Sept/2019", formatter))
                .vendor("McMillan")
                .type(TransactionTypeEnum.INTERNET)
                .amount(Double.valueOf("9.88"))
                .category("Shopping")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Jan/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Feb/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Mar/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Apr/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/May/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Jun/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Jul/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Aug/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Sept/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Nov/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactions.add(Transaction.builder()
                .transactionDate(LocalDate.parse("28/Dec/2020", formatter))
                .vendor("PureGym")
                .type(TransactionTypeEnum.DIRECT_DEBIT)
                .amount(Double.valueOf("40"))
                .category("MyMonthlyDD")
                .build());

        transactionRepository.saveAll(transactions);

    }
}
