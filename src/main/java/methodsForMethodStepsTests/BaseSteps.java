package methodsForMethodStepsTests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

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
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
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
    public void searchIssueTitle(final String issue_title) {
        $(byName("q")).click();
        $(byName("q")).val(issue_title).pressEnter();
    }
    @Step("Проверяем, что в репозитории есть Issue с заголовком {issue_title}")
    public void checkThatIssueTitleExists(final String issue_title) {
        $(withText(issue_title)).shouldBe(Condition.exist);
    }
}
