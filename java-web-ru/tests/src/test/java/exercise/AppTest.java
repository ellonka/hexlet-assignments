package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;
import io.ebean.Transaction;

import exercise.domain.User;
import exercise.domain.query.QUser;

class AppTest {

    private static Javalin app;
    private static String baseUrl;
    private static Transaction transaction;

    // BEGIN
    @BeforeAll
    public static void startApp() {
        app = App.getApp();
        app.start(0);
        int port = app.port();

        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void stopApp() {
        app.stop();
    }
    // END

    // Хорошей практикой является запуск тестов с базой данных внутри транзакции.
    // Перед каждым тестом транзакция открывается,
    @BeforeEach
    void beforeEach() {
        transaction = DB.beginTransaction();
    }

    // А после окончания каждого теста транзакция откатывается
    // Таким образом после каждого теста база данных возвращается в исходное состояние,
    // каким оно было перед началом транзакции.
    // Благодаря этому тесты не влияют друг на друга
    @AfterEach
    void afterEach() {
        transaction.rollback();
    }

    @Test
    void testRoot() {
        HttpResponse<String> response = Unirest.get(baseUrl).asString();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что на станице есть определенный текст
        assertThat(content).contains("Wendell Legros");
        assertThat(content).contains("Larry Powlowski");
    }

    @Test
    void testUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/5")
            .asString();
        String content = response.getBody();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(content).contains("Rolando Larson");
        assertThat(content).contains("galen.hickle@yahoo.com");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testUserCreationSuccess() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Ivan")
                .field("lastName", "Tretyakov")
                .field("email", "tretyakov.ivan@gmail.com")
                .field("password", "598623")
                .asString();
        assertThat(response.getStatus()).isEqualTo(302);
        User createdUser = new QUser()
                .firstName.equalTo("Ivan")
                .lastName.equalTo("Tretyakov")
                .findOne();
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getFirstName()).isEqualTo("Ivan");
        assertThat(createdUser.getLastName()).isEqualTo("Tretyakov");
        assertThat(createdUser.getEmail()).isEqualTo("tretyakov.ivan@gmail.com");
        assertThat(createdUser.getPassword()).isEqualTo("598623");
    }

    @Test
    void testUserCreationFail() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Vano")
                .field("lastName", "Klym")
                .field("email", "Klym.vanouser.com")
                .field("password", "598")
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);
        User user = new QUser()
                .firstName.equalTo("Vano")
                .lastName.equalTo("Klym")
                .findOne();
        assertThat(user).isNull();
        String content = response.getBody();
        assertThat(content).contains("Должно быть валидным email");
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");
    }
    // END
}
