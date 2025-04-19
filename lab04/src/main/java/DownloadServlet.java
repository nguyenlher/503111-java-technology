import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    private static final String RESOURCES_BASE = "/resources/files/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName = request.getParameter("file");

        if (fileName == null || fileName.isEmpty()) {
            response.setContentType("text/plain");
            response.getWriter().write("File not found");
            return;
        }

        String speedParam = request.getParameter("speed");
        int speedLimit = -1;

        if (speedParam != null && !speedParam.isEmpty()) {
            try {
                speedLimit = Integer.parseInt(speedParam);
                if (speedLimit <= 0) {
                    speedLimit = -1;
                }
            } catch (NumberFormatException e) {
            }
        }

        String filePath = RESOURCES_BASE + fileName;
        try (InputStream in = getServletContext().getResourceAsStream(filePath)) {

            if (in == null) {
                response.setContentType("text/plain");
                response.getWriter().write("The requested file '" + fileName + "' is not available on the server.");
                return;
            }

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            try (OutputStream out = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                long startTime = System.currentTimeMillis();
                long bytesWritten = 0;

                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);

                    if (speedLimit > 0) {
                        bytesWritten += bytesRead;
                        double kilobytesWritten = bytesWritten / 1024.0;
                        double elapsedTimeInSeconds = (System.currentTimeMillis() - startTime) / 1000.0;
                        double currentSpeed = kilobytesWritten / elapsedTimeInSeconds;
                        if (currentSpeed > speedLimit) {
                            double targetTimeInSeconds = kilobytesWritten / speedLimit;
                            long sleepTime = (long)((targetTimeInSeconds - elapsedTimeInSeconds) * 1000);

                            if (sleepTime > 0) {
                                try {
                                    Thread.sleep(sleepTime);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}