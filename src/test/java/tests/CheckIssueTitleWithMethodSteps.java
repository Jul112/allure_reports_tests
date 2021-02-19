package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import methodsForMethodStepsTests.BaseSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class CheckIssueTitleWithMethodSteps {
    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "allure-framework/allure2";
    private static final String ISSUES = "Issues";
    private static final String ISSUE_TITLE = "Debug task";

    BaseSteps steps = new BaseSteps();

    @Test
    @Link(BASE_URL)
    @Owner("jul112")
    @Tags({@Tag("Web"), @Tag("critical")})

    @Feature("Issue")
    @Story("Поиск Issue в существующем репозитории")
    @DisplayName("Поиск задачи(Issue) по названию")

    public void searchIssueTitle() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        steps.openMainPage();
        steps.searchRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.goToIssues(ISSUES);
        steps.searchIssueTitle(ISSUE_TITLE);
        steps.checkThatIssueTitleExists(ISSUE_TITLE);
    }
}
