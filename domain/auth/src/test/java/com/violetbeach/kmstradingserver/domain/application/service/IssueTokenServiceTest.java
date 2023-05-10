package com.violetbeach.kmstradingserver.domain.application.service;

import com.violetbeach.kmstradingserver.domain.application.port.out.IssueTokenPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("IssueTokenService의")
class IssueTokenServiceTest {
    IssueTokenService service;
    @Mock
    IssueTokenPort issueTokenPort;

    @BeforeEach
    void setup() {
        service = new IssueTokenService(issueTokenPort);
    }

    @Nested
    @DisplayName("issueToken 메서드는")
    class issueToken {

        @Test
        @DisplayName("IssueTokenPort.issueToken()을 호출한다.")
        void ItThrowsNothing() {
            // given
            doNothing().when(issueTokenPort).issueToken();

            // when
            service.issueToken();

            // then
            verify(issueTokenPort, times(1)).issueToken();
        }

    }

}