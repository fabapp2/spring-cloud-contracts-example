package com.example.scc.producer.baseclasses;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class ContractsRestBase {

    private RequestSpecification spec;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup(TestInfo testInfo, RestDocumentationContextProvider restDocumentation) {
       //  RestAssuredMockMvc.mockMvc(mockMvc)
        final MockMvcRestDocumentationConfigurer configurer = documentationConfiguration(restDocumentation);
        final RestDocumentationResultHandler document = document(getClass().getSimpleName() + "_" + testInfo.getTestMethod().get().getName());
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(configurer)
                .alwaysDo(document)
                .build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

}