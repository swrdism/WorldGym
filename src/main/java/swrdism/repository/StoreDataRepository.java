package swrdism.repository;

import org.springframework.data.repository.CrudRepository;
import swrdism.model.StoreData;

public interface StoreDataRepository extends CrudRepository<StoreData,Integer> {
}
