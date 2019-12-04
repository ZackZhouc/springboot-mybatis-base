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
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    private final String AUTH_HEADER = "Authorization";
    private boolean ROLE_REQUIRE;
    private boolean PERMISSION_REQUIRE;
    private boolean NONE;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTH_HEADER);
        boolean hasAnnotation = handler.getClass().isAssignableFrom(HandlerMethod.class);
        String uri = request.getRequestURL().toString();
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

                    // 进入到这一步表示已经登录

                    // 不需要权限
                    if (NONE)
                        return true;

                    // 需要权限 优先级高于角色 如果配置需要权限 先检验权限 低优先级pass掉不检查
                    if (PERMISSION_REQUIRE) {
                        String permissionName = requirePemission.value();
                        boolean hasPermission = checkHasPermission(permissionName, id);
                        if (hasPermission)
                            return true;
                        log.warn("(logmanage--nopermission)" + id + "进行没有权限的" + uri + "请求---------------------");
                        printOut(response, JSONObject.toJSONString(ResponseVO.genNoPermissionResponse()));
                        return false;
                    }
                    // 需要角色
                    if (ROLE_REQUIRE) {
                        String roleName = requireRole.value();
                        boolean hasRole = checkHasRole(roleName, id);
                        if (hasRole)
                            return true;
                        log.warn("(logmanage--norole)" + id + "进行没有角色权限的" + uri + "请求---------------------");
                        printOut(response, JSONObject.toJSONString(ResponseVO.genNoPermissionResponse()));
                        return false;
                    }


                } catch (TokenExpiredException e) {
                    log.warn(e.getMessage());
                    printOut(response, JSONObject.toJSONString(ResponseVO.genExpireResponse()));
                    return false;
                } catch (InvalidClaimException e) {
                    log.error("(logmanage--invalidTokenException)非法token-------------------");
                    log.error(e.getMessage());
                    printOut(response, JSONObject.toJSONString(ResponseVO.genIllegalRequestResponse()));
                    return false;
                } catch (JWTDecodeException e) {
                    log.error("(logmanage--decodeTokenException)非法token-------------------");
                    log.error(e.getMessage());
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

    private boolean checkHasRole(String roleName, String id) {
        // todo  getRolesById
        List<String> roles = new ArrayList<>();
        roles.add("test");
        return roles.contains(roleName);
    }

    private boolean checkHasPermission(String roleName, String id) {
        // todo  getPermissionsById
        List<String> roles = new ArrayList<>();
        roles.add("test");
        return roles.contains(roleName);
    }
}
