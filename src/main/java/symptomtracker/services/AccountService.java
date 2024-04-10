package symptomtracker.services;

import jakarta.persistence.EntityNotFoundException;
import symptomtracker.entities.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import symptomtracker.repos.AccountRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepo accountRepo;

    public Account findByUsername(String username) {
        List<Account> matchingAccount = accountRepo.findByUsername(username);
        if (matchingAccount.isEmpty()) {
            throw new EntityNotFoundException("Unable to find account.");
        } else {
            return accountRepo.findByUsername(username).get(0);
        }
    }

    public Account create(Account a) {
        return accountRepo.save(a);
    }

    public Account update(Account a) {
        Account account = accountRepo.findById(String.valueOf(a.getAccountId())).orElseThrow(() -> new EntityNotFoundException("Unable to find account."));
        account.setUsername(a.getUsername());
        account.setPassword(a.getPassword());
        account.setEmail(a.getEmail());
        return accountRepo.save(account);
    }

    public void delete(Integer accountId) {
        accountRepo.deleteById(String.valueOf(accountId));
    }
}
