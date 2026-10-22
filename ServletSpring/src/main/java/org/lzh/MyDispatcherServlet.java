package org.lzh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MyDispatcherServlet {

    @RequestMapping("/")
    @ResponseBody
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the context path of the application
        String contextPath = request.getContextPath();

        System.out.println(contextPath);
        // Remove the context path prefix from the request URI
        String requestURI = request.getRequestURI().substring(contextPath.length());

        if (requestURI.startsWith("/dispatch/j2ee")) {
            // Forward request to MyServlet
            request.getRequestDispatcher("/j2ee").forward(request, response);
        } else if (requestURI.startsWith("/dispatch/jakarta")) {
            // Forward request to MyServletJakarta
            request.getRequestDispatcher("/jakarta").forward(request, response);
        } else {
            // Handle unknown request or return error
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
