package ma.emsi.banck_account_service.repositories;

import ma.emsi.banck_account_service.entities.BankAccount;
import ma.emsi.banck_account_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


@RepositoryRestResource // used from the dependecy rest so that we do not have to create the get,post,delete in the service couche
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    @RestResource(path = "/byType")
    List<BankAccount> findByType(@Param("t") AccountType type); // with spring-data-rest we do not need to use it in the web service to be able to use it in our interface

}
