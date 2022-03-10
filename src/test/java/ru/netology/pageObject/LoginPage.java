package ru.netology.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.LoginData;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[name=login]");
    private SelenideElement passwordField = $("[name=password]");
    private SelenideElement button = $(Selectors.byClassName("button"));
    private SelenideElement errorPopUp = $(Selectors.withText("Неверно указан логин или пароль"));
    private SelenideElement blockPopUpWindow = $(Selectors.withText("Пользователь заблокирован"));

    public void fillFieds(LoginData data){
        loginField.setValue(data.getLogin());
        passwordField.setValue(data.getPassword());
    }

    public DashboardPage loginActiveUser(LoginData data){
        fillFieds(data);
        button.click();
        return new DashboardPage();
    }

    public void loginBlockedUser(LoginData user){
        fillFieds(user);
        button.click();
        blockPopUpWindow.shouldBe(Condition.appear);
    }

    public void incorrectCredentials(LoginData user){
        loginField.setValue(user.getLogin());
        passwordField.setValue(user.getPassword());
        button.click();
        errorPopUp.should(Condition.visible);
    }

    public void loginWithOldPassword(LoginData user, String password){
        loginField.setValue(user.getLogin());
        passwordField.setValue(password);
        button.click();
        errorPopUp.shouldBe(Condition.visible);
        cleanAllFields();
    }

    private void cleanAllFields(){
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }
}
