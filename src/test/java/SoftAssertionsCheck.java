import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsCheck {

    @BeforeAll
    static void testBrowseConfiguration() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void selenideWikiJUnit5search() {
//        Открыть страницу Selenide (Проверьте по возможности не прямой переход на страницу :))

        open("https://github.com/");
        $("span").find(byText("Search or jump to..."));
        $(byText("Search or jump to...")).click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $$("[data-testid=results-list]").first().$("a").click();

//        Перейдите в раздел Wiki проекта

        $("#wiki-tab").click();

//        Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions

        $(byText("Show 3 more pages…")).click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

//        Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5

        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:"));
        String exampleJunit5 = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """;
    }
}

