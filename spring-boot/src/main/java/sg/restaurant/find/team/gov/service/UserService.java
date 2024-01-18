package sg.restaurant.find.team.gov.service;

import org.springframework.beans.factory.annotation.Autowired;
import sg.restaurant.find.team.gov.entity.User;
import sg.restaurant.find.team.gov.repository.UserRepository;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
