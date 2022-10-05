package mainLogic.commonActions;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DefaultActions {

    private List<Integer> listCorrectStatusCodes = new ArrayList<>();

    public <T> Response<T> executeAndGetResponseAsClass(Call<T> service) {
        Call<T> call = service;
        try {
            return call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> double calculateResponseTime(Response<T> response) {
        BigDecimal sentAt = BigDecimal.valueOf(response.raw().sentRequestAtMillis());
        BigDecimal receivedAt = BigDecimal.valueOf(response.raw().receivedResponseAtMillis());
        BigDecimal divider = BigDecimal.valueOf(100);
        BigDecimal resultTimeInSeconds = receivedAt.subtract(sentAt).divide(divider);
        resultTimeInSeconds.doubleValue();
        return resultTimeInSeconds.doubleValue();
    }

    public <T> boolean isValidatedError(Response<T> response) {
        boolean isIt = isServerError(response);
        if (listCorrectStatusCodes.contains(response.code())) {
            isIt = true;

        }
        return isIt;
    }

    public <T> boolean isServerError(Response<T> response) {
        boolean isIt = false;
        if (response.code() == 500) {
            isIt = true;
            String serverErrorMessage = response.raw().body().toString();
            System.out.println("Server error is caught. Here is a error body: \n\r" + serverErrorMessage);
        }
        return isIt;
    }


}
