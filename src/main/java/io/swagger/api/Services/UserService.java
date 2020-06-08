package io.swagger.api.Services;

import io.swagger.api.Repositories.UserRepository;
import io.swagger.model.Users;
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


    public void createUser(Users user)
    {
        userRepository.save(user);
    }

    public List<Users> getUsers()
    {
        return (List<Users>) userRepository.findAll();
    }
}
