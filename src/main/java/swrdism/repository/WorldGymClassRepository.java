package swrdism.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import swrdism.model.WorldGymClass;

public interface WorldGymClassRepository extends CrudRepository<WorldGymClass,Integer> {
    @Transactional
    @Modifying
    @Query(value ="Truncate world_gym_class",nativeQuery = true)
    void truncate();
}
