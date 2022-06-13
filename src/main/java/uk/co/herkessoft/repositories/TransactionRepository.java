package uk.co.herkessoft.repositories;

import uk.co.herkessoft.domain.Transaction;

import java.time.Year;
import java.util.Collection;


public interface TransactionRepository {

    Collection<Transaction> findByCategory(String category);

    Collection<Transaction> findByCategoryAndYear(String category, Year year);

    int count();

    void saveAll(Collection<Transaction> transactions);

    Collection<String> getCategoryList();

}
