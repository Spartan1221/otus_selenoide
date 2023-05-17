package ui.selenium;

import exceptions.DriverTypeNotSupported;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ui.selenium.chrome.ChromeWebDriver;
import ui.selenium.firefox.FirefoxWebDriver;
import ui.selenium.opera.OperaWebDriver;

import java.util.Locale;

public class WebDriverFactory implements IWebDriverFactory{

    private String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

    @Override
    public EventFiringWebDriver getDriver(){
        switch (this.browserType) {
            case "chrome": {
                return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
            }
            case "firefox": {
                return new EventFiringWebDriver(new FirefoxWebDriver().newDriver());
            }
            case "opera": {
                return new EventFiringWebDriver(new OperaWebDriver().newDriver());
            }
            default:
                try {
                    throw new DriverTypeNotSupported(this.browserType);
                } catch (DriverTypeNotSupported ex) {
                    ex.printStackTrace();
                    return null;
                }
        }
    }

}
