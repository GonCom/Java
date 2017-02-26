package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SessionTimeOutFilter
 */
@WebFilter("*.xhtml")
public class SessionTimeOutFilter implements Filter {

	/**
	 * Default constructor.
	 */

	private String timeoutPage = "faces/timeout.xhtml";

	public SessionTimeOutFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if ((request instanceof HttpServletRequest)
				&& (response instanceof HttpServletResponse)) {
			System.out.println("Entrando en el filtro.");

			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			// is session expire control required for this request?

			if (isSessionControlRequiredForThisResource(httpServletRequest)) {

				// is session invalid?

				if (isSessionInvalid(httpServletRequest)) {

					String timeoutUrl = httpServletRequest.getContextPath()
							+ "/" + getTimeoutPage();

					System.out
							.println("Tiempo de inactividad agotado. Redireccionando a la página : "
									+ timeoutUrl);

					httpServletResponse.sendRedirect(timeoutUrl);

					return;

				}

			}

		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out .println("Entrando en el método init del filtro.");
	}

	private boolean isSessionControlRequiredForThisResource(
			HttpServletRequest httpServletRequest) {

		String requestPath = httpServletRequest.getRequestURI();

		boolean controlRequired = true;
		if (requestPath.contains(getTimeoutPage()))
			controlRequired = false;
		return controlRequired;
	}

	private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
		
		System.out.println("Entrando en issessioninvallid");
		System.out.println(httpServletRequest.getRequestedSessionId());
		boolean iRSIV=httpServletRequest.isRequestedSessionIdValid();
		if(iRSIV)
		   System.out.println("iRSIV es true");
		else
			System.out.println("iRSIV es false");

		boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null)

				&& !httpServletRequest.isRequestedSessionIdValid();

		return sessionInValid;

	}

	public String getTimeoutPage() {

		return timeoutPage;

	}

	public void setTimeoutPage(String timeoutPage) {

		this.timeoutPage = timeoutPage;

	}

}
