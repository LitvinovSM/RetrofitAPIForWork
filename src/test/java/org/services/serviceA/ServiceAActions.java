package org.services.serviceA;

import org.DTO.serviceA.users.ListUsers;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

public class ServiceAActions {
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
}
