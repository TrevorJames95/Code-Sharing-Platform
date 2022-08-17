package platform.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import platform.model.Code;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CodeRepository extends JpaRepository<Code, UUID> {
    Optional<Code> findById(UUID n);

    ArrayList<Code> findTop10ByOrderByCreationDateDesc();

    @Query(value = "SELECT * FROM Code c WHERE c.views = 0 AND c.time = 0 ORDER BY c.creation DESC LIMIT 10",
            nativeQuery = true)
    List<Code> findLatestSnippets();
}
