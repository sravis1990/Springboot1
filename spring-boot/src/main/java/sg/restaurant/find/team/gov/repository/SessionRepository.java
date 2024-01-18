package sg.restaurant.find.team.gov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.restaurant.find.team.gov.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
