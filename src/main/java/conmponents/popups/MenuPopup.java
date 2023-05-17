package conmponents.popups;

import annotations.Popup;
import conmponents.AbsBaseComponent;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Popup("css:.js-header3-popup:not[style*='none']")
public class MenuPopup extends AbsBasePopup<MenuPopup> {
    public MenuPopup(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-name='learning'] > .js-header3-popup-container .header3__card-recommendation-header-sub-chunk")
    private WebElement recommendationBlock;

    public MenuPopup recommendationBlockVisible() {
        Assertions.assertThat(this.baseWaiters.waitVisibleElement(recommendationBlock))
                .isTrue();

        return this;
    };

}
