package me.lixin.web.interceptor;


import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import lombok.extern.slf4j.Slf4j;
import me.lixin.web.constant.JwtConstant;
import me.lixin.web.enums.EnumResponseCode;
import me.lixin.web.exception.BusinessException;
import me.lixin.web.util.CookieUtils;
import me.lixin.web.util.UserContext;
import me.lixin.web.dto.LoginUserDTO;
import me.lixin.web.util.RedisUtils;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final String TOKEN = "token";

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("token校验开始");
        // 从header获取
        String token = request.getHeader(TOKEN);
        // 从请求参数获取
        if (org.apache.commons.lang3.StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN);
        }

        // 从cookie获取
        if (org.apache.commons.lang3.StringUtils.isBlank(token)) {
            String tempToken = CookieUtils.getCookieToken(TOKEN, request);
            if (Objects.nonNull(tempToken)) {
                token = tempToken;
            }
        }
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(EnumResponseCode.TOKEN_INVALID.getValue(), EnumResponseCode.TOKEN_INVALID.getDesc());
        }
        log.info("获取的token:{}",token);
        LoginUserDTO loginUser = parseToken(token);
        UserContext.setLoginUser(loginUser);

        return true;
    }

    private LoginUserDTO parseToken(String token) {

        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWSVerifier verifier = new MACVerifier(JwtConstant.SIGNATURE.getBytes());

            if (!jwsObject.verify(verifier)) {
                log.info("token校验失败");
                throw new BusinessException(EnumResponseCode.TOKEN_INVALID.getValue(), EnumResponseCode.TOKEN_INVALID.getDesc());
            }
            JSONObject payload = jwsObject.getPayload().toJSONObject();
            // 暂时去掉redis校验 假定是永久的
            /*String artistName = payload.getAsString("artistName");
            String userKey = artistName + RedisKey.LOGIN_USER + ":" + payload.getAsNumber("userId").intValue();
            String paramTokenMd5 = MD5Utils.md5Hex(token);

            String redisTokenMd5 = redisUtils.getString(userKey);

            if (Objects.isNull(redisTokenMd5) || !Objects.equals(paramTokenMd5, redisTokenMd5)) {
                throw new BusinessException(EnumResponseCode.TOKEN_EXPIRED.getValue(), EnumResponseCode.TOKEN_EXPIRED.getDesc());
            }*/
            LoginUserDTO loginUser = new LoginUserDTO();
            loginUser.setUserId(payload.getAsNumber("userId").longValue());
            loginUser.setUserName(payload.getAsString("userName"));
            loginUser.setUserAvatarUrl(payload.getAsString("userAvatarUrl"));
            loginUser.setArtistId(payload.getAsNumber("artistId").longValue());
            loginUser.setArtistName(payload.getAsString("artistName"));
            return loginUser;
        } catch (Exception e) {
            throw new BusinessException(EnumResponseCode.TOKEN_INVALID.getValue(), EnumResponseCode.TOKEN_INVALID.getDesc());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.clear();
    }

}
