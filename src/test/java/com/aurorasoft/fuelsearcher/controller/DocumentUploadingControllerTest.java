package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.service.documentuploading.DocumentUploadingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.aurorasoft.fuelsearcher.testutil.ControllerRequestUtil.doRequest;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@RunWith(SpringRunner.class)
@WebMvcTest(DocumentUploadingController.class)
public final class DocumentUploadingControllerTest {
    private static final String CONTROLLER_URL = "/uploadingDocument";

    private static final String HTTP_PUT_METHOD = "PUT";

    @MockBean
    private DocumentUploadingService mockedUploadingService;

    @Captor
    private ArgumentCaptor<MultipartFile> multipartFileArgumentCaptor;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void fuelDocumentShouldBeUploaded()
            throws Exception {
        final String givenFuelDocumentName = "fuelDocument";
        final byte[] givenFuelDocumentBytes = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        final MockMultipartFile givenFuelDocument = new MockMultipartFile(
                givenFuelDocumentName,
                givenFuelDocumentBytes
        );

        final String givenSearcherConfigFileName = "searcherConfig";
        final byte[] givenSearcherConfigFileBytes = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        final MockMultipartFile givenSearcherConfigFile = new MockMultipartFile(
                givenSearcherConfigFileName,
                givenSearcherConfigFileBytes
        );

        final RequestBuilder givenRequestBuilder = createRequestBuilder(givenFuelDocument, givenSearcherConfigFile);
        doRequest(this.mockMvc, givenRequestBuilder, OK);

        verify(this.mockedUploadingService, times(1)).upload(
                this.multipartFileArgumentCaptor.capture(),
                this.multipartFileArgumentCaptor.capture()
        );

        final List<MultipartFile> actualCapturedMultipartFiles = this.multipartFileArgumentCaptor.getAllValues();

        final MultipartFile actualFuelDocument = actualCapturedMultipartFiles.get(0);
        assertEquals(givenFuelDocumentName, actualFuelDocument.getName());
        assertArrayEquals(givenFuelDocumentBytes, actualFuelDocument.getBytes());

        final MultipartFile actualSearcherConfigFile = actualCapturedMultipartFiles.get(1);
        assertEquals(givenSearcherConfigFileName, actualSearcherConfigFile.getName());
        assertArrayEquals(givenSearcherConfigFileBytes, actualSearcherConfigFile.getBytes());
    }

    private static RequestBuilder createRequestBuilder(final MockMultipartFile fuelDocument,
                                                       final MockMultipartFile searcherConfigFile) {
        return multipart(CONTROLLER_URL)
                .file(fuelDocument)
                .file(searcherConfigFile)
                .with(DocumentUploadingControllerTest::setPutMethod);
    }

    private static MockHttpServletRequest setPutMethod(final MockHttpServletRequest request) {
        request.setMethod(HTTP_PUT_METHOD);
        return request;
    }
}
