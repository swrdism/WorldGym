package swrdism.repository;

import org.springframework.data.repository.CrudRepository;
import swrdism.model.UpdateTime;

public interface UpdateTimeRepository extends CrudRepository<UpdateTime, Integer> {
}
