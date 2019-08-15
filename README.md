Veriff Website Automated Check
---

Simple project created as test assignment.

### Task:
 
    1. Write automated tests for veriff.com website
    2. Tests should produce readable report
    3. Should run on Veriff's machines (execute everything in containers)
    
### Solution: 
Quick smoke test verifying that main navigation paths are opening expected pages, 
login form works as expected, site contains no broken links and page layouts are matching baseline.

Project contains following tests: 

    url (JSoup based) validates html source directly; all unique urls are extracted and checked
    visual (aShot based) - visual checks comparing website UI with existing baseline
    smoke (Webdriver based) - vanilla UI tests written using Selenide library
    
### Requirements
Minimal requirements to execute tests:
- [Docker](https://docs.docker.com/install/)
- [Docker-Compose](https://docs.docker.com/compose/install/)
- MacOS (highly recommended, project was not tested on other platforms, assume that "--network-mode=host" won't work on Windows)
    
### Quickstart

NB! First time execution will be _really_ slow.

    1. Clone repository.
    2. Run init.sh > working environment will be initialized:
        - Container with Chrome Browser (required for Selenoid)
        - Selenoid - For managing browser sessions
        - Selenoid UI - Available on: http://localhost:8080
        - Allure Reporting Service - Available on http://localhost:4040
    3. Wait until environment is fully deployed
    4. Run tests.sh > will build project and run tests
        - After test execution, open or refresh following URL 
          to view reports: http://localhost:4040

### Troubleshooting

What to do if nothing works?

    gradle clean test -> should run all non web-driver based tests (requires Java). 
    WebDriver tests are currently configured to run with Selenoid only.
    
### Project Structure

    config - browser configudarion for Selenoid
    src\main\javascript - webhook handling service implementation (code snippet only, won't work without AWS sdk)
    src\main\kotlin - test harness and utility classes
    src\main\resources - properties
    src\test\kotlin - tests
    src\test\resources - test resources (UI baseline files)
        
### Tech Stack
Automated Tests:

    - Kotlin (primary language)
    - Gradle (build automation)
    - Kotlin DSL (build script)
    - Selenide (webdriver based UI testing)
    - JSoup (html parser)
    - aShot (screenshot based UI testing)
    - Allure (test reports)
    - Docker (for environment handling)
    - JUnit5 (testing framework)
    - Selenoid (runs browsers in containers)
    - Log4j2 (logging framework)
    - Owner (configuration management)
    - TestLogger (prettyprinting tests output + marking slow tests)
    
Bonus Task (Service Implementation): 
File Location: src/main/javascript/WebhookHandler.js

    - AWS Lambda / Node.JS Runtime (event handler)
    - JavaScript (primary language)
    - Amazon API Gateway (to handle incoming connections)
    - CloudWatch Logs (logging + events tracing)
    - Amazon Simple Email Service (send notifications)




