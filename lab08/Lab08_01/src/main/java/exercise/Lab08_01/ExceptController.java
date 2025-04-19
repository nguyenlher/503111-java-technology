package exercise.Lab08_01;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Controller
public class ExceptController implements ErrorController {

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage(HttpServletRequest httpRequest, Model model) {
        String errorMsg = new String();
        int errorCode = getErrorCode(httpRequest);
        switch (errorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
            default: {
                errorMsg = "Error";
                break;
            }
        }
        model.addAttribute("errorMsg", errorMsg);
        return "errorPage";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        Object errorCode = httpRequest.getAttribute(ERROR_STATUS_CODE);
        return errorCode == null ? 0 : (Integer)errorCode;
    }
}
