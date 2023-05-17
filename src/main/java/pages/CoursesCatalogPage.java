package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;

@Path("/courses")
public class CoursesCatalogPage extends BasePage<CoursesCatalogPage> {

    public CoursesCatalogPage(WebDriver driver) {
        super(driver);
    }
}
