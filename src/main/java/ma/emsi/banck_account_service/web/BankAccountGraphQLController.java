package ma.emsi.banck_account_service.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.banck_account_service.dto.BankAccountRequestDTO;
import ma.emsi.banck_account_service.dto.BankAccountResponseDTO;
import ma.emsi.banck_account_service.entities.BankAccount;
import ma.emsi.banck_account_service.entities.Customer;
import ma.emsi.banck_account_service.repositories.BankAccountRepository;
import ma.emsi.banck_account_service.repositories.CustomerRepository;
import ma.emsi.banck_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

// we use Graphql because it is more optimised than rest as in it lets us choose what we want to print with no problem therefore we won't have an infinite loop
@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping // when the client demands an accountsList that is in graphql, it will execute this method that is with same name
    public List<BankAccount> accountsList()
    {
        return bankAccountRepository.findAll();
    }
    @QueryMapping // when the client demands an accountsList that is in graphql, it will execute this method that is with same name
    public BankAccount bankAccountById(@Argument String id)
    {
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Bank Account %s not found",id)));
    }

    @MutationMapping // used for modification operations
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccont(bankAccount);
    }

    @MutationMapping // used for modification operations
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }

    @MutationMapping // used for modification operations
    public Boolean deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
        return true;
    }

    @QueryMapping // when the client demands an accountsList that is in graphql, it will execute this method that is with same name
    public List<Customer> customers()
    {
        return customerRepository.findAll();
    }



    //an immutable object that lets you choose what to create using a constructor
    //record BankAccountDTO(Double balance,String type,String currency){}
}
