package fedbycat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** User Model List
 *
 * Created by anjin on 4/23/17.
 *
 */
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * user id
     */
    private long userId;

    /**
     * user name
     */
    private String userName;

    /**
     * user password
     */
    private String password;

    /**
     * user gender
     */
    private int gender;

    /**
     * user email
     */
    private String email;

    /**
     * last time to receive pictures
     */
    private long gmtSend;

    /**
     * the number of pictures people received
     */
    private String receiveHistory;

    /**
     * time when user was created
     */
    private long gmtCreate;

    /**
     * if the account is active now
     */
    private boolean active;

    public UserModel(String userName, String password, int gender, String email, long gmtSend, String receiveHistory, long gmtCreate, boolean active) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.gmtSend = gmtSend;
        this.receiveHistory = receiveHistory;
        this.gmtCreate = gmtCreate;
        this.active = active;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getReceiveHistory() {
        return receiveHistory;
    }

    public void setReceiveHistory(String receiveHistory) {
        this.receiveHistory = receiveHistory;
    }

    public long getGmtSend() {
        return gmtSend;
    }

    public void setGmtSend(long gmtSend) {
        this.gmtSend = gmtSend;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
