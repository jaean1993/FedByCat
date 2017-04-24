package fedbycat.service;

import fedbycat.model.UserModel;

import java.util.List;

/**
 * User Service Interface
 *
 * Created by anjin on 4/23/17.
 */
public interface UserService {

    /**
     * create or update account
     *
     * @title putUser
     * @param userModel
     * @return boolean
     */
    boolean putUser(UserModel userModel);

    /**
     * query user by time
     *
     * @title queryUserByTime
     * @param timeStamp
     * @return List<UserModel>
     */
    List<UserModel> queryUserByTime(long timeStamp);
}
