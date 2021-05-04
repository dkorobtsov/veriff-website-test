Veriff Website Automated Check
---

**NB: Does not make sense to run tests, those were targeting old version of website.**
---

Simple project created as test assignment.

### Task:
 
    1. Write automated tests for veriff.com website
    2. Tests should produce readable report
    3. Should run on Veriff's machines (execute everything in containers)
    
### Solution: 
Quick smoke test verifying that main navigation paths are opening expected pages, 
login form works as expected, site contains no broken links and page layouts are matching baseline.

#### What is covered?
- main navigation path
- support / login / forgot password page smoke test
- all unique links are checked
- visual check of all pages (to find any diffs with baseline)

#### What is not covered?
- positive flows (user can login / user can recover password etc)
- page rendering on different screen sizes
(can be added pretty fast, but is out of scope for given assignment) 

Project contains following tests: 

    smoke (Webdriver based) - vanilla UI tests written using Selenide library
    url (JSoup based) validates html source directly; all unique urls are extracted and checked
    visual (aShot based) - visual checks comparing website UI with existing baseline
    
NB. One visual test (form main page) should fail for demo purposes - I removed button 
and some header from baseline image (test is expected to detect inconsistency). Note that there could be also some random 
failures if page render will be different from baseline. Exclusions for some parts of UI (like instagram feed) are not configured. 
All discovered differences will be marked with __RED__ highlights on screenshots attached to Allure test reports.

 
    
### Requirements
Minimal requirements to execute tests:
- [Docker](https://docs.docker.com/install/)
- [Docker-Compose](https://docs.docker.com/compose/install/)
- MacOS (highly recommended, project was not tested on other platforms, assume that "--network-mode=host" won't work on Windows)
    
### Quickstart

    1. Clone repository.
    2. Open project directory in terminal
    3. Run init.sh > working environment will be initialized:
        - Container with Chrome Browser (required for Selenoid)
        - Selenoid - For managing browser sessions
        - Selenoid UI - Available on: http://localhost:8080
        - Allure Reporting Service
    4. Wait until environment is fully deployed
        - Allure Reports will be available on: http://localhost:4040
        - Allure API will be available on: http://localhost:5050
        - Selenoid UI will be available on: http://localhost:8080
        (allows to track browser sessions, open VNC to container etc)
    5. Run tests.sh > will build project and run tests
        (my recommendation is to run it in a new terminal window)
        - After test execution, open or refresh following URL 
          to view reports on server: http://localhost:4040
          to get emailable report: http://localhost:5050/emailable-report/render
          (reports should be generated automatically based 
          on allure-results folder contents)
          
NB! First time execution will be __VERY__ slow. Approximate timings for a first run:

    init.sh - about 10 minutes to rollout full environment 
    tests.sh - about 5 minutes to fetch all dependencies and build project
    
### Troubleshooting

What to do if nothing works?

    gradle clean test -> should run all non web-driver based tests (requires Java). 
    WebDriver tests are currently configured to run with Selenoid only.
    
### Project Structure

    config - browser configuration for Selenoid
    src\main\javascript - webhook handling service implementation 
    (code snippet only, won't work without AWS sdk)
    src\main\kotlin - test harness and utility classes
    src\main\resources - properties
    src\test\kotlin - tests
    src\test\resources - test resources (UI baseline files)
        
### Tech Stack
Automated Tests:

    - Kotlin (primary language)
    - Gradle (build automation)
    - Kotlin DSL (build script)
    - Detekt (Code quality control)
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




