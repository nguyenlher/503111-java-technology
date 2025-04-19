package servlets;

import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String[] pathParts = pathInfo.split("/");

        if (pathParts.length != 2) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            Long productId = Long.parseLong(pathParts[1]);
            boolean deleted = productService.deleteProduct(productId);

            if (deleted) {
                request.getSession().setAttribute("flashMessage", "Product deleted successfully");
            } else {
                request.getSession().setAttribute("flashMessage", "Failed to delete product");
            }

            response.sendRedirect(request.getContextPath() + "/");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}