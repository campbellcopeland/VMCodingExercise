package uk.co.herkessoft.web.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class OutgoingDto {

    public String category;

    @NotNull
    public Double totalAmount;
}
