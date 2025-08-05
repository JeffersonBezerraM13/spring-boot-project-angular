package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) //setSubject define o valor de reevindicações do token
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //momento atual mais 3min da variavel
                .signWith(SignatureAlgorithm.RS512, secret.getBytes()) //pegando o array de bytes
                .compact(); //compacta o corpo do JWT deixando a API mais perfomatica
    }
}
