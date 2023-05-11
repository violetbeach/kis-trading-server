package com.violetbeach.kistradingserver.boot.support.batch;

import com.violetbeach.kistradingserver.boot.support.client.MockKISClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@SpringBatchTest
@AutoConfigureWireMock(port = 0)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseBatchIntegrationTest {

    @Autowired
    protected JobLauncherTestUtils jobLauncherTestUtils;

    @BeforeAll
    static void setup() {
        MockKISClient.setupIssueToken();
    }

}
