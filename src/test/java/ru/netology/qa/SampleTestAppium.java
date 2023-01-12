package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreenAppium;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleTestAppium {

    private AndroidDriver driver;

    MainScreenAppium mainScreenAppium;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "phone");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        mainScreenAppium = new MainScreenAppium(driver);
    }


    @Test
    public void emptyStringTest() {
        mainScreenAppium.buttonChange.isDisplayed();
        mainScreenAppium.buttonChange.click();
        Assertions.assertEquals("Hello UiAutomator!", mainScreenAppium.textChangedResult.getText());
    }

    @Test
    public void filledString() {
        mainScreenAppium.userInput.isDisplayed();
        mainScreenAppium.userInput.click();
        mainScreenAppium.userInput.sendKeys("Hello World");
        mainScreenAppium.buttonChange.isDisplayed();
        mainScreenAppium.buttonChange.click();
        Assertions.assertEquals("Hello World", mainScreenAppium.textChangedResult.getText());
    }

    @Test
    public void openTextInActivity() {
        mainScreenAppium.userInput.isDisplayed();
        mainScreenAppium.userInput.click();
        mainScreenAppium.userInput.sendKeys("Hello");
        mainScreenAppium.openTextInActivity.isDisplayed();
        mainScreenAppium.openTextInActivity.click();
        Assertions.assertEquals("Hello", mainScreenAppium.expectedText.getText());

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
