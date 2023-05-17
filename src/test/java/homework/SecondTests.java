package homework;

import annotations.Driver;
import conmponents.CourseCardComponent;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class SecondTests {

    @Driver
    public WebDriver driver;

    @Test
    public void clickOnLatestCourseTest(){
        new MainPage(driver).open();
        new CourseCardComponent(driver)
                .clickOnCourseByBoundaryDate(true);
    }

}
