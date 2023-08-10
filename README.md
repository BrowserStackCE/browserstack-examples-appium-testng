![Logo](https://www.browserstack.com/images/static/header-logo.jpg)

# BrowserStack Examples Appium TestNG

## Introduction

TestNG is a testing framework inspired from JUnit and NUnit but introducing some new functionalities that make it more powerful.

This BrowserStack Example repository demonstrates an Appium test framework written in TestNG with parallel testing capabilities. The Appium test scripts are written for the open source [todo](todo). This BrowserStack Demo Mobile App is an e-commerce mobile application which showcases multiple real-world user scenarios. The app is bundled with offers data, orders data and products data that contains everything you need to start using the app and run tests out-of-the-box.

The Appium tests are run on different platforms like on-prem and BrowserStack using various run configurations and test capabilities.

---

## Repository setup

- Clone the repository

- Ensure you have the following dependencies installed on the machine
   - Java >= 8
   - Maven >= 3.1+

  Maven:
    ```sh
     mvn install -DskipTests
    ```

  Gradle:

    ```sh
     gradle clean build
    ```


## About the tests in this repository

This repository contains the following Selenium tests:

| Module   | Test name                          | Description |
  | ---   | ---                                   | --- |
| E2E      | OrderTest                       | This test scenario verifies successful product purchase lifecycle end-to-end. It demonstrates the [Page Object Model design pattern](https://www.browserstack.com/guide/page-object-model-in-selenium) and is also the default test executed in all the single test run profiles. |
| Login    | LoginTest                       | This test verifies the login workflow with different types of valid login users. |
| Login    | LoginFailTest                   | This test verifies the login workflow error. |
| Login    | LoginDataDrivenTest             | This test verifies the login for all error cases in a datadriven way |
| Login    | LoginDataDrivenReadFromCSVTest  | This test verifies the login for all error cases in a datadriven way with CSV-file  |
| Login    | LoginRequestedTest              | This test verifies that the login page is shown when you access the favourites page with being logged in  |
| Offers   | OfferTest                       | This test mocks the GPS location for Singapore and verifies that the product offers applicable for the Singapore location are shown.   |
| User     | UserTest                        | The first test verifies that existing orders are shown for user: "existing_orders_user". The second test verifies if a user can add product to the favourites. |
  
---


## Test infrastructure environments

- [On-premise/self-hosted](#on-premise-self-hosted)
- [BrowserStack](#browserstack)


## Test Reporting

- [Allure reports](#generating-allure-reports)

---

# On Premise / Self Hosted

This infrastructure points to running the tests on your own machine using simulator or connected devices.

## Prerequisites

- For this infrastructure configuration (i.e on-premise), ensure that the app is downloaded and placed in the `/src/test/resources/app` folder.
<todo add download urls>

## Running Your Tests

### Run a specific test on your own machine

- How to run the test?

  To run the default test scenario (e.g. End to End Scenario) on your own machine, use the following command:

  Maven:
  ```sh
  mvn clean test -P on-prem
  ```

  Gradle:
  ```sh
  gradle clean on-prem
  ```

  To run a specific test scenario, use the following command with the additional 'test' argument:

  Maven:
  ```sh
  mvn clean test -P on-prem -Dtest=LoginDataDrivenTest

  ```

  Gradle:
  ```sh
  gradle clean on-prem -Ptest-name=LoginDataDrivenTest
  ```

  where, the argument `test` or `test-name` can be any testclass implemented this repository.

- Output

  This run profile executes a specific test scenario on a single device instance on your own machine.


### Run the entire test suite on your own machine

- How to run the test?

  To run the entire test suite on your own machine, use the following command:

  Maven:
  ```sh
  mvn clean test -P on-prem-suite
  ```

  Gradle:
  ```sh
  gradle clean on-prem-suite
  ```

- Output

  This run profile executes the entire test suite sequentially on a single device, on your own machine.

---

# BrowserStack

[BrowserStack](https://browserstack.com) provides instant access to 2,000+ real mobile devices and browsers on a highly reliable cloud infrastructure that effortlessly scales as testing needs grow.

## Prerequisites

- Create a new [BrowserStack account](https://www.browserstack.com/users/sign_up) or use an existing one.
- Identify your BrowserStack username and access key from the [BrowserStack App Automate Dashboard](https://app-automate.browserstack.com/) and export them as environment variables using the below commands.

   - For \*nix based and Mac machines:

  ```sh
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

   - For Windows:

  ```shell
  set BROWSERSTACK_USERNAME=<browserstack-username>
  set BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

  Alternatively, you can also hardcode username and access_key objects in the [browserstack.yml](resources/conf/capabilities/browserstack-single.yml) file.

Note:
- We have configured a list of test capabilities in the [browserstack.yml](resources/conf/capabilities/browserstack-single.yml) file. You can certainly update them based on your device / browser test requirements.
- The exact test capability values can be easily identified using the [Browserstack Capability Generator](https://browserstack.com/automate/capabilities)


## Running Your Tests on BrowserStack

### Prerequisites

You need to upload the `APK` or `IPA` to BrowserStack, before you can run the test on BrowserStack. BrowserStack will provide you with an `app_url` which you need to use.

You can upload the `APK` or `IPA` using a file on your filesystem or using a public url.

cURL command: 
``` shell
curl -u "browserstack_username:browserstack_access_key" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/path/to/ipa/or/apk"
-F "custom_id=BrowserStackDemoApp"
```

More information on [Upload apps from filesystem](https://www.browserstack.com/docs/app-automate/appium/upload-app-from-filesystem), [Upload apps using public URL](https://www.browserstack.com/docs/app-automate/appium/upload-app-using-public-url) or [Define custom ID for app](https://www.browserstack.com/docs/app-automate/appium/upload-app-define-custom-id).

**OR**

With Browserstack SDK, all the effort is taken away from the user.

All you need to do is add specify the path of your `APK` or `IPA` file in your browserstack.yml as shown below:
```
app: ./path/to/my/demo/app.apk
```

If your app does indeed exist in the path provided, the sdk will be able to identify it, upload it, and use it for your tests.

### Run a specific test on BrowserStack

In this section, we will run a single test on an Android device on Browserstack. To change test capabilities for this configuration, please refer to the `browserstack-single.yml` file in resources/conf/capabilities/

- How to run the test?

  - Set Environment Variable to pick desired YML file.

    - For \*nix based and Mac machines:

    ```sh
    
    export BROWSERSTACK_CONFIG_FILE="src/test/resources/conf/capabilities/browserstack-single.yml"
    ```

    - For Windows:

    ```sh
    set BROWSERSTACK_CONFIG_FILE="src\test\resources\conf\capabilities\browserstack-single.yml"
    ```

   - To run the default test scenario (e.g. End to End Scenario) on a BrowserStack device, use the following command:

  Maven:
  ```sh
  mvn clean test -P bstack-single
  ```

  Gradle:
    ```sh
  gradle clean bstack-single
  ```

  To run a specific test scenario, use the following command with the additional 'test-name' argument:
  Maven:
  ```sh
  mvn clean test -P bstack-single -Dtest=LoginDataDrivenTest
  ```

  Gradle:
  ```sh
  gradle clean bstack-single -Ptest-name=LoginDataDrivenTest
  ```

  where, the argument `test` or `test-name` can be any testclass implemented in this repository.


- Output

  This run profile executes a single test on a single device on BrowserStack. Please refer to your [BrowserStack App Automate Dashboard](https://app-automate.browserstack.com/) for test results.


### Run the entire test suite in parallel on a single BrowserStack device

In this section, we will run the tests in parallel on a single device on Browserstack. To change test capabilities for this configuration, please refer to the `browserstack-parallel.yml` file in resources/conf/capabilities/

- How to run the test?

  - Set Environment Variable to pick desired YML file:

    - For \*nix based and Mac machines:

    ```sh
    export BROWSERSTACK_CONFIG_FILE="src/test/resources/conf/capabilities/browserstack-parallel.yml"
    ```

    - For Windows:

    ```sh
    set BROWSERSTACK_CONFIG_FILE="src\test\resources\conf\capabilities\browserstack-parallel.yml"
    ```

  To run the entire test suite in parallel on a single BrowserStack device, use the following command:

  Maven:
  ```sh
  mvn clean test -P bstack-parallel
  ```
  Gradle:
    ```sh
  gradle clean bstack-parallel
  ```


- Output

  This run profile executes the entire test suite in parallel on a single BrowserStack device. Please refer to your [BrowserStack App Automate Dashboard](https://app-automate.browserstack.com/) for test results.

   - Note: By default, this execution would run maximum 5 test threads in parallel on BrowserStack. Refer to the section ["Configuring the maximum parallel test threads for this repository"](#Configuring-the-maximum-parallel-test-threads-for-this-repository) for updating the parallel thread count based on your requirements.


### Run the entire test suite in parallel on multiple BrowserStack devices

In this section, we will run the tests in parallel on multiple devices on Browserstack. To change test capabilities for this configuration, please refer to the `browserstack-parallel-devices.yml` file in resources/conf/capabilities/

- How to run the test?

  - Set Environment Variable to pick desired YML file:

    - For \*nix based and Mac machines:

    ```sh
    export BROWSERSTACK_CONFIG_FILE="src/test/resources/conf/capabilities/browserstack-parallel-devices.yml"
    ```

    - For Windows:

    ```sh
    set BROWSERSTACK_CONFIG_FILE="src\test\resources\conf\capabilities\browserstack-parallel-devices.yml"
    ```


  To run the entire test suite in parallel on multiple BrowserStack devices, use the following command:

  Maven:
  ```sh
  mvn clean test -P bstack-parallel-devices
  ```

  Gradle:
  ```sh
  gradle clean bstack-parallel-devices
  ```

### [Mobile application using local or internal environment] Running your tests on BrowserStack using BrowserStackLocal

#### Prerequisites

- Clone the [BrowserStack demo application](https://github.com/browserstack/browserstack-demo-app) repository.
  ```sh
  git clone https://github.com/browserstack/browserstack-demo-app
  ``` 
- Please follow the README.md on the BrowserStack demo application repository to install and start the dev server on localhost.
- We will change the response of the `signin` (for the `locked_user`) API endpoint. (File to change: `pages/api/signin.js` line `43`)
  - The API endpoint respond with a specific error, `Your account has been locked.`.
  - We will change that to something generic, like: `Something went wrong.`
- In this section, we will run a single test case that changes the API used in BrowserStack Demo app, in a wat that it interact with you local machine. Refer to the `browserstack-local.yml` file to change test capabilities for this configuration.
- Note: You may need to provide additional BrowserStackLocal arguments to successfully connect your localhost environment with BrowserStack infrastructure. (e.g if you are behind firewalls, proxy or VPN).
- Further details for successfully creating a BrowserStackLocal connection can be found here:

   - [Local Testing with App Automate](https://www.browserstack.com/local-testing/app-automate)
   - [BrowserStackLocal Java GitHub](https://github.com/browserstack/browserstack-local-java)


### [Mobile application using local or internal environment] Run a specific test on BrowserStack using BrowserStackLocal

- How to run the test?

  - Set Environment Variable to pick desired YML file:

    - For \*nix based and Mac machines:

    ```sh
    export BROWSERSTACK_CONFIG_FILE="src/test/resources/conf/capabilities/browserstack-local.yml"
    ```

    - For Windows:

    ```sh
    set BROWSERSTACK_CONFIG_FILE="src\test\resources\conf\capabilities\browserstack-local.yml"
    ```

   - To run the default test scenario (e.g. End to End Scenario) on a single BrowserStack device using BrowserStackLocal, use the following command:

  Maven:
  ```sh
  mvn clean test -P bstack-local
  ```

  Gradle:
    ```sh
  gradle clean bstack-local
  ```

- Output

  This run profile executes a single test on a mobile application using local or internal environment on a single device on BrowserStack. Please refer to your [BrowserStack App Automate Dashboard](https://app-automate.browserstack.com/) for test results.


### [Mobile application using local or internal environment] Run the entire test suite in parallel on multiple BrowserStack devices using BrowserStackLocal

In this section, we will run the test cases on a mobile application using a local or internal environment in parallel on multiple devices on Browserstack. To change test capabilities for this configuration, please refer to the `browserstack-local-parallel-devices.yml` file in resources/conf/capabilities/

- How to run the test?

  - Set Environment Variable to pick desired YML file:

    - For \*nix based and Mac machines:

    ```sh
    export BROWSERSTACK_CONFIG_FILE="src/test/resources/conf/capabilities/browserstack-local-parallel-devices.yml"
    ```

    - For Windows:

    ```sh
    set BROWSERSTACK_CONFIG_FILE="src\test\resources\conf\capabilities\browserstack-local-parallel-devices.yml"
    ```

  To run the entire test suite in parallel on multiple BrowserStack devices using BrowserStackLocal, use the following command:

  Maven:
  ```sh
  mvn clean test -P bstack-local-parallel-devices
  ```

  Gradle:
    ```sh
  gradle clean bstack-local-parallel-devices
  ```

- Output

  This run profile executes the entire test suite on a mobile application Mobile application using local or internal environment on multiple devices on BrowserStack. Please refer to your [BrowserStack App Automate Dashboard](https://app-automate.browserstack.com/) for test results.

- Note: By default, this execution would run maximum 5 test threads in parallel on BrowserStack. Refer to the section ["Configuring the maximum parallel test threads for this repository"](#Configuring-the-maximum-parallel-test-threads-for-this-repository) for updating the parallel thread count based on your requirements.

## Generating Allure Reports

- Generate Report using the following command: 

  Maven:
  ```sh
  mvn allure:report
  ```

  Gradle:
    ```sh
  gradle allureReport
  ```

- Serve the Allure report on a server: 

  Maven:
  ```sh
  mvn allure:serve
  ```

  Gradle:
    ```sh
  gradle allureServe
  ```

## Additional Resources

- View your test results on the [BrowserStack App Automate Dashboard](https://www.browserstack.com/app-automate)
- Documentation for writing [Automate test scripts in Java](https://www.browserstack.com/automate/java)
- Customizing your tests capabilities on BrowserStack using our [test capability generator](https://www.browserstack.com/app-automate/capabilities)
- [List of Browsers & mobile devices](https://www.browserstack.com/list-of-browsers-and-platforms?product=automate) for automation testing on BrowserStack
- [Using Automate REST API](https://www.browserstack.com/automate/rest-api) to access information about your tests via the command-line interface
- Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/app-automate/parallel-calculator?ref=github)
- For testing public web applications behind IP restriction, [Inbound IP Whitelisting](https://www.browserstack.com/local-testing/inbound-ip-whitelisting) can be enabled with the [BrowserStack Enterprise](https://www.browserstack.com/enterprise) offering
