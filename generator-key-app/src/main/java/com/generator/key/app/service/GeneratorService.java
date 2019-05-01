package com.generator.key.app.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GeneratorService {

    private static final String COOKIE_NAME = "cvpw";
    private static final String DOMAIN_NAME = "valentinareposteria.com";
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorService.class);

    @Value("${generator.key.app.secret.key}")
    private String siteKey;

    @Value("${generator.key.app.cookie.value}")
    private String cookieValue;

    /**
     * Create cookie
     * @param request
     * @param response
     * @return
     */
    public boolean generatorKey(HttpServletRequest request, HttpServletResponse response){
        try {
            if(!searchCookie(request)) {
                createCookie(response);

                LOGGER.info("Cookie created");
            }

            return true;
        } catch (Exception e) {
            LOGGER.error("There was an error creating cookie for site", e);
            return false;
        }
    }

    /**
     * Search if exist the cookie
     * @param request
     * @return
     */
    private boolean searchCookie(HttpServletRequest request) {
        if (request != null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Create the cookie with expiration for a month and for company domain
     * @param response
     * @throws Exception
     */
    private void createCookie(HttpServletResponse response) throws Exception{
        Cookie cookie = new Cookie(COOKIE_NAME, encrypt());
        cookie.setMaxAge(43200 * 60);
        cookie.setPath("/");
        cookie.setDomain(DOMAIN_NAME);
        response.addCookie(cookie);
    }


    /**
     * Encrypt cookie value
     *
     * @return
     * @throws Exception
     */
    private String encrypt() throws Exception {
        Cipher encripta = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(this.siteKey.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(encripta.doFinal(this.cookieValue.getBytes("UTF-8")));
    }
}
