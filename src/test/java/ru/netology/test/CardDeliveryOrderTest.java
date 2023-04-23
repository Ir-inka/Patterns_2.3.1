package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {
    @BeforeEach

    public void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test

    public void rescheduleDateWithValidData() {                                                            //  перепланирование даты валидными данными
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id =city] input").val(validUser.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").val(firstMeetingDate);
        $("[data-test-id=name] input").val(validUser.getName());
        $("[data-test-id=phone] input").val(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $x("//button//span[contains(text(),'Запланировать')]").click();
        $("[data-test-id='success-notification']").should(Condition.text("Встреча успешно запланирована на " + firstMeetingDate),
                Duration.ofSeconds(15)).shouldBe(visible);

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").val(secondMeetingDate);
        $x("//button//span[contains(text(),'Запланировать')]").click();
        $("[data-test-id='replan-notification']").should(Condition.text("Необходимо подтверждение"));
        $x("//button//span[contains(text(),'Перепланировать')]").click();
        $("[data-test-id='success-notification']").should(Condition.text("Встреча успешно запланирована на " + secondMeetingDate),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test

    public void emptyCityField() {                                                            //  перепланирование даты поле город пустое
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").val(firstMeetingDate);
        $("[data-test-id=name] input").val(validUser.getName());
        $("[data-test-id=phone] input").val(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $x("//button//span[contains(text(),'Запланировать')]").click();
        $("[data-test-id=city] .input__sub").should(Condition.text("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test

    public void emptyDataField() {                                                            //  перепланирование даты поле дата пустое
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id =city] input").val(validUser.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=name] input").val(validUser.getName());
        $("[data-test-id=phone] input").val(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $x("//button//span[contains(text(),'Запланировать')]").click();
        $("[data-test-id=date] .input__sub").shouldBe(Condition.text("Неверно введена дата")).shouldBe(visible);
    }

    @Test

    public void emptyNameField() {                                                            //  перепланирование даты поле Фамилия и Имя пустое
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id =city] input").val(validUser.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").val(firstMeetingDate);
        $("[data-test-id=phone] input").val(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $x("//button//span[contains(text(),'Запланировать')]").click();
        $("[data-test-id=name] .input__sub").should(Condition.text("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test

    public void emptyPhoneField() {                                                            //  перепланирование даты поле телефон пустое
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id =city] input").val(validUser.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").val(firstMeetingDate);
        $("[data-test-id=name] input").val(validUser.getName());
        $("[data-test-id=agreement]").click();
        $x("//button//span[contains(text(),'Запланировать')]").click();
        $("[data-test-id=phone] .input__sub").should(Condition.text("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test

    public void doNotTick() {                                                            //  галочку не поставили
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id =city] input").val(validUser.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").val(firstMeetingDate);
        $("[data-test-id=name] input").val(validUser.getName());
        $("[data-test-id=phone] input").val(validUser.getPhone());
        $x("//button//span[contains(text(),'Запланировать')]").click();
        $(".checkbox__text").shouldBe(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"))
                .shouldBe(visible);
    }
}
