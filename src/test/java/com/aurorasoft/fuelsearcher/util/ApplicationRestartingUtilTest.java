package com.aurorasoft.fuelsearcher.util;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.devtools.restart.Restarter;

import static com.aurorasoft.fuelsearcher.util.ApplicationRestartingUtil.restartApplication;
import static org.mockito.Mockito.*;

public final class ApplicationRestartingUtilTest {

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void applicationShouldBeRestarted() {
        try (final MockedStatic<Restarter> mockedStaticRestarter = mockStatic(Restarter.class)) {
            final Restarter givenRestarter = mock(Restarter.class);
            mockedStaticRestarter.when(Restarter::getInstance).thenReturn(givenRestarter);

            restartApplication();

            verify(givenRestarter, times(1)).restart();
        }
    }

}
