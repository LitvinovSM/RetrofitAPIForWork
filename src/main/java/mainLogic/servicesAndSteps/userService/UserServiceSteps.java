package mainLogic.servicesAndSteps.userService;

import io.cucumber.java.ru.И;
import mainLogic.DTO.userService.SingleUserRs;
import retrofit2.Call;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceSteps extends RestWrapperUserService{

    @И("'сервис пользователей' получает пользователя с id равным {int}")
    public void getSingleUserById(int userId) throws IOException {
        Call<SingleUserRs> call = userService.getUser(headers,userId);
        request = call.request();
        singleUserResponse = executeAndStoreResponse(call);
        userFromResponse = singleUserResponse.body().getUser();
        supportFromResponse = singleUserResponse.body().getSupport();
    }

    @И("'сервис пользователей' проверяет что статус код ответа получения одного пользователя равен {int}")
    public void checkStatusCode(int expectedStatusCode) {

        compareStatusCodes(storedValues.get("RESPONSE"),expectedStatusCode);
    }

    @И("'сервис пользователей' проверяет что first_name равно {string}")
    public void checkUserFirstName(String expectedFirstName) {
        String actualFirstName = userFromResponse.getFirstName();
        assertEquals(actualFirstName,expectedFirstName,String.format("Ожидаемое first_name %s не соответствует фактическому %s",expectedFirstName,actualFirstName));
    }

    @И("'сервис пользователей' проверяет что last_name равно {string}")
    public void checkUserLastName(String expectedLastName) {
        String actualLastName = userFromResponse.getLastName();
        assertEquals(actualLastName,expectedLastName,String.format("Ожидаемое имя пользователя %s не соответствует фактическому %s",expectedLastName,actualLastName));
    }
}
