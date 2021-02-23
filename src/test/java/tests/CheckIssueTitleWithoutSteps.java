package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CheckIssueTitleWithoutSteps {
    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "allure-framework/allure2";
    private static final String REPOSITORY_URL = "https://github.com/allure-framework/allure2";
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
        //Открываем главную страницу
        open(BASE_URL);
        $("button.btn-green-mktg-fluid").shouldHave(text("Sign up for GitHub"));
       //Ищем репозиторий
        $(".header-search-input").val(REPOSITORY).submit();
        $("ul.repo-list a").shouldHave(attribute("href", REPOSITORY_URL));
        //Переходим в репозиторий
        $$("a").findBy(attribute("href", REPOSITORY_URL)).click();
        $("h1 strong a").shouldHave(attribute("href", REPOSITORY_URL));
        //Переходим в раздел Issue
        $$("span").findBy(attribute("data-content", "Issues")).click();
        $("div#repo-content-pjax-container summary.btn-primary").shouldHave(text("New issue"));
        //Ищем Issue с заголовком ISSUE_TITLE
        $("input#js-issues-search").val(ISSUE_TITLE).pressEnter();
        //Проверяем, что в репозитории есть Issue с заголовком ISSUE_TITLE
        $(withText(ISSUE_TITLE)).shouldBe(visible);
    }
}
