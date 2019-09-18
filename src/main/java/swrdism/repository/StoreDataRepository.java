package swrdism.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import swrdism.model.StoreData;

public interface StoreDataRepository extends CrudRepository<StoreData,Integer> {
    @Transactional
    @Modifying
    @Query(value ="Truncate store_data",nativeQuery = true)
    void truncate();
}
