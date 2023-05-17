package conmponents;

import org.openqa.selenium.WebDriver;
import pageobject.PageObject;

import java.awt.print.PageFormat;

public abstract class AbsBaseComponent<T> extends PageObject<T> {

    public AbsBaseComponent(WebDriver driver) {
        super(driver);
    }


}
