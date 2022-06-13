package uk.co.herkessoft.repositories;

import org.springframework.stereotype.Component;
import uk.co.herkessoft.domain.Transaction;

import java.util.ArrayList;
import java.util.Collection;

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
    public int count() {
        return this.transactions.size();
    }

    @Override
    public void saveAll(Collection<Transaction> transactions) {
        this.transactions.addAll(transactions);
    }
}
