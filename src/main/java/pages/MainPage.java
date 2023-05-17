package pages;

import annotations.Path;
import data.CoursesCategoryData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@Path("/")
public class MainPage extends BasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".course-categories__nav a[title='Программирование']")
    private WebElement navCategoryLink;

    private String navCategoryLinkTemplateSelector = ".course-categories__nav a[title='%s']";

    public CoursesCatalogPage clickCategoryCourseLinkByName(CoursesCategoryData categoryData){
        String selector = String.format(navCategoryLinkTemplateSelector, categoryData.getName());
        driver.findElements(By.cssSelector(selector)).get(0).click();

        return new CoursesCatalogPage(driver);
    }

}
