package ma.emsi.banck_account_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.banck_account_service.enums.AccountType;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountRequestDTO {
    /*private String id;
    private Date createdAt;  we do not need neither in dto request*/
    private Double balance; // Double instead of double so that the value by default is Null not 0
    private String currency;
    private AccountType type;
}
