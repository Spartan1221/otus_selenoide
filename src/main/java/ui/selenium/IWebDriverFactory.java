package ui.selenium;

import exceptions.DriverTypeNotSupported;
import org.openqa.selenium.WebDriver;

public interface IWebDriverFactory {

    WebDriver getDriver() throws DriverTypeNotSupported;
}
