import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, String> users;

    @Override
    public void init() throws ServletException {
        users = new HashMap<>();
        // Add some pre-built accounts
        users.put("admin", "123");
        users.put("user", "password");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Result</title>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container mt-5\">");

        if (username != null && password != null && users.containsKey(username) && users.get(username).equals(password)) {
            out.println("<div class=\"alert alert-success\">Name/Password match</div>");
        } else {
            out.println("<div class=\"alert alert-danger\">Name/Password does not match</div>");
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}