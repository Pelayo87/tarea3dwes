package com.pelayora.tarea3dwes.configuracion;

import jakarta.servlet.ServletException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Component
public class RedireccionUsuarioAutenticacionExitosa implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        DetallesUsuario userDetails = (DetallesUsuario) authentication.getPrincipal();
        String username = userDetails.getUsername();

        HttpSession session = request.getSession();
        session.setAttribute("nombreUsuario", username);
        session.setAttribute("id_Persona", userDetails.getIdPersona());
        session.setAttribute("id_Cliente", userDetails.getIdCliente());
        session.setAttribute("Usuario", userDetails);

        if ("admin".equalsIgnoreCase(username)) {
            response.sendRedirect(request.getContextPath() + "/inicio-admin");
        } else if (userDetails.getIdPersona() <= 0) { 
            response.sendRedirect(request.getContextPath() + "/inicio-cliente");
        } else {
            response.sendRedirect(request.getContextPath() + "/inicio-personal");
        }
    }
}

