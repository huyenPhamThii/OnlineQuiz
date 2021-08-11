/**
 * Copyright(C) 2021, Pham Thi Huyen
 * J3.L.P0001
 * Online Quiz
 *
 * Record of change:
 * DATE          VERSION    AUTHOR      DESCRIPTION
 * 2021-07-23    1.0        HuyenPT     First Implement
 * 2021-07-24    2.0        HuyenPT     Fix header class, header method comment
 */
package filter;

import entity.Account;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class contain function of checking the user's access is right
 *
 * @author HuyenPT
 */
public class PageFillter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    /**
     * Used to initialize RoleFilter with no parameter
     */
    public PageFillter() {
    }

    /**
     * Use these functions for request and response filters before processing.
     */
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RoleFilter:DoBeforeProcessing");
        }
    }

    /**
     * Use these functions for request and response filters after processing.
     */
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("RoleFilter:DoAfterProcessing");
        }
    }

    /**
     * The doFilter method called by the container each time a request/response
     * pair is passed through the chain due to a client request for a resource
     * at the end of the chain.
     *
     * @param request The servlet request we are processing. It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
     * @param response The servlet response we are creating. It is a
     * <code> javax.servlet.http.HttpServletResponse</code>
     * @param chain The filter chain we are processing. The FilterChain passed
     * in to this method allows the Filter to pass on the request and response
     * to the next entity in the chain.
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("RoleFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        Throwable problem = null;

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletRespone = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        Account account = (Account) session.getAttribute("account");
        String url = httpServletRequest.getServletPath();

        if (account == null) {
            if (url.equals("/makeQuiz")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/login");
            } else if (url.equals("/takeQuiz")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/login");
            } else if (url.equals("/manageQuiz")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/login");
            } else if (httpServletRequest.getRequestURI().endsWith(".jsp")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/login");
            }
        } else if (account.getType() != 1) {
            if (url.equals("/makeQuiz")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/login");
            }else if (url.equals("/manageQuiz")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/login");
            } else if (httpServletRequest.getRequestURI().endsWith(".jsp")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/login");
            }
        } else if (account.getType() == 1) {
            if (httpServletRequest.getRequestURI().endsWith("MakeQuiz.jsp")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/makeQuiz");
            } else if (httpServletRequest.getRequestURI().endsWith("TakeQuiz.jsp")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/takeQuiz");
            } else if (httpServletRequest.getRequestURI().endsWith("ManageQuiz.jsp")) {
                httpServletRespone.sendRedirect(httpServletRequest.getContextPath() + "/manageQuiz");
            }
        }

        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("RoleFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("RoleFilter()");
        }
        StringBuffer sb = new StringBuffer("RoleFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
