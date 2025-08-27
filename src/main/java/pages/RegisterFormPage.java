package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterFormPage extends AbsBasePage{

  private final By USER_NAME = By.id("username");
  private final By EMAIL = By.id("email");
  private final By PASSWORD = By.id("password");
  private final By CONFIRM_PASSWORD = By.id("confirm_password");
  private final By BIRTHDATE = By.id("birthdate");
  private final By DROPDOWN_LANGUAGE_LEVEL = By.id("language_level");
  private final By LANGUAGE_LEVEL_BEGINNER = By.xpath("//select[@id='language_level']/option[@value='beginner']");
  private final By LANGUAGE_LEVEL_INTERMEDIATE = By.xpath("//select[@id='language_level']/option[@value='intermediate']");
  private final By LANGUAGE_LEVEL_ADVANCED = By.xpath("//select[@id='language_level']/option[@value='advanced']");
  private final By LANGUAGE_LEVEL_NATIVE = By.xpath("//select[@id='language_level']/option[@value='native']");
  private final By REGISTER = By.cssSelector("[type='submit']");
  private final By RESULT = By.id("output");



  public RegisterFormPage(WebDriver driver) {
    super(driver, "/form.html");
  }

  public void fillForm(String userName, String email) {
    $(USER_NAME).sendKeys(userName);
    $(EMAIL).sendKeys(email);
  }
  public boolean isValidEmail(String email) {
    if (email == null || email.isEmpty()) {
      logger.error("Поле 'Электронная почта' не заполнено");
      return false;
    }
    int locSymbol = email.indexOf('@');
    if (locSymbol > 0 && locSymbol < email.length() - 1) {
      return true;
    } else {
      logger.error("Email должен содержать символ @ и он должен быть не последний");
      return false;
    }
  }
  public void inputPassword(String password) {
    $(PASSWORD).sendKeys(password);
  }
  public void inputConfirmPassword(String confirmPassword) {
    $(CONFIRM_PASSWORD).sendKeys(confirmPassword);
  }

  public boolean validatePassword (String password, String confirmPassword) {
    inputPassword(password);
    inputConfirmPassword(confirmPassword);

    String actualPassword = driver.findElement(PASSWORD).getAttribute("value");
    String actualConfirmPassword = driver.findElement(CONFIRM_PASSWORD).getAttribute("value");

    boolean isTrue = actualPassword.equals(actualConfirmPassword);
    if (isTrue) {
      logger.info("Пароли совпадают");
    } else {
      logger.error("Пароль и Подтверженный пароль не совпадают");
    }
      return isTrue;
    }

  public void inputBirthdate(String birthdate) {
    $(BIRTHDATE).sendKeys(birthdate);
    String formattedDate = formatDate(birthdate);
  }
  private String formatDate(String date) {
    String[] parts = date.split("\\.");
    return String.format("%s-%s-%s", parts[2], parts[1], parts[0]);
  }

    public void clickDropDownLevel() {
      $(DROPDOWN_LANGUAGE_LEVEL).click();
    }

  public void selectLanguageLevel(String languageLevel) {
    Select languageSelect = new Select($(DROPDOWN_LANGUAGE_LEVEL));
    languageSelect.selectByValue(languageLevel);
//    logger.info("Выбран уровень языка: " + languageLevel);
  }

  public String getLanguageLevel(String languageLevel) {
    switch (languageLevel) {
      case "beginner": return "Начальный";
      case "intermediate": return "Средний";
      case "advanced": return "Продвинутый";
      case "native": return "Носитель языка";
      default: return languageLevel;
    }
  }
  public void clickLevelBeginner() {
    $(LANGUAGE_LEVEL_BEGINNER).click();
  }
  public void clickLevelIntermediate() {
    $(LANGUAGE_LEVEL_INTERMEDIATE).click();
  }
  public void clickLevelAdvanced() {
    $(LANGUAGE_LEVEL_ADVANCED).click();
  }
  public void clickLevelNative() {
    $(LANGUAGE_LEVEL_NATIVE).click();
  }

  public void clickButtonRegister() {
    $(REGISTER).click();
  }

  public void verifyResult(String username, String email, String birthdate, String languageLevel) {

    if (!$(RESULT).isDisplayed()) {
      Assertions.fail("Результат не отображается на странице");
    }

    String actualOutputData = $(RESULT).getText();

    String expectedOutData = String.format(
        "Имя пользователя: %s\nЭлектронная почта: %s\nДата рождения: %s\nУровень языка: %s", username, email,
        formatDate(birthdate), languageLevel);

    Assertions.assertEquals("Введенные и отображаемые данные не совпадают",
        expectedOutData, actualOutputData);

    logger.info("Проверка вывода данных прошла успешно!");

  }

}
