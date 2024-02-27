package de.sachsenCompany.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import de.sachsenCompany.user.domain.User;
import de.sachsenCompany.user.service.UserService;

@Component
public class HeaderInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Überprüfe, ob das Modell und die Ansicht vorhanden sind
		if (modelAndView == null || modelAndView.getViewName() == null) {
			return;
		}

		// Überprüfe, ob der Nutzer authentifiziert ist
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return;
		}
		// Überprüfe, ob der Nutzer in der Datenbank existiert
		User user = userService.findByLoginName(authentication.getName());
		if (user == null) {
			return;
		}
		// Name für's Anzeigen in der headerline
		modelAndView.getModelMap().addAttribute("name", user.getName());
		// Adminrechte, hier da es in der headerline benutzt wird
		authentication.getAuthorities().stream()
				.forEach(a -> modelAndView.getModelMap().addAttribute(a.getAuthority(), true));
	}
}
