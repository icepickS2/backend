package app.api.bbs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BbsRepository extends JpaRepository<Bbs, Long> {

  @Query(value = "select * from bbs limit 10 offset :index", nativeQuery = true)
  public List<Bbs> list(int index);
}