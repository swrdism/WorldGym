package swrdism.repository;

import org.springframework.data.repository.CrudRepository;
import swrdism.model.TeacherData;

public interface TeacherSetRepository extends CrudRepository<TeacherData,Integer> {
}
