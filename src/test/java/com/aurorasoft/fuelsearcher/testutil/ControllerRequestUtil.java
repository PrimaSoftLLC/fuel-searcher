package com.aurorasoft.fuelsearcher.testutil;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@UtilityClass
public final class ControllerRequestUtil {
    private static final Charset REQUEST_CHARSET = UTF_8;

    public static MockHttpServletResponse doRequest(final MockMvc mockMvc,
                                                    final String url,
                                                    final HttpStatus expectedHttpStatus)
            throws Exception {
        return doRequest(mockMvc, get(url), expectedHttpStatus);
    }

    public static String doRequestAndGetContentAsJson(final MockMvc mockMvc,
                                                      final String url,
                                                      final HttpStatus expectedHttpStatus)
            throws Exception {
        return doRequestAndGetContentAsJson(mockMvc, get(url), expectedHttpStatus);
    }

    public static String doRequestAndGetContentAsJson(final MockMvc mockMvc,
                                                      final RequestBuilder requestBuilder,
                                                      final HttpStatus expectedHttpStatus)
            throws Exception {
        return doRequest(
                mockMvc,
                requestBuilder,
                expectedHttpStatus
        ).getContentAsString(REQUEST_CHARSET);
    }

    public static MockHttpServletResponse doRequest(final MockMvc mockMvc,
                                                    final RequestBuilder requestBuilder,
                                                    final HttpStatus expectedHttpStatus)
            throws Exception {
        return mockMvc.perform(requestBuilder)
                .andExpect(status().is(expectedHttpStatus.value()))
                .andReturn()
                .getResponse();
    }
}
