package by.aurorasoft.fuelsearcher.testutil;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@UtilityClass
public final class ControllerRequestUtil {
    private static final MediaType EXPECTED_CONTENT_TYPE = APPLICATION_JSON;
    private static final Charset REQUEST_CHARSET = UTF_8;

    public static String doRequest(final MockMvc mockMvc,
                                   final RequestBuilder requestBuilder,
                                   final HttpStatus expectedHttpStatus)
            throws Exception {
        return mockMvc.perform(requestBuilder)
                .andExpect(status().is(expectedHttpStatus.value()))
                .andExpect(content().contentType(EXPECTED_CONTENT_TYPE))
                .andReturn()
                .getResponse()
                .getContentAsString(REQUEST_CHARSET);
    }

}
