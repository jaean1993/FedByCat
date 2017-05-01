package fedbycat.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by anjin on 4/26/17.
 */
public class FedByCatParamResult {
    public static String getConfigValue(String param, String fileName) {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("src/main/resources/" + fileName + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p.getProperty(param);
    }
}
