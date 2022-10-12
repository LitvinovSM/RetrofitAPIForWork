package mainLogic.commonActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import mainLogic.DTO.error.ErrorMessage;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static mainLogic.servicesAndSteps.RestWrapperAbstract.attachTextToAllure;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface DefaultActions {

    public default  <T> double calculateResponseTime(Response<T> response) {
        BigDecimal sentAt = BigDecimal.valueOf(response.raw().sentRequestAtMillis());
        BigDecimal receivedAt = BigDecimal.valueOf(response.raw().receivedResponseAtMillis());
        BigDecimal divider = BigDecimal.valueOf(100);
        BigDecimal resultTimeInSeconds = receivedAt.subtract(sentAt).divide(divider);
        resultTimeInSeconds.doubleValue();
        return resultTimeInSeconds.doubleValue();
    }

    public default <T> void compareStatusCodes(Response<T> response,int expectedStatusCode) {
        int actualStatusCode = response.code();
        if (actualStatusCode!=expectedStatusCode){attachTextToAllure("Сравнение статус кодов, ответ и тело ответа", response.toString(), response.body().toString());}
        assertEquals(actualStatusCode, expectedStatusCode, String.format("Ожидаемый статус код: %s не соответствует фактическому: %s", expectedStatusCode, actualStatusCode));
    }

    public default <T> ErrorMessage getResponseAsErrorMessage(Response<T> response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = response.errorBody().string();
        return mapper.readValue(jsonString,ErrorMessage.class);
    }

    public default <T> void compareErrorMessageText(Response<T> response, String expectedErrorText) throws IOException {
        String actualMessage = getResponseAsErrorMessage(response).getError();
        assertTrue(actualMessage.equals(expectedErrorText),String.format("Текущей текст ошибки: %s не соответствует ожидаемому: %s",actualMessage,expectedErrorText));
    }
}
