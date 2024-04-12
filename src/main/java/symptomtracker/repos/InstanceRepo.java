package symptomtracker.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import symptomtracker.entities.Instance;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceRepo extends CrudRepository<Instance, Integer>, PagingAndSortingRepository<Instance, Integer> {
    Slice<Instance> findAllByFeatureId(Integer featureId, Pageable pageable);

    @Query(value = "DELETE * FROM Instances WHERE featureId = ?1", nativeQuery = true)
    void deleteByFeature(Integer featureId);
}
