package sg.restaurant.find.team.gov.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.restaurant.find.team.gov.entity.Restaurant;
import sg.restaurant.find.team.gov.entity.Session;
import sg.restaurant.find.team.gov.entity.User;
import sg.restaurant.find.team.gov.repository.SessionRepository;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public Session createSession(String sessionName, User initiator) {
        Session session = new Session();
        session.setSessionName(sessionName);
        session.setInitiator(initiator);
        session.getParticipants().add(initiator);
        return sessionRepository.save(session);
    }

    public Session joinSession(Session session, User participant) {
        if (session.isEnded()) {
            return session;
        } else {
            session.getParticipants().add(participant);
            return sessionRepository.save(session);
        }
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(long sessionId) {
        Session session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new NoSuchElementException("Session not found"));
        return session;
    }

    public Session submitRestaurantChoice(Long sessionId, Restaurant restaurant) {
        Session session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new NoSuchElementException("Session not found"));
        if (session.isEnded()) {
            return session;
        } else {
            session.getRestaurants().add(restaurant);
            return sessionRepository.save(session);
        }
    }

    public Restaurant endSession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new NoSuchElementException("Session not found"));

        List allRestaurants = session.getRestaurants();

        session.setEnded(true);
        sessionRepository.save(session);

        Random random = new Random();
        int randomIndex = random.nextInt(allRestaurants.size());

        return (Restaurant) allRestaurants.get(randomIndex);
    }
}
