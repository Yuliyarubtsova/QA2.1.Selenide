package ru.netology;

import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AlfaCardTest {

    @Test
    void shouldValidTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("+79168689672");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInValidNameTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Vasiliy");
        $("[data-test-id=phone] input").setValue("+79168689672");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name] [class=input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldInValidNoNameTest() {
        open("http://localhost:9999");
        $("[data-test-id=phone] input").setValue("+79168689672");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name] [class=input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNoTelTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone] [class=input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldInValidTelTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("89168689672");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone] [class=input__sub]").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldInValidNotButtonTest() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий");
        $("[data-test-id=phone] input").setValue("89168689672");
        $("[type=button]").click();
        $("[data-test-id=agreement] [class=checkbox__text]").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}
