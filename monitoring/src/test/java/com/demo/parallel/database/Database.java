package com.demo.parallel.database;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

public class Database {
    private static final String URL = "http://localhost:8086";
    private static final String NAME = "demo";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final InfluxDB db = InfluxDBFactory.connect(URL,USER, PASS);

    static {
        db.setDatabase(NAME);
    }

    public static void save(final Point content){
        db.write(content);
    }
}
