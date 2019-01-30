Sample UI Test for Android app + Monitoring solution
====================================================

Requirement
-----------
1. Java JDK
2. Maven
3. Docker

Quick Start
-----------

1. Run all needed docker services

	```bash
	docker-compose up -d
	```

2. Create Database (only for the first time)

	```bash
	curl -i -XPOST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE demo"
	```

3. Connect Database to Grafana with following parameters (only for the first time)

	```bash
	Name: influxdb
	Type: influxDB
	URL: http://localhost:8086
	Access: direct
	Database: demo
	User and pass: root
	```
					
4. Run test

	```bash
	mvn test
	```
