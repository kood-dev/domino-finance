package com.domino.finance.stock.adapter.in.web;

import com.domino.finance.stock.application.model.DailyStockPriceQuery;
import com.domino.finance.stock.domain.DailyStockPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@WebMvcTest(StockPriceAnalysisController.class)
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureMockMvc
class StockPriceAnalysisControllerTest {
    private static final String SCHEME = "https";
    private static final String HOST = "localhost";

    @MockBean
    StockPriceAnalysisControllerHelper helper;

    MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext context, RestDocumentationContextProvider provider) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
                .build();
    }

    @Test
    void findStockPriceAnalysis() throws Exception {
        String documentPath = "stock/price/analysis";
        String url = "/stock/price/analysis/{stockCode}";
        String stockCode = "005930";


        DailyStockPriceQuery query1 = DailyStockPriceQuery.builder()
                .stockCode(stockCode)
                .high(68400)
                .low(68400)
                .open(67800)
                .close(68400)
                .volume(20349345)
                .transactionTimestamp(1684454400)
                .build();
        List<DailyStockPriceQuery> result = List.of(query1);
        Mockito.when(helper.CreateAndFindStockPriceAnalysis(Mockito.any())).thenReturn(result);
        MockHttpServletRequestBuilder builder = RestDocumentationRequestBuilders.get(url, stockCode);
        ResultActions resultActions = mockMvc.perform(builder);


        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        getDocumentRequest(),
                        getDocumentResponse(),
                        RequestDocumentation.pathParameters(
                                RequestDocumentation.parameterWithName("stockCode").description("Stock Code")
                        ),
                        commonResponseFields(true,
                                PayloadDocumentation.fieldWithPath("stockCode").type(JsonFieldType.STRING).description("Stock Code"),
                                PayloadDocumentation.fieldWithPath("high").type(JsonFieldType.NUMBER).description("최고가"),
                                PayloadDocumentation.fieldWithPath("low").type(JsonFieldType.NUMBER).description("최저가"),
                                PayloadDocumentation.fieldWithPath("open").type(JsonFieldType.NUMBER).description("개장가"),
                                PayloadDocumentation.fieldWithPath("close").type(JsonFieldType.NUMBER).description("종가"),
                                PayloadDocumentation.fieldWithPath("volume").type(JsonFieldType.NUMBER).description("거래량"),
                                PayloadDocumentation.fieldWithPath("transactionTimestamp").type(JsonFieldType.NUMBER).description("거래일자")
                        )
                ))
                .andDo(MockMvcResultHandlers.print());
    }

    public static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(
                modifyUris()
                        .scheme(SCHEME)
                        .host(HOST)
                        .removePort(),
                prettyPrint());
    }

    public static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(prettyPrint());
    }

    public static ResponseFieldsSnippet commonResponseFields(boolean isCollection, FieldDescriptor... descriptors) {
        return PayloadDocumentation.responseFields(
                fieldWithPath("code").type(JsonFieldType.STRING).description("요청 응답 코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메세지")).and(
                Arrays.stream(descriptors)
                        .map(fd -> {
                            if (fd.isOptional()) {
                                return fieldWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).optional().description(fd.getDescription());
                            } else {
                                return fieldWithPath((isCollection ? "data[]." : "data.") + fd.getPath()).type(fd.getType()).description(fd.getDescription());
                            }
                        })
                        .collect(Collectors.toList()));
    }
}