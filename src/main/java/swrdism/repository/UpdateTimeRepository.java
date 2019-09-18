package swrdism.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import swrdism.model.UpdateTime;

public interface UpdateTimeRepository extends CrudRepository<UpdateTime, Integer> {

    @Transactional
    @Modifying
    @Query(value ="Truncate update_time",nativeQuery = true)
    void truncate();
}
