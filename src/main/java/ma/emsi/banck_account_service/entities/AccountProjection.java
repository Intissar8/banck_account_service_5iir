package ma.emsi.banck_account_service.entities;

import ma.emsi.banck_account_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class, name = "p1") // we use this from spring-data-rest just like dto to specify wich attributes to use in the interface in thus case its id and type
public interface AccountProjection {
    public String getId();
    public AccountType getType();
    public Double getBalance();
}
