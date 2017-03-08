package wyl.search.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import wyl.search.util.NetworkUtil;

import java.util.List;

/**
 * Created by dfsj0317 on 2017/3/8.
 */
public class IpInterceptor extends HandlerInterceptorAdapter {
    // 信任ip
    private List<String> ips;

    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        String ip = NetworkUtil.getIpAddress(request);
        System.out.println(ip);
        if(!ips.contains(ip)){
            String path = request.getRequestURI();
            if("/error.smc".equals(path) || "/unknown.smc".equals(path)){
                return true;
            }
            request.getRequestDispatcher("/unknown.smc").forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
