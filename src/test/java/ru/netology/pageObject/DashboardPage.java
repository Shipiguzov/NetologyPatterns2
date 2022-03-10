package ru.netology.pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement checkPage = $(Selectors.withText("Личный кабинет"));

    DashboardPage(){
        checkPage.should(Condition.visible);
    }
}
