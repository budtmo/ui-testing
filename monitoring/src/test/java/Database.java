import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

public class Database {
    private static final String URL = "http://localhost:8086";
    private static final String NAME = "demo";
    private static final InfluxDB db = InfluxDBFactory.connect(URL,"root", "root");

    static {
        db.setDatabase(NAME);
    }

    public static void save(final Point point){
        db.write(point);
    }
}
