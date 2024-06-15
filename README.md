                                    *** Scalable Framework for UI Automation ***
![alt text](https://github.com/nyevhen/endpoint-challenge/blob/main/Screenshot%202024-06-15%20at%201.52.30%E2%80%AFPM.png)

# What you can do with it



## Requirements
To code, build, and run tests in this framework locally you'l need:
- java 17 (or latest)
- maven
- cucumber for java plugin
- gherkin plugin
- lombok plugin
- chromedriver
- internet access
- maybe cup of coffee

## Installation
To download latest and greatest source of this framework run:

```git clone https://github.com/nyevhen/endpoint-challenge.git```

## Configuration
To run tests on your side you'll need to create "drivers" folder in the root of project and leave chromedriver inside.
Chromedriver compatible with you chrome browser version can be downloaded here:
https://googlechromelabs.github.io/chrome-for-testing/


## Test execution
To run all of UI test you can go to the root of the repo and run:

```mvn clean install -Dtest="RunUITest"```

Basic html report will be saved to:
${project.build.directory}/cucumber-reports/

