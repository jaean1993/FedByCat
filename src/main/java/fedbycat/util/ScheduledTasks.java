package fedbycat.util;

import fedbycat.model.UserModel;
import fedbycat.service.EmailService;
import fedbycat.service.ImgService;
import fedbycat.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by anjin on 4/26/17.
 */

@Component
public class ScheduledTasks {

    private static final Logger log = Logger.getLogger(ScheduledTasks.class);

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    ImgService imgService;

    @Scheduled(fixedRate = 5000)
    public void checkLastSentTime() {
        List<UserModel> userModelList = userService.queryUserByTime(System.currentTimeMillis());
        for (UserModel um: userModelList) {
            emailService.sendEmail(um.getEmail(), imgService.getImgNumber(um));
        }
        log.info("finish check for once");
    }
}
