package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import pages.RegisterFormPage;

public class TestRegisterFormPage extends AbsBaseTestSuite{
  private static final Logger logger = LogManager.getLogger(TestRegisterFormPage.class);

  @Test
  @DisplayName("Тест № 1: Пароли совпадают-регистрация успешна")
  public void formRegister() {
    String userName = "Иван";
    String email = "homework@test.ru";
    String password = "12345";
    String confirmPassword = "12345";
    String birthdate = "01.01.1990";
    String languageLevel = "intermediate";

    RegisterFormPage mainPage = new RegisterFormPage(driver);
    logger.info("Открытие страницы");
    mainPage.open();
    logger.info("Ввод имени пользователя и email");
    mainPage.fillForm(userName, email);
    mainPage.isValidEmail(email);
    logger.info("Ввод пароля и его подтверждение");
    mainPage.inputPassword(password);
    mainPage.inputConfirmPassword(confirmPassword);
    mainPage.validatePassword(password, confirmPassword);
    logger.info("Ввод даты рождения");
    mainPage.inputBirthdate(birthdate);
    logger.info("Выбор уровня знания языка");
    mainPage.clickDropDownLevel();
    mainPage.selectLanguageLevel(languageLevel);
    logger.info("Регистрация пользователя");
    mainPage.clickButtonRegister();
    logger.info("Проверка отображения данных");
    mainPage.verifyResult(userName, email, birthdate, languageLevel, true);
  }

    @Test
    @DisplayName("Тест № 2: Пароли не совпадают и email не валидный")
    public void formRegisterError() {
      String userName = "Иван";
      String email = "homeworktest.ru";
      String password = "12345";
      String confirmPassword = "54321";
      String birthdate = "01.01.1990";
      String languageLevel = "intermediate";

      RegisterFormPage mainPage = new RegisterFormPage(driver);
      logger.info("Открытие страницы");
      mainPage.open();
      logger.info("Ввод имени пользователя и email");
      mainPage.fillForm(userName, email);
      mainPage.isValidEmail(email);
      logger.info("Ввод пароля и его подтверждение");
      mainPage.inputPassword(password);
      mainPage.inputConfirmPassword(confirmPassword);
      mainPage.validatePassword(password, confirmPassword);
      logger.info("Ввод даты рождения");
      mainPage.inputBirthdate(birthdate);
      logger.info("Выбор уровня знания языка");
      mainPage.clickDropDownLevel();
      mainPage.selectLanguageLevel(languageLevel);
      logger.info("Регистрация пользователя");
      mainPage.clickButtonRegister();
      logger.info("Проверка отображения данных");
      mainPage.verifyResult(userName, email, birthdate, languageLevel, false);
  }
}

