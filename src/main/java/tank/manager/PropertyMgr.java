package tank.manager;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("tank.config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return String.valueOf(properties.get(key));
    }
}
