package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.springframework.boot.devtools.restart.Restarter;

@UtilityClass
public final class ApplicationRestartingUtil {

    public static void restartApplication() {
        Restarter.getInstance().restart();
    }

}
