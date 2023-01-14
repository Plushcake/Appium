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
    public void emptyStringTest() throws InterruptedException {
        mainScreenAppium.userInput.isDisplayed();
        mainScreenAppium.userInput.click();
        mainScreenAppium.userInput.sendKeys("Воспроизвел сценарий.");
        mainScreenAppium.buttonChange.isDisplayed();
        mainScreenAppium.buttonChange.click();
        Thread.sleep(2000);
        mainScreenAppium.userInput.isDisplayed();
        mainScreenAppium.userInput.click();
        mainScreenAppium.userInput.clear();
        Thread.sleep(2000);
        mainScreenAppium.buttonChange.isDisplayed();
        mainScreenAppium.buttonChange.click();
        mainScreenAppium.textChangedResult.isDisplayed();
        Thread.sleep(3000);
        Assertions.assertEquals("Воспроизвел сценарий.", mainScreenAppium.textChangedResult.getText());

    }

    @Test
    public void filledString() throws InterruptedException {
        mainScreenAppium.userInput.isDisplayed();
        mainScreenAppium.userInput.click();
        mainScreenAppium.userInput.sendKeys("Hello");
        mainScreenAppium.buttonChange.isDisplayed();
        mainScreenAppium.buttonChange.click();
        Thread.sleep(3000);
        Assertions.assertEquals("Hello", mainScreenAppium.textChangedResult.getText());
    }

    @Test
    public void openTextInActivity() throws InterruptedException {
        mainScreenAppium.userInput.isDisplayed();
        mainScreenAppium.userInput.click();
        mainScreenAppium.userInput.sendKeys("Hello World");
        mainScreenAppium.openTextInActivity.isDisplayed();
        mainScreenAppium.openTextInActivity.click();
        Thread.sleep(3000);
        Assertions.assertEquals("Hello World", mainScreenAppium.expectedText.getText());
        driver.navigate().back();
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
