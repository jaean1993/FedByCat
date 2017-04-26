package fedbycat.service.impl;

import fedbycat.model.UserModel;
import fedbycat.repository.UserRepository;
import fedbycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anjin on 4/25/17.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public boolean putUser(UserModel userModel) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(userModel.getPassword().getBytes());
            userModel.setPassword(new String(md.digest(), StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        Object result = userRepository.save(userModel);
        return result instanceof UserModel;
    }

    public List<UserModel> queryUserByTime(long timeStamp) {
        List<UserModel> result = new LinkedList<>();
        return result;
    }
}
