package swrdism.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import swrdism.model.TeacherData;

public interface TeacherDataRepository extends CrudRepository<TeacherData,Integer> {

    @Transactional
    @Modifying
    @Query(value ="Truncate teacher_data",nativeQuery = true)
    void truncate();
}
