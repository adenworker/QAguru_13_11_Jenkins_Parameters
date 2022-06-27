package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.attachments.Attach;
import guru.qa.credentials.CredentialsConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
    @BeforeAll
    static void setUp() {
        /**Подключенный в этом классе Allure Selenide listener будет работать и для всех наследующих классов с тестами.*/
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        /**Подключаем запуск браузера удаленно в селеноиде, на отдельном от сервера Дженкинса сервере.
         * Также включаем возможность писать видео теста.*/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        /**Загружаем настройки для записи видео из объекта класса capabilities, созданного выше*/
        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        /**Создаем переменную, которая принимает значения из командной строки при запуске теста*/
        String browserResolution = System.getProperty("resolution", "1366×768");
        Configuration.browserSize = browserResolution;
        /**Создаем переменную, которая принимает значения из командной строки при запуске теста*/
        String browserVersion = System.getProperty("version", "100");
        Configuration.browserVersion = browserVersion;

        /**Подключаемся к удаленному серверу с селеноидом по логину и паролю*/
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        /**Для выполнения домашки комментируем параметр выше и настраиваем получение адреса из Дженкинса*/
        String remoteBrowser = System.getProperty("remote", "google.com");
        /**Логин и пароль получаем из файла, которого нет в Гите и который мы создаем прямо в Дженкинсе*/
        CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);
        Configuration.remote = "https://" + credentialsConfig.login() + ":" + credentialsConfig.password() + "@" + remoteBrowser;

        /**Создаем переменную, которая принимает значения из командной строки при запуске теста.
         * Эта перемення также содержит дефолтное значение (Opera).*/
        String browserOption = System.getProperty("browser", "Opera");
        Configuration.browser = browserOption;
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}