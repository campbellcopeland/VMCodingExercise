package uk.co.herkessoft.web.mappers;

import org.mapstruct.Mapper;
import uk.co.herkessoft.domain.Transaction;
import uk.co.herkessoft.web.model.TransactionDto;

@Mapper
public interface TransactionMapper {

    TransactionDto transactionToTransactionDto(Transaction transaction);

    Transaction TransactionDtoToTransaction(TransactionDto dto);
}
