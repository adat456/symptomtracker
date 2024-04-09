package symptomtracker.repos;

import org.springframework.data.jpa.repository.Query;
import symptomtracker.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Integer> {
//   @Query("SELECT a.categories FROM Account a WHERE a.accountId = ?1")
    @Query("SELECT c FROM Category c JOIN c.account a WHERE a.accountId = ?1")
    List<Category> findByAccountId(Integer accountId);
}
