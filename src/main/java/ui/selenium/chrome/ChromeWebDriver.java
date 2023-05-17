package ui.selenium.chrome;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.selenium.IWebDriver;

import java.net.URI;
import java.net.URL;
import java.util.logging.Level;

public class ChromeWebDriver implements IWebDriver {
    @Override
    public WebDriver newDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--no-first-run");
        chromeOptions.addArguments("--homepage=about:blank");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        if (getRemoteUrl() == null) {
            try {
                downloadLocalWebDriver(DriverManagerType.CHROME);
            } catch (DriverTypeNotSupported ex) {
                ex.printStackTrace();
            }

            return new ChromeDriver(chromeOptions);
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, System.getProperty("browser", "chrome"));
            capabilities.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browserVersion", "112.0"));
            capabilities.setCapability("enableVNC", true);
            return new RemoteWebDriver(getRemoteUrl(), capabilities);
        }

    }
}
