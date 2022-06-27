package guru.qa.tests;

import guru.qa.steps.AnnotationQAapfSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**Общие для всех тестов аннотации можно вынести на уровень класса и не повторять их перед каждым тестом.*/
@Owner("alisichkin")

public class DemoQAapfTestCase extends TestBase {
    /**Allure Selenide listener нам тут подключать не нужно, так как он был подключен в родительском классе TestBase.*/
    @Test
    @DisplayName("Проверка работы автотеста на странице проверки формы DemoQA")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Подключение Дженкинса")
    @Story("Работа связки Дженкинс + Гитхаб + Аллюр")
    @Description("Дженкинс на базе autotests.cloud прогоняет автотест с гитхаба")
    @Link(name = "DemoQA", url = "https://demoqa.com/automation-practice-form")
    void demoQAapf() {
        AnnotationQAapfSteps steps = new AnnotationQAapfSteps();

        steps.apfPageOpening();
        steps.adRemoving();
        steps.setFirstName();
        steps.setLastName();
        steps.setEmail();
        steps.setSex();
        steps.setPhoneNumber();
        steps.setBirthDate();
        steps.setSubject();
        steps.setHobby();
        steps.setAvatar();
        steps.setAddress();
        steps.setState();
        steps.setCity();
        steps.clickSubmit();
        steps.resultChecking();
    }
}
