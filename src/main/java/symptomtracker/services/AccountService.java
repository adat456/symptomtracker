package symptomtracker.services;

import symptomtracker.entities.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import symptomtracker.repos.AccountRepo;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepo accountRepo;

    public Account findByUsername(String username) {
        return accountRepo.findByUsername(username).get(0);
    }

    public Account createUpdate(Account a) {
        return accountRepo.save(a);
    }

    public void delete(Integer accountId) {
        accountRepo.deleteById(String.valueOf(accountId));
    }
}
