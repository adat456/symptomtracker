package symptomtracker.repos;

import symptomtracker.entities.Symptom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepo extends CrudRepository<Symptom, Integer> {
}
