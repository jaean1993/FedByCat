package fedbycat.service.impl;

import fedbycat.model.UserModel;
import fedbycat.service.ImgService;
import org.springframework.stereotype.Service;

/**
 * Created by anjin on 4/30/17.
 */
@Service
public class ImgServiceImpl implements ImgService{

    @Override
    public int getImgNumber(UserModel userModel) {
        return 1;
    }
}
