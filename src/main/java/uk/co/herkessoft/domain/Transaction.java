package uk.co.herkessoft.domain;

import lombok.*;

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
    public Double amount;
    public String category;
}
