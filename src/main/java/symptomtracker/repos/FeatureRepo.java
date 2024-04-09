package symptomtracker.repos;

import symptomtracker.entities.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepo extends CrudRepository<Feature, Integer> {
}
