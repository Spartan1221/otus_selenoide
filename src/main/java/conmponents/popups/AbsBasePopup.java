package conmponents.popups;

import annotations.Path;
import annotations.Popup;
import data.ModalStateData;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.PageObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbsBasePopup<T>  extends PageObject<T> implements IPopup<T> {

    public AbsBasePopup(WebDriver driver) {
        super(driver);
    }


    private By getPopupBy(){
        Popup popup = getClass().getAnnotation(Popup.class);
        if (popup != null){
            return analayzer(popup.value());
        }
        return null;
    }

    private By analayzer(String locatorStr){
        Pattern pattern = Pattern.compile("(\\w+):(.*)");
        Matcher matcher = pattern.matcher(locatorStr);
        if (matcher.find()){
            String strategySearch = matcher.group(1);
            String value = matcher.group(2);

            switch (strategySearch) {
                case "css":
                    return By.cssSelector(value);
                default:
                    return null;
            }

        }

        return null;
    }


    @Override
    public T modalState(ModalStateData modalStateData){
        ExpectedCondition condition = modalStateData.isState()
                ? ExpectedConditions.visibilityOfElementLocated(getPopupBy()) :
                ExpectedConditions.invisibilityOfElementLocated(getPopupBy());

        Assertions.assertThat(baseWaiters.waitForCondition(condition))
                .as(String.format("Modal status should be %s", modalStateData.name()))
                .isTrue();

        return (T) this;
    }





}
