package ma.emsi.banck_account_service.web;
import ma.emsi.banck_account_service.dto.BankAccountRequestDTO;
import ma.emsi.banck_account_service.dto.BankAccountResponseDTO;
import ma.emsi.banck_account_service.entities.BankAccount;
import ma.emsi.banck_account_service.mappers.AccountMapper;
import ma.emsi.banck_account_service.repositories.BankAccountRepository;
import ma.emsi.banck_account_service.service.AccountService;
import ma.emsi.banck_account_service.service.AccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api") // so that we can test if the data-rest dependency that manages all this, is working
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService,AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/bankAccounts") //the name of the url should be the same name of the entitity+s
    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount BankAccount(@PathVariable  String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format(" Account %s not found",id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {

        return accountService.addAccont(requestDTO);

    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable  String id,@RequestBody BankAccount bankAccount) {
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!= null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt()!= null) account.setCreatedAt(bankAccount.getCreatedAt());
        if(bankAccount.getType()!= null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!= null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable  String id) {
         bankAccountRepository.deleteById(id);
    }
}
