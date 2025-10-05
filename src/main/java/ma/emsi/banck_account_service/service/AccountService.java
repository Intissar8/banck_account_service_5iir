package ma.emsi.banck_account_service.service;


import ma.emsi.banck_account_service.dto.BankAccountRequestDTO;
import ma.emsi.banck_account_service.dto.BankAccountResponseDTO;

public interface AccountService  {
    public BankAccountResponseDTO addAccont(BankAccountRequestDTO banckAccountDTO);
}
