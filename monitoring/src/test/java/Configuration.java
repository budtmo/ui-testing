import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class Configuration {
    private static Properties prop;

    public static void load() {
        prop = new Properties();

        try {
            String configName = "config.properties";

            // Check if the file exits
            ClassLoader cL = Configuration.class.getClassLoader();
            URL res = Objects.requireNonNull(cL.getResource(configName));

            // Load
            prop.load(new FileInputStream(res.getFile()));

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return prop;
    }
}
