package ma.emsi.banck_account_service.service;

import ma.emsi.banck_account_service.dto.BankAccountRequestDTO;
import ma.emsi.banck_account_service.dto.BankAccountResponseDTO;
import ma.emsi.banck_account_service.entities.BankAccount;
import ma.emsi.banck_account_service.mappers.AccountMapper;
import ma.emsi.banck_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccont(BankAccountRequestDTO banckAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .type(banckAccountDTO.getType())
                .balance(banckAccountDTO.getBalance())
                .createdAt(new Date())
                .currency(banckAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO banckAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(id)
                .type(banckAccountDTO.getType())
                .balance(banckAccountDTO.getBalance())
                .createdAt(new Date())
                .currency(banckAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
