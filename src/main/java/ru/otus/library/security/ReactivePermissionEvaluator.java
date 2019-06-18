package ru.otus.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.otus.library.rest.RestBookController;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReactivePermissionEvaluator {
    public boolean hasPermission(Object targetDomainObject, Object permission) {
        if ( (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

        return hasPrivilege(targetType, permission.toString().toUpperCase());
    }

    public boolean hasPermission(Serializable targetId, String targetType, Object permission) {

        if ( (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(targetType.toUpperCase(),
                permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(String targetType, String permission) {

        Mono<User> principal = ReactiveSecurityContextHolder.getContext()
                .switchIfEmpty(Mono.error(new IllegalStateException("ReactiveSecurityContext is empty")))
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .cast(User.class);
        User user = (User) principal.subscribe();

        Set<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream().collect(Collectors.toSet());*/
        for (GrantedAuthority grantedAuth : grantedAuthorities) {
            if (grantedAuth.getAuthority().startsWith(targetType)) {
                if (grantedAuth.getAuthority().contains(permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Principal getPrincipal (Principal principal) {
        return principal;
    }

    public boolean test (String s) {
        return true;
    }
}
