package servlets;

import models.Product;
import models.User;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all products
        List<Product> products = productService.getAllProducts();
        request.setAttribute("products", products);

        // Get flash message if any
        Object flashMessage = request.getSession().getAttribute("flashMessage");
        if (flashMessage != null) {
            request.setAttribute("flashMessage", flashMessage);
            request.getSession().removeAttribute("flashMessage");
        }

        // Get user information
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        // Validate input
        if (name == null || name.trim().isEmpty()) {
            request.getSession().setAttribute("flashMessage", "Please enter product name");
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("flashMessage", "Please enter a valid price");
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // Add product
        boolean added = productService.addProduct(name, price);

        if (added) {
            request.getSession().setAttribute("flashMessage", "Product added successfully");
        } else {
            request.getSession().setAttribute("flashMessage", "Failed to add product");
        }

        response.sendRedirect(request.getContextPath() + "/");
    }
}