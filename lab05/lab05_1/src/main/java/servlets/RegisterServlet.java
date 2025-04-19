package servlets;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is already logged in
        if (request.getSession(false) != null && request.getSession().getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // Check for flash message
        Object flashMessage = request.getSession().getAttribute("flashMessage");
        if (flashMessage != null) {
            request.setAttribute("flashMessage", flashMessage);
            request.getSession().removeAttribute("flashMessage");
        }

        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName = request.getParameter("fullName");

        // Validate input
        if (username == null || username.isEmpty() ||
                email == null || email.isEmpty() ||
                password == null || password.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty() ||
                fullName == null || fullName.isEmpty()) {

            request.getSession().setAttribute("flashMessage", "All fields are required");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        // Validate password length
        if (password.length() < 6) {
            request.getSession().setAttribute("flashMessage", "Password must have at least 6 characters");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        // Validate password match
        if (!password.equals(confirmPassword)) {
            request.getSession().setAttribute("flashMessage", "Passwords do not match");
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        // Register user
        boolean registered = userService.registerUser(username, password, fullName, email);

        if (registered) {
            // Registration successful, redirect to login
            request.getSession().setAttribute("flashMessage", "Registration successful. Please login.");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // Registration failed
            request.getSession().setAttribute("flashMessage", "Username or email already exists");
            response.sendRedirect(request.getContextPath() + "/register");
        }
    }
}