package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

public class CheckIssueTitleWithLambdaSteps {
    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "allure-framework/allure2";
    private static final String ISSUES = "Issues";
    private static final String ISSUE_TITLE = "Debug task";

    @Test
    @Link(BASE_URL)
    @Owner("jul112")
    @Tags({@Tag("Web"), @Tag("critical")})

    @Feature("Issue")
    @Story("Поиск Issue в существующем репозитории")
    @DisplayName("Поиск задачи(Issue) по названию")
    public void searchIssueTitle() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        parameter("Repository", REPOSITORY);
        parameter("Issue", ISSUE_TITLE);

        step("Открываем главную страницу", () -> {
            open(BASE_URL);
        });

        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Переходим в раздел Issue", () -> {
            $(withText(ISSUES)).click();
        });

        step("Ищем Issue с заголовком " + ISSUE_TITLE, () -> {
            $(byName("q")).click();
            $(byName("q")).val(ISSUE_TITLE).pressEnter();
        });

        step("Проверяем, что в репозитории есть Issue с заголовком " + ISSUE_TITLE, () -> {
            $(withText(ISSUE_TITLE)).shouldBe(Condition.exist);
        });
    }
}
