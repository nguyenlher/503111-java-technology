package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getServletPath();
        HttpSession session = request.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isLoginPage = path.equals("/login") || path.equals("/login.jsp");
        boolean isRegisterPage = path.equals("/register") || path.equals("/register.jsp");
        boolean isStaticResource = path.matches(".*\\.(css|js|png|jpg|gif)$");

        if (isLoggedIn) {
            // User is logged in
            if (isLoginPage || isRegisterPage) {
                // Redirect to home if trying to access login or register
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
        } else {
            // User is not logged in
            if (!isLoginPage && !isRegisterPage && !isStaticResource) {
                // Redirect to login for all protected pages
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}