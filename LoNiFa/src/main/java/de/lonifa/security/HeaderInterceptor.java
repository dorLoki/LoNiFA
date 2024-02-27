package de.lonifa.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import de.lonifa.user.domain.User;
import de.lonifa.user.service.UserService;

@Component
public class HeaderInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;

	@Override
	public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull Object handler, @Nullable ModelAndView modelAndView) throws Exception {
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
		String loginName = authentication.getName();
		if (loginName == null) {
			return;
		}
		User user = userService.findByLoginName(loginName);
		if (user == null) {
			return;
		}
		// Name für's Anzeigen in der headerline
		modelAndView.getModelMap().addAttribute("name", user.getName());
		// Adminrechte, hier da es in der headerline benutzt wird
		authentication.getAuthorities().stream().forEach(a -> {
			String authString = a.getAuthority();
			if (authString != null) {
				modelAndView.getModelMap().addAttribute(authString, true);
			}
		});
	}
}