package methodsForMethodStepsTests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BaseSteps {
    private static final String BASE_URL = "https://github.com";

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open(BASE_URL);
    }
    @Step("Ищем репозиторий {repository}")
    public void searchRepository(final String repository) {
        $(".header-search-input").val(repository).submit();
    }
    @Step("Переходим в репозиторий {repository}")
    public void openRepository(final String repository) {
        $(By.linkText(repository)).click();
    }
    @Step("Переходим в раздел Issues")
    public void goToIssues(final String issues) {
        $(withText(issues)).click();
    }
    @Step("Ищем Issue с заголовком {issue_title}")
    public void searchIssueTitle(final String issueTitle) {
        $(byName("q")).val(issueTitle).pressEnter();
    }
    @Step("Проверяем, что в репозитории есть Issue с заголовком {issue_title}")
    public void checkThatIssueTitleExists(final String issueTitle) {
        $(withText(issueTitle)).shouldBe(exist);
    }
}
