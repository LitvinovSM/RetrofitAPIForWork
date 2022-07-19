package org;

import static org.junit.Assert.assertTrue;

import okhttp3.Headers;
import org.actions.DefaultActions;
import org.junit.Before;
import org.junit.Test;
import org.DTO.serviceA.login.LoginRq;
import org.DTO.serviceA.login.LoginRs;
import org.DTO.serviceA.users.CreateUserRq;
import org.DTO.serviceA.users.CreateUserRs;
import org.DTO.serviceA.users.ListUsers;
import org.services.serviceA.services.LoginService;
import org.services.serviceA.RestWrapper;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    RestWrapper api;

    @Before
    public void prepareTest() throws IOException {
        api = RestWrapper.loginAs("eve.holt@reqres.in","cityslicka");
    }

    @Test
    public void shouldAnswerWithTrue() throws IOException {

        Call<ListUsers> call = api.userService.listUsers();
        String body = call.execute().body().toString();
        System.out.println(body);

    }

    @Test
    public void shouldAnswerWithTrue1() throws IOException, InterruptedException {
        Call<ListUsers> call = api.userService.listUsersDelay("1");

        ListUsers abc = call.execute().body();
        //Проверка что у первого юзера в списке lastName = Bluth
        assertTrue(abc.getUsersList().get(0).getLastName().equals("Bluth"));
        assertTrue(abc.getUsersList().size()==6);

    }

    @Test
    public void shouldAnswerWithTrue2() throws IOException, InterruptedException {
        DefaultActions defaultActions = new DefaultActions();
        CreateUserRq rq = CreateUserRq.builder().job("abc").name("cde").build();
        Call<CreateUserRs> call = api.userService.createUser(rq);
        Response<CreateUserRs> rsss = defaultActions.executeAndGetResponseAsClass(api.userService.createUser(rq));
        double time = defaultActions.calculateResponseTime(rsss);
        System.out.println(rsss.toString());
    }

        @Test
    public void shouldAnswerWithTrue10() throws IOException, InterruptedException {
            LoginRq rq = LoginRq.builder().email("eve.holt@reqres.in").password("cityslicka").build();
            LoginService service = api.loginService;
            Call<LoginRs> call = service.login(rq);

            LoginRs rs = call.execute().body();
            Headers headers = call.request().headers();
        System.out.println(rs.toString());
    }
}
