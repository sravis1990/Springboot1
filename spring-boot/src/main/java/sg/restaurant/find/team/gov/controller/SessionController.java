package sg.restaurant.find.team.gov.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.restaurant.find.team.gov.entity.Restaurant;
import sg.restaurant.find.team.gov.entity.Session;
import sg.restaurant.find.team.gov.entity.User;
import sg.restaurant.find.team.gov.service.SessionService;
import sg.restaurant.find.team.gov.service.UserService;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/sessions")
    public String listSessions(Model model) {
        List<Session> sessions = sessionService.getAllSessions();
        model.addAttribute("sessions", sessions);
        return "sessions";
    }

    @GetMapping("/create-session")
    public String showCreateSessionForm(Model model) {
        model.addAttribute("session", new Session());
        return "create-session";
    }

    @GetMapping("/join-session/{sessionId}")
    public String showJoinSessionForm(@PathVariable Long sessionId, Model model) {
        Session session = sessionService.getSessionById(sessionId);
        model.addAttribute("session", session);
        model.addAttribute("user", new User());
        return "join-session";
    }

    @PostMapping("/create-session")
    public String createSession(@ModelAttribute("session") Session sessionForm) {
        User initiator = userService.getUserByUsername(sessionForm.getInitiator().getUsername());
        Session session = sessionService.createSession(sessionForm.getSessionName(), initiator);
        return "redirect:/sessions";
    }

    @PostMapping("/join-session/{sessionId}")
    public String joinSession(@PathVariable Long sessionId, @ModelAttribute("user") User userForm) {
        Session session = sessionService.getSessionById(sessionId);
        User participant = userService.getUserByUsername(userForm.getUsername());
        sessionService.joinSession(session, participant);
        return "redirect:/sessions";
    }

    @PostMapping("/submit-restaurant-choice/{sessionId}/{userId}")
    public String submitRestaurantChoice(@PathVariable Long sessionId, @PathVariable Long userId,
                              @ModelAttribute("restaurant") Restaurant restaurant) {
        sessionService.submitRestaurantChoice(sessionId, restaurant);
        return "redirect:/sessions";
    }

    @PostMapping("/end-session")
    public String endSession(@ModelAttribute("session") Session sessionForm) {
        Restaurant restaurant = sessionService.endSession(sessionForm.getId());
        return "redirect:/sessionEnd";
    }
}
