package symptomtracker.repos;


import symptomtracker.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends CrudRepository<Account, String> {
    List<Account> findByUsername(String username);
}
