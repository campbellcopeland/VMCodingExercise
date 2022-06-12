package uk.co.herkessoft.web.model;

import lombok.*;
import uk.co.herkessoft.domain.TransactionTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TransactionDto {
    @NotNull
    public LocalDate transactionDate;

    @NotBlank
    public String vendor;

    @NotNull
    public TransactionTypeEnum type;

    @NotNull
    public Double amount;

    @NotBlank
    public String category;
}
