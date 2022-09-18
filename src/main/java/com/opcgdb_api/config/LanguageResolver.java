package com.opcgdb_api.config;

import com.opcgdb_api.constant.LanguageCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Configuration
public class LanguageResolver extends AcceptHeaderLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String languageCode = request.getHeader("Accept-Language");
        if (StringUtils.isBlank(languageCode)) {
            return Locale.ENGLISH;
        }
        return LanguageCodeEnum.languagesAvailable()
                .stream()
                .filter(code -> code.equals(languageCode.toLowerCase()))
                .map(Locale::new)
                .findFirst()
                .orElse(Locale.ENGLISH);
    }

}
