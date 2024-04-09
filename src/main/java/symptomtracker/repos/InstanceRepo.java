package symptomtracker.repos;

import symptomtracker.entities.Instance;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepo extends PagingAndSortingRepository<Instance, Integer> {
}
