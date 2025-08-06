package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.security;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos.CredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

/**
 * quando eu faço herança com essa classe, automaticamente o spring vai entender que esse filtro vai interceptar a requisição post la no end point /login que é um endpoint reservado para o spring security
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     *é a principal interface de estrategia para autenticação, se o principal(usuario e senha) de autenciação de entrada ele for valido e verificado o metodo que ele possui chamadado authenticate retorna uma intancia de authentication com um sinalizador de autenticado definido como verdade, se não, se o principal não for valido esse sinalizador vai retornar um valor null, ou seja ele não vai poder decidir para mim se o cara ta autenticado ou não
     */
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //convertar o post (endpoint) do /login em credenciais DTO
        try{
            CredentialsDTO credentials = new ObjectMapper().readValue(request.getInputStream(),CredentialsDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(),credentials.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return authentication;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * caso dê sucesso vai entrar nesse método
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userName = ((UserSS) authResult.getPrincipal()).getUsername();
        String token = this.jwtUtil.generateToken(userName);
        response.setHeader("acess-control-expose-headers", "Authorization");
        response.setHeader("Authorization", "Bearer " + token);
    }

    /**
     * se não obter o sucesso na autentiação, vai entrar nesse método aqui
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401
        response.setContentType("application/json");
        response.getWriter().append(json());
    }

    private CharSequence json() {
        long date = new Date().getTime();
        return "{"
                + "\"timestamp\":" + date + ", "
                + "\"status\":401, "
                + "\"error\":\"Não autorizado\""
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\":\"/login\"";
    }
}
