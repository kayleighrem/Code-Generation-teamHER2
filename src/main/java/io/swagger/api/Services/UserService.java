package io.swagger.api.Services;

import io.swagger.api.Repositories.UserRepository;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void createUser(User user)
    {
        userRepository.save(user);
    }
    public List<User> getUser()
    {
        return (List<User>) userRepository.findAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUserAccount(User user) throws SendFailedException {
        if (CheckIfEmailExists(user.getEmail())) {
            throw new SendFailedException(
                    "There is an account with that email adress:" + user.getEmail());
        }
        User newuser = new User();
        newuser.setName(user.getName());
        newuser.setLastname(user.getLastname());

        newuser.setPassword(passwordEncoder.encode(user.getPassword()));

        newuser.setEmail(user.getEmail());
        newuser.setIsEmployee(false);
        return userRepository.save(newuser);
    }

    public boolean CheckIfEmailExists(String email) {
        for ( User u : getUser()) {
            if (email.equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean CheckInlog(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for ( User u : getUser()) {
            if (user.getEmail().equals(u.getEmail()) && encoder.matches(user.getPassword(), u.getPassword()) == true) {
                return true;
            }
        }
        return false;
    }
}
