package com.gdutelc.recruit.interceptor;

import com.gdutelc.recruit.constant.ResultStatusCodeConstant;
import com.gdutelc.recruit.domain.exception.LoginException;
import com.gdutelc.recruit.domain.vo.ResultVO;
import com.gdutelc.recruit.service.interfaces.IInterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员登录验证拦截器
 *
 * @author gregPerlinLi
 * @date 2022-08-28
 */
@Slf4j
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IInterviewService interviewService = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getBean(IInterviewService.class);
        if( HttpMethod.OPTIONS.matches(request.getMethod().toUpperCase()) ) {
            return true;
        }
        String username = (String) request.getSession(false).getAttribute("admin_username");
        String sessionId = request.getSession().getId();
        if ( username != null ) {
            ResultVO<String> result = interviewService.loginVerify(username, sessionId);
            if ( result.getCode() == ResultStatusCodeConstant.SUCCESS ) {
                // log.info("登录校验成功");
                return true;
            }
            log.info("登录校验失败，Redis中没有记录此用户的Session");
            throw new LoginException(ResultStatusCodeConstant.FAILED, "登录校验失败，Redis中没有记录此用户的Session");
        }
        log.info("登录校验失败，Session中没有用户");
        throw new LoginException(ResultStatusCodeConstant.FAILED, "登录校验失败，Session中没有用户");
    }
}
