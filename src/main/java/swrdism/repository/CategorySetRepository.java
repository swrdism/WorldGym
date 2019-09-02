package swrdism.repository;

import org.springframework.data.repository.CrudRepository;
import swrdism.model.CategoryData;

public interface CategorySetRepository extends CrudRepository<CategoryData,Integer> {
}
