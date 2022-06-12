package uk.co.herkessoft.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Transaction {

    public LocalDate transactionDate;
    public String vendor;
    public TransactionTypeEnum type;
    public BigDecimal amount;
    public String category;
}
