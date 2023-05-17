package homework;

import annotations.Driver;
import conmponents.CourseCardComponent;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(UIExtension.class)
public class FirstTests {

    @Driver
    public WebDriver driver;

    @Test
    public void filterCoursesTest(){
        new MainPage(driver)
                .open();

        List<WebElement> collect = new CourseCardComponent(driver)
                .getCourseTitles()
                .stream()
                .filter(element -> element.getText().equals("Специализация Python"))
                .collect(Collectors.toList());

        assertThat(collect)
                .as("Ожидалось нахождение 1 курса")
                .hasSize(1);
    }

}
