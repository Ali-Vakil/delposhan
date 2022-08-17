package holosen.shop.app.config.filter;

import holosen.shop.app.config.JwtTokenUtil;
import holosen.shop.app.helper.exceptions.jwtTokenException;
import holosen.shop.app.helper.uiModels.people.UserVM;
import holosen.shop.app.services.people.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class jwtRequestFilter implements Filter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private List<String> excludeUrl;
    private List<String> excludeContainsUrl;

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        excludeUrl = new ArrayList<>();

        excludeContainsUrl = new ArrayList<>();
        excludeContainsUrl.add("/api/utils/upload/files/");
        excludeContainsUrl.add("/api/blog/info/");
        excludeContainsUrl.add("/api/product/getall/");
        excludeContainsUrl.add("/api/productcategory/info/");
        excludeContainsUrl.add("/api/product/info/");
        excludeContainsUrl.add("/api/customer/getbyuserid/");

        excludeUrl.add("/api/user/login");
        excludeUrl.add("/api/color/");
        excludeUrl.add("/api/size/");
        excludeUrl.add("/api/nav/");
        excludeUrl.add("/api/slider/");
        excludeUrl.add("/api/product/newproduct/");
        excludeUrl.add("/api/product/popularproduct/");
        excludeUrl.add("/api/product/expensiveproduct/");
        excludeUrl.add("/api/product/cheapestproduct/");
        excludeUrl.add("/api/productcategory");
        excludeUrl.add("/api/content/getalldata");
        excludeUrl.add("/api/blog/getalldata");
        excludeUrl.add("/api/payment/");
        excludeUrl.add("/api/Payment/existInvoicePayment");
        excludeUrl.add("/api/invoice/findpage");
        excludeUrl.add("/api/user/getuserinfo");
        excludeUrl.add("/api/customer/");
        excludeUrl.add("/api/customer/getall");
        excludeUrl.add("/api/customer/withuser");


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String url = ((HttpServletRequest) request).getRequestURI().toLowerCase();
            if (excludeUrl.stream().anyMatch(x -> url.equals(x.toLowerCase())) ||
                excludeContainsUrl.stream().anyMatch(x->url.startsWith(x.toLowerCase())))
            {
                chain.doFilter(request, response);
                return;
            }

            String requestTokenHeader = ((HttpServletRequest) request).getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
                throw new jwtTokenException("Request Token Header dose not set ! ");
            String token = requestTokenHeader.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (username == null)
                throw new jwtTokenException("userName not found");

            UserVM userVM = new UserVM(userService.getByUsername(username));
            if (!jwtTokenUtil.validateToken(token, userVM))
                throw new jwtTokenException("Token is not valid");
            chain.doFilter(request, response);

        } catch (jwtTokenException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized");
        }catch (ExpiredJwtException e){
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "Token is expired");
        }catch (Exception e){
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }
}
