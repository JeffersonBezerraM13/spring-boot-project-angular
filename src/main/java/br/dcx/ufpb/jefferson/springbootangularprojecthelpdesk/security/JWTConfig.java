package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

/**
 * Componente responsável pela geração de tokens JWT e fornecimento da chave secreta usada para assinatura.
 */
@Component
public class JWTConfig {

    /**
     * Tempo de expiração do token (em milissegundos), definido no application.properties.
     * Exemplo: 180000 (3 minutos)
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Chave secreta codificada em Base64, usada para assinar o token.
     * Deve ter no mínimo 512 bits para o algoritmo HS512.
     */
    @Value("${jwt.secret}")
    private String jwtSecretBase64;

    /**
     * Gera um token JWT com base no e-mail do usuário.
     *
     * @param email Identificador do usuário (geralmente o e-mail)
     * @return String contendo o token JWT gerado
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Define a informação principal (subject) do token: o e-mail do usuário
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Define a data de expiração
                .signWith(getSecretKey(), SignatureAlgorithm.HS512) // Assina o token com a chave segura
                .compact(); // Compacta o token (gera a string final do JWT)
    }

    /**
     * Decodifica a chave secreta em Base64 e retorna uma instância de SecretKey compatível com HS512.
     *
     * @return SecretKey segura e válida para uso com o JWT
     */
    public SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecretBase64);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
