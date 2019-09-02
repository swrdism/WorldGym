package swrdism.repository;

import org.springframework.data.repository.CrudRepository;
import swrdism.model.StoreData;

public interface StoreSetRepository extends CrudRepository<StoreData,Integer> {
}
