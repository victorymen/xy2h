//package com.xy2h.interceptor;
//
//import com.xy2h.common.utils.CookieUtils;
//import com.xy2h.pojo.TbAdminUser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * @Author XingJun Qi
// * @MyBlog www.qixingjun.tech
// * @Version 1.0.0
// * @Date 2016/12/26
// * @Description 自定义登录拦截
// */
//public class LoginInterceptor extends HandlerInterceptorAdapter {
//    private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
//
//    /**
//     * 在业务处理器处理请求之前被调用
//     * 如果返回false
//     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
//     * 如果返回true
//     *    执行下一个拦截器,直到所有的拦截器都执行完毕
//     *    再执行被拦截的Controller
//     *    然后进入拦截器链,
//     *    从最后一个拦截器往回执行所有的postHandle()
//     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
//     */
//    /**
//     * Handler执行之前调用这个方法
//     */
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//                             Object handler) throws Exception {
//
//
//        //返回值决定handler是否执行
//        //判断用户是否登陆，从cookie中取token，根据token获取用户信息，调用sso系统的接口。
//        String token= CookieUtils.getCookieValue(request, "TT_TOKEN_ADMIN");
//        TbAdminUser user = adminServiceImpl.getUserByToken(token);
//        if(null==user){
//            //调到登录页面
//            response.sendRedirect("http://sso.tianfang1314.cn/page/admin/login");
//            return false;
//        }
//        //把用户信息放入Request
//        request.setAttribute("admin", user);
//        return true;
//
//    }
//
//    /**
//     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
//     * 可在modelAndView中加入数据，比如当前时间
//     */
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        log.info("==============执行顺序: 2、postHandle================");
//    }
//
//    /**
//     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
//     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        log.info("==============执行顺序: 3、afterCompletion================");
//    }
//
//}
