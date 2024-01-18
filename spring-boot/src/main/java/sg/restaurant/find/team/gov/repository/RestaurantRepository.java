package sg.restaurant.find.team.gov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.restaurant.find.team.gov.entity.Restaurant;
import sg.restaurant.find.team.gov.entity.Session;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findBySession(Session session);
}
