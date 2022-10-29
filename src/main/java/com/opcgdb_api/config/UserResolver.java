package com.opcgdb_api.config;


import com.opcgdb_api.constant.LanguageCodeEnum;
import com.opcgdb_api.dto.User;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Locale;

@Configuration
public class UserResolver {

    public User resolveUserFromRequest(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");
        String[] splitAuthorizationHeader = authorizationHeader.split(" ");
        if (splitAuthorizationHeader.length != 2) {
            return null;
        }
        String[] splitToken = splitAuthorizationHeader[1].split("\\.");
        if (splitToken.length != 3) {
            return null;
        }
        JSONObject payload = new JSONObject(decode(splitToken[1]));
        return new User().setMail(payload.getString("email"))
                .setProfilePicture(payload.getString("picture"));
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }


}
