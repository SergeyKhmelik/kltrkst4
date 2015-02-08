package ua.nure.khmelik.SummaryTask4.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class EncodingFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);
    
    private String encoding;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	LOGGER.debug("Filter initialization starts");
	encoding = filterConfig.getInitParameter("encoding");
	LOGGER.trace("Encoding from web.xml --> " + encoding);
	LOGGER.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	LOGGER.debug("Filter starts");
	
	HttpServletRequest httpRequest = (HttpServletRequest)request;
	LOGGER.trace("Request uri --> " + httpRequest.getRequestURI());
	String requestEncoding = request.getCharacterEncoding();
	if (requestEncoding == null) {
		LOGGER.trace("Request encoding = null, set encoding --> " + encoding);
		request.setCharacterEncoding(encoding);
	}
	LOGGER.debug("Filter finished");		
	chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
	LOGGER.debug("Filter destroyed.");
    }

}
