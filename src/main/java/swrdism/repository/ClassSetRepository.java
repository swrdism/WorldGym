package swrdism.repository;

import org.springframework.data.repository.CrudRepository;
import swrdism.model.ClassNameData;

public interface ClassSetRepository extends CrudRepository<ClassNameData,Integer> {
}
