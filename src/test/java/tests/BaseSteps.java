package tests;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class BaseSteps {
    private static final String BASE_URL = "https://github.com";

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open(BASE_URL);
        $("button.btn-green-mktg-fluid").shouldHave(text("Sign up for GitHub"));
    }
    @Step("Ищем репозиторий {repository}")
    public void searchRepository(final String repository, final String repositoryUrl) {
        $(".header-search-input").val(repository).submit();
        $("ul.repo-list a").shouldHave(attribute("href", repositoryUrl));
    }
    @Step("Переходим в репозиторий {repository}")
    public void openRepository(final String repositoryUrl) {
        $$("a").findBy(attribute("href", repositoryUrl)).click();
        $("h1 strong a").shouldHave(attribute("href", repositoryUrl));
    }
    @Step("Переходим в раздел Issues")
    public void goToIssues() {
        $$("span").findBy(attribute("data-content", "Issues")).click();
        $("div#repo-content-pjax-container summary.btn-primary").shouldHave(text("New issue"));
    }
    @Step("Ищем Issue с заголовком {issue_title}")
    public void searchIssueTitle(final String issueTitle) {
        $("input#js-issues-search").val(issueTitle).pressEnter();
    }
    @Step("Проверяем, что в репозитории есть Issue с заголовком {issue_title}")
    public void checkThatIssueTitleExists(final String issueTitle) {
        $(withText(issueTitle)).shouldBe(visible);
    }
}
