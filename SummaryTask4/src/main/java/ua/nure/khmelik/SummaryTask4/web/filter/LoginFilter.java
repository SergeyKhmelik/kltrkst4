package ua.nure.khmelik.SummaryTask4.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LoginFilter implements Filter {

	private static final String LOGIN_PAGE = "login";
	private static final String CSS_JPG_PNG_GIF_JS = ".*(css|jpg|png|gif|js)";
	private static final String USER = "user";
	private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);

	private String excludePatterns;

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		this.excludePatterns = cfg.getInitParameter("excludePatterns");
		LOGGER.debug("LoginFilter initialized.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (validUrl(req)) {
			chain.doFilter(request, response);
		} else {
			LOGGER.debug("No user logged in " + req.getRequestURI());
			resp.sendRedirect(LOGIN_PAGE);
		}
	}

	@Override
	public void destroy() {
		LOGGER.debug("LoginFilter instance destroyed.");
	}

	private boolean validUrl(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String requestURL = req.getRequestURL().toString();
		return matchesURL(requestURL) || session.getAttribute(USER) != null;
	}

	private boolean matchesURL(String requestURL) {
		return (matchesExcludePatterns(requestURL) || matchesUploadingComponents(requestURL));
	}

	private boolean matchesExcludePatterns(String requestURL) {
		return requestURL.contains(excludePatterns);
	}

	private boolean matchesUploadingComponents(String requestURL) {
		return requestURL.matches(CSS_JPG_PNG_GIF_JS);
	}

}
