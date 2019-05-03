Sample UI Test for Android app + Monitoring solution
====================================================

Requirement
-----------
1. Java JDK
2. Maven
3. Docker

Quick Start
-----------

1. Run all needed services

	```bash
	./service.sh restart

3. Connect Database to Grafana with following parameters (only for the first time), choose type InfluxDB.

	```bash
	Name: influxdb
	URL: http://localhost:8086
	Access: Browser
	Database: demo
	User and pass: root
	```
					
4. Run test

	```bash
	BUILD_NUMBER=x mvn clean test
	```
