package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
import service.UserService;

public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Check if user is already logged in
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }

            // Check for saved cookies
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String savedUsername = null;
                String savedPassword = null;

                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        savedUsername = cookie.getValue();
                    } else if ("password".equals(cookie.getName())) {
                        savedPassword = cookie.getValue();
                    }
                }

                if (savedUsername != null && savedPassword != null) {
                    request.setAttribute("savedUsername", savedUsername);
                    request.setAttribute("savedPassword", savedPassword);
                    request.setAttribute("rememberChecked", "checked");
                }
            }

            // Check for flash message
            Object flashMessage = request.getSession().getAttribute("flashMessage");
            if (flashMessage != null) {
                request.setAttribute("flashMessage", flashMessage);
                request.getSession().removeAttribute("flashMessage");
            }

            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");

            // Validate input
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                request.getSession().setAttribute("flashMessage", "Please enter both username and password");
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // Authenticate user
            if (userService.validateLogin(username, password)) {
                // Set session
                HttpSession session = request.getSession();
                User user = userService.findByUsername(username);
                session.setAttribute("user", user);

                // Set cookies if remember is checked
                if ("on".equals(remember)) {
                    Cookie usernameCookie = new Cookie("username", username);
                    Cookie passwordCookie = new Cookie("password", password);

                    // Set cookies to expire in 30 days
                    int maxAge = 30 * 24 * 60 * 60; // 30 days in seconds
                    usernameCookie.setMaxAge(maxAge);
                    passwordCookie.setMaxAge(maxAge);

                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                } else {
                    // Clear any existing cookies
                    Cookie usernameCookie = new Cookie("username", "");
                    Cookie passwordCookie = new Cookie("password", "");
                    usernameCookie.setMaxAge(0);
                    passwordCookie.setMaxAge(0);

                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                }

                // Redirect to home page
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                // Authentication failed
                request.getSession().setAttribute("flashMessage", "Invalid username or password");
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        }
    }
}