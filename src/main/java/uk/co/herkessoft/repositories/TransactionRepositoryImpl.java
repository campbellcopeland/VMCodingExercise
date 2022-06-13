package uk.co.herkessoft.repositories;

import org.springframework.stereotype.Component;
import uk.co.herkessoft.domain.Transaction;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class TransactionRepositoryImpl implements TransactionRepository{

    private final Collection<Transaction> transactions = new ArrayList<>();

    @Override
    public Collection<Transaction> findByCategory(String category) {

        Collection<Transaction> results = new ArrayList<>();
        results.addAll(this.transactions);
        results.removeIf(t -> !t.getCategory().equalsIgnoreCase(category));
        return results;
    }

    @Override
    public Collection<Transaction> findByCategoryAndYear(String category, Year year) {
        Collection<Transaction> results = findByCategory(category);
        results.removeIf(t -> t.getTransactionDate().getYear() != year.getValue());
        return results;
    }

    @Override
    public int count() {

        return this.transactions.size();
    }

    @Override
    public void saveAll(Collection<Transaction> transactions) {

        this.transactions.addAll(transactions);
    }

    @Override
    public Collection<String> getCategoryList() {

        Predicate<Transaction> predicate = distinctByKey(p -> p.getCategory());
        return transactions.stream().filter(predicate::test).map(Transaction::getCategory).collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {

        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
