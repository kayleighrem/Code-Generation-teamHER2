package io.swagger.api.Services;

import io.swagger.api.Repositories.UserRepository;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
