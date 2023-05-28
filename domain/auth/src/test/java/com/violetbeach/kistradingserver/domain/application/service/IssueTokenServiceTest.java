package com.violetbeach.kistradingserver.domain.application.service;

import com.violetbeach.kistradingserver.domain.application.port.out.IssueTokenPort;
import com.violetbeach.kistradingserver.domain.domain.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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
        @DisplayName("IssueTokenPort.issueToken()을 호출하고 결과를 그대로 반환한다.")
        void ItCallIssueTokenPort_ItReturnSameToken() {
            // given
            Token exptected = mock(Token.class);
            given(issueTokenPort.issueToken()).willReturn(exptected);

            // when
            Token token = service.issueToken();

            // then
            verify(issueTokenPort, times(1)).issueToken();

            assertThat(token).isEqualTo(exptected);
        }

    }

}