package cz.muni.fi.pa165.airportmanager.service.security;

import cz.muni.fi.pa165.airportmanager.api.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.api.dto.UserDTO;
import cz.muni.fi.pa165.airportmanager.api.facades.UserFacade;
import cz.muni.fi.pa165.airportmanager.service.exceptions.AirportManagerDataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
@WebFilter(urlPatterns = {"/*"})
public class ProtectFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("ProtectFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String auth = request.getHeader("Authorization");
        if (auth == null) {
            response401(response);
            return;
        }
        String[] creds = parseAuthHeader(auth);
        String login = creds[0];
        String password = creds[1];

        //get Spring context and UserFacade from it
        UserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(servletRequest.getServletContext()).getBean(UserFacade.class);

        log.info("write");
        log.info(userFacade.getAllUsers().toString());

        UserDTO matchingUser;
        try {
            matchingUser = userFacade.getUserByLogin(login);
        } catch (AirportManagerDataAccessException e) {
            matchingUser = null;
        }
        if (matchingUser == null) {
            log.warn("no user with login {}", login);
            response401(response);
            return;
        }
        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setLogin(matchingUser.getLogin());
        userAuthenticateDTO.setPassword(password);
        if (!userFacade.isAdmin(matchingUser)) {
            log.warn("user not admin {}", matchingUser);
            response401(response);
            return;
        }
        if (!userFacade.authenticate(userAuthenticateDTO)) {
            log.warn("wrong credentials: user={} password={}", creds[0], creds[1]);
            response401(response);
            return;
        }
        request.setAttribute("authenticatedUser", matchingUser);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private String[] parseAuthHeader(String auth) {
        return new String(Base64.getDecoder().decode(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type email and password\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1> Go away ...</body></html>");
    }

}
