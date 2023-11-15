# qrScannerMLive
# SampleTest Appium Test

This is a sample Appium test written in Java using JUnit for automating iOS application testing. The test interacts with the iOS application specified by the bundle ID "com.apple.mobileslideshow" and captures screenshots at various stages of the test.

## Prerequisites

- Appium should be set up and running.
- An iOS device or simulator available for testing.
- Maven or a similar build tool installed to manage dependencies.

## Setup

1. Clone the repository.
2. Open the project in your preferred Java IDE.
3. Install the necessary dependencies using Maven.

## Test Description

The test script follows these main steps:

1. **Set Up Appium Driver**: Initializes the Appium driver with the specified capabilities to connect to the iOS application.

2. **Capture Screenshots Method**: Defines a method `captureScreenShots()` to capture screenshots during different stages of the test. Screenshots are saved with a timestamp in the "screenshots" folder.

3. **Test Method (`sampleTest()`)**: Executes the main test flow, which includes:
   - Capturing an initial screenshot.
   - Setting an implicit wait of 5 seconds.
   - Activating the "com.apple.mobileslideshow" app.
   - Capturing a screenshot after activating the app.
   - Locating and clicking on an element with the specified XPath.
   - Capturing a screenshot after clicking on the element.
   - Waiting for 3 seconds for the picture to load.
   - Taking a screenshot and using ZXing to decode a QR code from the image.
   - Terminating the "com.apple.mobileslideshow" app.
   - Capturing a final screenshot.
   - Printing the decoded QR code result.

4. **Tear Down**: Closes the Appium driver after the test is completed.

## Running the Test

1. Execute the `setUp()` method to initialize the Appium driver.
2. Run the `sampleTest()` method to execute the test.
3. The test results, screenshots, and decoded QR code text will be displayed in the console.
## Notes
You shoul get your own token and user name by going MLive and choose a suitable device that you want to run script and click details and get "Automation" and take your own momentum option values like;
        momentumOptions.put("user", "your username");
        momentumOptions.put("token", "your token");
        momentumOptions.put("gw", gw value of device);
