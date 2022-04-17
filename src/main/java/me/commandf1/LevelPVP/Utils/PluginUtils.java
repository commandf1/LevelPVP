package me.commandf1.LevelPVP.Utils;

import java.util.Objects;

import static me.commandf1.LevelPVP.Utils.VerificationUtils.getHtmlContent;

public class PluginUtils {

    public static boolean isCrackedVersion = true;

    public static void CheckCrackedVersion() {
        String[] HWIDs = Objects.requireNonNull(getHtmlContent("https://api.linkmc.cn/Verification/LevelPVP/HWID", "utf-8")).split("\\|");
        String HWID = VerificationUtils.getHWID();
        for (String hwid : HWIDs) {
            assert HWID != null;
            if (HWID.equals(hwid)) {
                isCrackedVersion = false;
                break;
            }
        }
    }

}
