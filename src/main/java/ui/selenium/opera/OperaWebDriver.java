package ui.selenium.opera;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.selenium.IWebDriver;

import java.util.logging.Level;

public class OperaWebDriver implements IWebDriver {
    @Override
    public WebDriver newDriver() {
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.addArguments("--homepage=about:blank");
        operaOptions.addArguments("--ignore-certificate-errors");
        operaOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        operaOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        if (getRemoteUrl() == null) {
            try {
                downloadLocalWebDriver(DriverManagerType.OPERA);
            } catch (DriverTypeNotSupported ex) {
                ex.printStackTrace();
            }

            return new OperaDriver(operaOptions);
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, System.getProperty("browser", "opera"));
            capabilities.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browserVersion", "112.0"));
            capabilities.setCapability("enableVNC", true);
            return new RemoteWebDriver(getRemoteUrl(), capabilities);
        }
    }
}
