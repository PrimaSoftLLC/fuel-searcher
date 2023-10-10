package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.model.DownloadedFile;
import com.aurorasoft.fuelsearcher.service.downloadingfile.DownloadingFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType.DOCX;
import static com.aurorasoft.fuelsearcher.model.DownloadedFile.ContentType.XML;
import static com.aurorasoft.fuelsearcher.testutil.ControllerRequestUtil.doRequest;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@WebMvcTest(DownloadingFileController.class)
public final class DownloadingFileControllerTest {
    private static final String HEADER_KEY = "Content-disposition";

    private static final String CONTROLLER_URL = "/document";

    private static final String PATH_TO_DOWNLOAD_FUEL_DOCUMENT = "/fuelDocument";
    private static final String URL_TO_DOWNLOAD_FUEL_DOCUMENT = CONTROLLER_URL + PATH_TO_DOWNLOAD_FUEL_DOCUMENT;

    private static final String PATH_TO_DOWNLOAD_SEARCHER_CONFIG_FILE = "/searcherConfig";
    private static final String URL_TO_DOWNLOAD_SEARCHER_CONFIG_FILE = CONTROLLER_URL
            + PATH_TO_DOWNLOAD_SEARCHER_CONFIG_FILE;

    private static final long MILLISECOND_TIMEOUT_TO_GET_RESPONSE_CONTENT = 100;

    @MockBean
    private DownloadingFileService mockedDownloadingService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void fuelDocumentShouldBeDownloaded()
            throws Exception {
        final byte[] givenBytes = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        final DownloadedFile givenDownloadedFile = DownloadedFile.builder()
                .bytes(givenBytes)
                .name("fuel-document")
                .contentType(DOCX)
                .build();
        when(this.mockedDownloadingService.downloadFuelDocument()).thenReturn(givenDownloadedFile);

        final MockHttpServletResponse response = doRequest(
                this.mockMvc,
                URL_TO_DOWNLOAD_FUEL_DOCUMENT,
                OK
        );

        final String actualHeaderValue = response.getHeader(HEADER_KEY);
        final String expectedHeaderValue = "attachment; filename=fuel-document.docx";
        assertEquals(expectedHeaderValue, actualHeaderValue);

        final byte[] actualContent = findContent(response);
        assertArrayEquals(givenBytes, actualContent);
    }

    @Test
    public void searcherConfigFileShouldBeDownloaded()
            throws Exception {
        final byte[] givenBytes = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        final DownloadedFile givenDownloadedFile = DownloadedFile.builder()
                .bytes(givenBytes)
                .name("searcher-config")
                .contentType(XML)
                .build();
        when(this.mockedDownloadingService.downloadSearcherConfigFile()).thenReturn(givenDownloadedFile);

        final MockHttpServletResponse response = doRequest(
                this.mockMvc,
                URL_TO_DOWNLOAD_SEARCHER_CONFIG_FILE,
                OK
        );

        final String actualHeaderValue = response.getHeader(HEADER_KEY);
        final String expectedHeaderValue = "attachment; filename=searcher-config.xml";
        assertEquals(expectedHeaderValue, actualHeaderValue);

        final byte[] actualContent = findContent(response);
        assertArrayEquals(givenBytes, actualContent);
    }

    private static byte[] findContent(final MockHttpServletResponse response) {
        try {
            MILLISECONDS.sleep(MILLISECOND_TIMEOUT_TO_GET_RESPONSE_CONTENT);
            return response.getContentAsByteArray();
        } catch (final InterruptedException cause) {
            throw new RuntimeException(cause);
        }
    }
}
