Sample Mobile Website UI Test
=============================

Requirement
-----------
1. Java JDK
2. Maven
3. Appium server
4. Android emulator

Quick Start
-----------

```bash
mvn test
```

Notes
-----

If you get error message "Original error: unknown error: Chrome version must be >= xx.x",
then you need to download chrome driver from [here](https://chromedriver.storage.googleapis.com/index.html) and run appium with following command:

```bash
appium appium --chromedriver-executable <path_of_chromedriver>
```
