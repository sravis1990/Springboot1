package sg.restaurant.find.team.gov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.restaurant.find.team.gov.entity.Restaurant;
import sg.restaurant.find.team.gov.repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurants")
    public String listRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantRepository.findAll());
        return "restaurant/list";
    }

    @PostMapping("/restaurants")
    public String submitRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return "redirect:/restaurants";
    }
}
