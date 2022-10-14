package mainLogic.commonActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import mainLogic.DTO.AbstractResponse;
import mainLogic.DTO.error.ErrorMessage;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import static mainLogic.servicesAndSteps.RestWrapperAbstract.attachTextToAllure;
import static org.junit.jupiter.api.Assertions.*;

public interface DefaultActions {
    String RESPONSE_KEY = "RESPONSE";
    /**
     * Hash map for storing Responses*/
    HashMap<String,Response<? extends AbstractResponse>> storedValues = new HashMap<>();

    /**
     *Executing the service and put the response to HashMap storedValues
     * @param service the service will be executed
     * @return response with the type of executed service*/
    default <T extends AbstractResponse> Response<T> executeAndStoreResponse(Call<T> service) {
        try {
            Response<T> response = service.execute();
            storedValues.put(RESPONSE_KEY,response);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calculate the response time as a double type value
     *
     * @param response target response
     * @return response time as a double type value
     */
    default <T> double calculateResponseTime(Response<T> response) {
        BigDecimal sentAt = BigDecimal.valueOf(response.raw().sentRequestAtMillis());
        BigDecimal receivedAt = BigDecimal.valueOf(response.raw().receivedResponseAtMillis());
        BigDecimal divider = BigDecimal.valueOf(100);
        BigDecimal resultTimeInSeconds = receivedAt.subtract(sentAt).divide(divider, RoundingMode.DOWN);
        return resultTimeInSeconds.doubleValue();
    }

    /**
     * Compare actual status code with expected status code of response
     *
     * @param response           target response
     * @param expectedStatusCode expected status code
     */
    default <T> void compareStatusCodes(Response<T> response, int expectedStatusCode) {
        int actualStatusCode = response.code();
        if (actualStatusCode != expectedStatusCode) {
            if (response.body() != null) {
                attachTextToAllure("Сравнение статус кодов, ответ и тело ответа", response.toString(), response.body().toString());
            }
        }
        assertEquals(actualStatusCode, expectedStatusCode, String.format("Ожидаемый статус код: %s не соответствует фактическому: %s", expectedStatusCode, actualStatusCode));
    }

    /**
     * Cast the response body contains the expected error to ErrorMessage object
     *
     * @param response target response
     * @return an object with ErrorMessage type
     */
    default <T> ErrorMessage getResponseAsErrorMessage(Response<T> response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        assertNotNull(response.errorBody(), "Ожидалось что тело с ошибкой не пустое, но оно пустое");
        String jsonString = response.errorBody().string();
        return mapper.readValue(jsonString, ErrorMessage.class);
    }

    /**
     * Compare the actual error text with expected error text
     *
     * @param response          target response
     * @param expectedErrorText expected full error text
     */
    default <T> void compareErrorMessageText(Response<T> response, String expectedErrorText) throws IOException {
        String actualMessage = getResponseAsErrorMessage(response).getError();
        if (!actualMessage.equals(expectedErrorText)) {
            attachTextToAllure("Сравнение ожидаемого и фактического кода ошибки", expectedErrorText, actualMessage);
        }
        assertEquals(actualMessage, expectedErrorText, String.format("Текущей текст ошибки: %s не соответствует ожидаемому: %s", actualMessage, expectedErrorText));
    }
}
