package swrdism.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import swrdism.model.ClassData;

public interface ClassDataRepository extends CrudRepository<ClassData,Integer> {
    @Transactional
    @Modifying
    @Query(value ="Truncate class_data",nativeQuery = true)
    void truncate();
}
