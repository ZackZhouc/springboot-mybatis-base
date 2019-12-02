package com.yzj.cep.web.intercept;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.yzj.cep.common.pojo.vo.ResponseVO;
import com.yzj.cep.service.util.JwtUtil;
import com.yzj.cep.web.annotation.RequireLogin;
import com.yzj.cep.web.annotation.RequirePemission;
import com.yzj.cep.web.annotation.RequireRole;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
    private final String AUTH_HEADER = "Authorization";
    private boolean ROLE_REQUIRE;
    private boolean PERMISSION_REQUIRE;
    private boolean NONE;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTH_HEADER);
        boolean hasAnnotation = handler.getClass().isAssignableFrom(HandlerMethod.class);
        if (hasAnnotation) {
            RequireLogin requireLogin = ((HandlerMethod) handler).getMethodAnnotation(RequireLogin.class);
            RequirePemission requirePemission = ((HandlerMethod) handler).getMethodAnnotation(RequirePemission.class);
            RequireRole requireRole = ((HandlerMethod) handler).getMethodAnnotation(RequireRole.class);

            ROLE_REQUIRE = requireRole != null;
            PERMISSION_REQUIRE = requirePemission != null;
            NONE = (!ROLE_REQUIRE && !PERMISSION_REQUIRE);

            if (token == null) {
                // 任何权限都不要的情况下 直接通过
                if (requireLogin == null && requirePemission == null && requireRole == null)
                    return true;
                // 需要任何一个权限 返回未登录json
                printOut(response, JSONObject.toJSONString(ResponseVO.genUnauthResponse()));
                return false;
            } else {
                try {
                    Map<String, Claim> claims = JwtUtil.verifyToken(token);
                    String id = claims.get("id").asString();
                    // 仅需要登录 通过
                    if (requireLogin != null)
                        return true;

                    // 不需要权限
                    if (NONE) 
                        return true;

                    // 仅需要角色
                    if (ROLE_REQUIRE) {

                    }

                    if (PERMISSION_REQUIRE) {

                    }

                } catch (TokenExpiredException e) {
                    e.printStackTrace();
                    printOut(response, JSONObject.toJSONString(ResponseVO.genExpireResponse()));
                    return false;
                } catch (InvalidClaimException e) {
                    e.printStackTrace();
                    printOut(response, JSONObject.toJSONString(ResponseVO.genIllegalRequestResponse()));
                    return false;
                } catch (JWTDecodeException e) {
                    e.printStackTrace();
                    printOut(response, JSONObject.toJSONString(ResponseVO.genIllegalRequestResponse()));
                    return false;
                }
            }

            return true;
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    private void printOut(HttpServletResponse response, String out) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.flush();
        pw.println(out);
        pw.close();
    }
}
