package guru.qa.steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Tag;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

/**Тест может иметь больше 1 тэга. В тасках гредла можно будет настроить этот тест под разные тест-сьюты.*/
@Tag("needed")
@Tag("needless")
public class AnnotationQAapfSteps {

    @Step("Открываем страницу automation-practice-form")
    public void apfPageOpening() {
        Selenide.open("/automation-practice-form");
    }

    @Step("Удаляем футер и блок с рекламой")
    public void adRemoving() {
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
    }

    @Step("Вводим имя")
    public void setFirstName() {
        $("#firstName").setValue("Pilot");
    }

    @Step("Вводим фамилию")
    public void setLastName() {
        $("#lastName").setValue("Pirx");
    }

    @Step("Вводим email")
    public void setEmail() {
        $("#userEmail").setValue("pilot@pirx.pl");
    }

    @Step("Вводим пол")
    public void setSex() {
        $(byText("Male")).click();
    }

    @Step("Вводим номер телефона")
    public void setPhoneNumber() {
        $("#userNumber").setValue("4812345678");
    }

    @Step("Вводим дату рождения")
    public void setBirthDate() {
        $("#dateOfBirthInput").click();
        //        SelenideElement dateOfBirthInput = ($(".react-datepicker__month-select"));
        //        actions().moveToElement(dateOfBirthInput).click(dateOfBirthInput).perform();
        $(".react-datepicker__month-select").click();
        $(byText("August")).click();
        $(".react-datepicker__year-select").click();
        $(byText("1966")).scrollIntoView(true).click();
        $(byText("15")).click();
    }

    @Step("Вводим факультет")
    public void setSubject() {
        $(".subjects-auto-complete__input").click();
        $("#subjectsInput").sendKeys("English");
        $("#subjectsInput").pressEnter();
    }

    @Step("Вводим хобби")
    public void setHobby() {
        $(byText("Reading")).click();
        $(byText("Music")).click();
    }

    @Step("Вводим аватар")
    public void setAvatar() {
        //        $("#form-control-file").uploadFile(new File("meh.jpg"));
        $("#uploadPicture").uploadFromClasspath("meh.jpg");
    }

    @Step("Вводим адрес")
    public void setAddress() {
        $("#currentAddress").setValue("House No. 4 on Bohdan Lepky Street");
    }

    @Step("Вводим штат")
    public void setState() {
        $("#state").click();
        $(byText("Haryana")).click();
    }

    @Step("Вводим город")
    public void setCity() {
        $("#city").click();
        $(byText("Karnal")).click();
    }

    @Step("Жмем кнопку Submit")
    public void clickSubmit() {
        $("#submit").click();
    }

    @Step("Проверяем, что вся введенная информация сохранилась")
    public void resultChecking() {
        $(".modal-content").shouldHave(
                text("Pilot"),
                text("Pirx"),
                text("pilot@pirx.pl"),
                text("Male"),
                text("4812345678"),
                text("15 August,1966"),
                text("Reading, Music"),
                text("meh.jpg"),
                text("House No. 4 on Bohdan Lepky Street"),
                text("Haryana"),
                text("Karnal"),
                text("English"));
    }
}