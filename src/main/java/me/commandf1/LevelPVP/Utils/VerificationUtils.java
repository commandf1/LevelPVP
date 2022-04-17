package me.commandf1.LevelPVP.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Objects;

public class VerificationUtils {

    public static String getHWID() {
        try {
            String toEncrypt = System.getenv("CompUTERNAME".toUpperCase()) + System.getProperty("user.name") +
                    System.getenv("PROCESSOR_IDENTIFIER") +
                    System.getenv("PROCESSOR_LEVEL");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toEncrypt.getBytes());
            StringBuilder hexString = new StringBuilder();
            byte[] byteData = md.digest();
            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verification() {
        String[] HWIDs = Objects.requireNonNull(getHtmlContent("https://api.linkmc.cn/Verification/LevelPVP/HWID", "utf-8")).split("\\|");
        String HWID = getHWID();
        for (String hwid : HWIDs) {
            assert HWID != null;
            if (HWID.equals(hwid)) {
                return true;
            }
        }
        return false;
    }


    public static String getHtmlContent(URL url, String encode) {
        StringBuilder contentBuffer = new StringBuilder();

        int responseCode = -1;
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            con.setConnectTimeout(60000);
            con.setReadTimeout(60000);
            try {
                responseCode = con.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (responseCode == -1) {
                System.out.println(url + " : connection is failure...");
                con.disconnect();
                return null;
            }
            if (responseCode >= 400) {
                con.disconnect();
                return null;
            }

            InputStream inStr = con.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inStr, encode);
            BufferedReader buffStr = new BufferedReader(streamReader);

            String str;
            while ((str = buffStr.readLine()) != null)
                contentBuffer.append(str);
            inStr.close();
        } catch (IOException e) {
            e.printStackTrace();
            contentBuffer = null;
            System.out.println("error: " + url);
        } finally {
            assert con != null;
            con.disconnect();
        }
        assert contentBuffer != null;
        return contentBuffer.toString();
    }

    public static String getHtmlContent(String url, String encode) {

        try {
            URL rUrl = new URL(url);
            return getHtmlContent(rUrl, encode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
