package mainLogic.servicesAndSteps.userService;

import io.cucumber.java.ru.И;
import mainLogic.DTO.userService.ListUsersRs;
import mainLogic.DTO.userService.SingleUserRs;
import mainLogic.DTO.userService.User;
import retrofit2.Call;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceSteps extends RestWrapperUserService{

    @И("'сервис пользователей' получает пользователя с id равным {int}")
    public void getSingleUserById(int userId){
        Call<SingleUserRs> call = userService.getUser(headers,userId);
        request = call.request();
        singleUserResponse = executeAndStoreResponse(call);
        assertNotNull(singleUserResponse.body(),String.format("Ожидалось что тело будет не пустое, но оно пустое: %s",singleUserResponse.body()));
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
        assertEquals(expectedFirstName,actualFirstName,String.format("Ожидаемое first_name %s не соответствует фактическому %s",expectedFirstName,actualFirstName));
    }

    @И("'сервис пользователей' проверяет что last_name равно {string}")
    public void checkUserLastName(String expectedLastName) {
        String actualLastName = userFromResponse.getLastName();
        assertEquals(expectedLastName,actualLastName,String.format("Ожидаемое last_name пользователя %s не соответствует фактическому %s",expectedLastName,actualLastName));
    }

    @И("'сервис пользователей' проверяет что email равно {string}")
    public void checkUserEmail(String expectedEmail) {
        String actualEmail = userFromResponse.getEmail();
        assertEquals(expectedEmail,actualEmail,String.format("Ожидаемый email пользователя %s не соответствует фактическому %s",expectedEmail,actualEmail));
    }

    @И("'сервис пользователей' проверяет что id равно {int}")
    public void checkUserId(int expectedId) {
        int actualId = userFromResponse.getId();
        assertEquals(expectedId,actualId,String.format("Ожидаемый id пользователя %s не соответствует фактическому %s",expectedId,actualId));

    }

    @И("'сервис пользователей' проверяет что avatar равно {string}")
    public void checkUserAvatar(String expectedAvatarLink) {
        String actualAvatarLink = userFromResponse.getAvatar();
        assertEquals(expectedAvatarLink,actualAvatarLink,String.format("Ожидаемый avatar пользователя %s не соответствует фактическому %s",expectedAvatarLink,actualAvatarLink));
    }

    @И("'сервис пользователей' получает список пользователей со страницы {int}")
    public void getUserListByPage(int pageNumber) {
    }

    @И("'сервис пользователей' получает список пользователей с заданными параметрами")
    public void getListUsersWithParams() throws IOException {
        Call<ListUsersRs> call = userService.getListUsers(headers,queryParams);
        request= call.request();
        listUsersResponse = executeAndStoreResponse(call);
        assertNotNull(listUsersResponse.body(),String.format("Ожидалось, что listUsersResponse не будет пустым, но оно пустое. Тело ошибки: %s", listUsersResponse.errorBody()));
        assertTrue(listUsersResponse.body().getUsersList().size()!=0);
    }

    @И("'сервис пользователей' проверяет что список пользователей содержит пользователя с id равным {int}")
    public void checkThatUsersListContains(int expectedId) {
        assert listUsersResponse.body() != null;
        userFromResponse = listUsersResponse.body()
                .getUsersList()
                .stream()
                .filter((user -> user.getId() == expectedId))
                .findFirst()
                .orElse(null);
        assertNotNull(userFromResponse,String.format("Ожидалось что пользователь с id равным %s присутствует в списке, но список таков: %s",expectedId,listUsersResponse.body()));
    }
}
