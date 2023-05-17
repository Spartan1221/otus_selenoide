package ui.selenium.firefox;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.selenium.IWebDriver;

import java.util.logging.Level;

public class FirefoxWebDriver implements IWebDriver {
    @Override
    public WebDriver newDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--homepage=about:blank");
        firefoxOptions.addArguments("--ignore-certificate-errors");
        firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        firefoxOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        if (getRemoteUrl() == null) {
            try {
                downloadLocalWebDriver(DriverManagerType.FIREFOX);
            } catch (DriverTypeNotSupported ex) {
                ex.printStackTrace();
            }

            return new FirefoxDriver(firefoxOptions);
        } else{
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, System.getProperty("browser", "firefox"));
            capabilities.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browserVersion", "112.0"));
            capabilities.setCapability("enableVNC", true);
            return new RemoteWebDriver(getRemoteUrl(), capabilities);
        }
    }

}
