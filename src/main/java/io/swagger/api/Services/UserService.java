package io.swagger.api.Services;

import io.swagger.api.Repositories.UserRepository;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void createUser(User user) { userRepository.save(user); }
    public List<User> getUsers() { return (List<User>) userRepository.findAll(); }
    public List<User> getEmployees() { return (List<User>) findEmployees(); }
    public List<User> getClients() { return (List<User>) findClients(); }

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
        if (user.getIsEmployee() == true)
            newuser.setIsEmployee(true);
        if (user.getIsEmployee() == null | user.getIsEmployee() == false)
            newuser.setIsEmployee(false);
        return userRepository.save(newuser);
    }

    public boolean CheckIfEmailExists(String email) {
        for ( User u : getUsers()) {
            if (email.equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public User CheckInlog(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for ( User u : getUsers()) {
            if (user.getEmail().equals(u.getEmail()) && encoder.matches(user.getPassword(), u.getPassword()) == true) {

                return u;
            }
        }
        return null;
    }

    public User getUserByLogin(User user)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for(User u : getUsers())
        {
            if(u.getEmail().equals(user.getEmail()) && encoder.matches(user.getPassword(),u.getPassword()))
            {
                return u;
            }
        }
        return new User();
    }


    public List<User> findEmployees() {
        List<User> employees = new ArrayList<User>();
        for (User user : userRepository.findAll()) {
            if(user.getIsEmployee() == true)
                employees.add(user);
        }
        return employees;
    }

    public List<User> findClients() {
        List<User> clients = new ArrayList<User>();
        for (User user : userRepository.findAll()) {
            if(user.getIsEmployee() == false)
                clients.add(user);
        }
        return clients;
    }

    public void deleteUser(int userId) {
        ArrayList<User> allusers = (ArrayList<User>) userRepository.findAll();
        User us = allusers.stream().filter(a -> a.getUserId() == (int)userId).collect(Collectors.toList()).get(0);
        userRepository.delete(us);
    }

    public void updateUser(int userId) {
        ArrayList<User> allusers = (ArrayList<User>) userRepository.findAll();
        User us = allusers.stream().filter(a -> a.getUserId() == (int)userId).collect(Collectors.toList()).get(0);
//        userRepository.(us);
    }
}
