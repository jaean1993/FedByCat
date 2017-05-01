package fedbycat.service.impl;

import fedbycat.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by anjin on 4/30/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailServiceImplTest {

    private EmailServiceImpl emailService = new EmailServiceImpl();

    @Test
    public void emailSendTest() {
        emailService.sendEmail("282821521@qq.com", 1);
    }
}
