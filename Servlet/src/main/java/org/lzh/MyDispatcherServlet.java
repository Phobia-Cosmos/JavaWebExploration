package org.lzh;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyDispatcherServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the context path of the application
        String contextPath = request.getContextPath();

//        System.out.println(contextPath);
//        System.out.println(request.getRequestURI());

        // Remove the context path prefix from the request URI
        String requestURI = request.getRequestURI().substring(contextPath.length());

//        System.out.println(requestURI);
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
