import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

public class Database {
    private static final String URL = Configuration.getProperties().getProperty("db.url");
    private static final String NAME = Configuration.getProperties().getProperty("db.name");
    private static final String USER = Configuration.getProperties().getProperty("db.user");
    private static final String PASS = Configuration.getProperties().getProperty("db.pass");
    private static final InfluxDB db = InfluxDBFactory.connect(URL,USER, PASS);

    static {
        db.setDatabase(NAME);
    }

    public static void save(final Point content){
        db.write(content);
    }
}
