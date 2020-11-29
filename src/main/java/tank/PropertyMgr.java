package tank;

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

    public static Object get(String key) {
        return properties.get(key);
    }
}
