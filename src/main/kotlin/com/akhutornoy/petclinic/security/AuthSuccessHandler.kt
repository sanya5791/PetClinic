package com.akhutornoy.petclinic.security

import com.akhutornoy.petclinic.controller.HostsController
import com.akhutornoy.petclinic.controller.PetsController
import com.akhutornoy.petclinic.domain.db.Role
import org.springframework.security.core.Authentication
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthSuccessHandler(
        private val redirectStrategy: RedirectStrategy
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authentication: Authentication) {

        handle(request, response, authentication)
        clearAuthenticationAttributes(request)
    }

    @Throws(IOException::class)
    private fun handle(request: HttpServletRequest,
                         response: HttpServletResponse, authentication: Authentication) {
        val targetUrl = determineTargetUrl(authentication)

        if (response.isCommitted) {
            println("Response has already been committed. Unable to redirect to $targetUrl")
            return
        }

        redirectStrategy.sendRedirect(request, response, targetUrl)
    }

    private fun determineTargetUrl(authentication: Authentication): String {
        var isUser = false
        var isAdmin = false

        val authorities = authentication.authorities
        for (grantedAuthority in authorities) {
            when (grantedAuthority.authority) {
                Role.ADMIN.withPrefixROLE() -> isAdmin = true
                Role.USER.withPrefixROLE() -> isUser = true
                else -> IllegalArgumentException("Not a case")
            }
        }
        return when {
            isAdmin -> HostsController.END_POINT
            isUser -> PetsController.END_POINT
            else -> throw IllegalStateException("Not a case")
        }
    }

    private fun clearAuthenticationAttributes(request: HttpServletRequest) {
        val session = request.getSession(false) ?: return
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)
    }

}