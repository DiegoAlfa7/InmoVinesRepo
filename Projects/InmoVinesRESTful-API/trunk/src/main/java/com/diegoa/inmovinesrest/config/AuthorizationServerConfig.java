package com.diegoa.inmovinesrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // Define el id de la aplicacion cliente que esta autorizada a autenticarse
    @Value("${security.jwt.client-id}")
    private String clientId;

    // Es la contraseña de la aplicacion clientes
    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    // Le pasamos el grantType porque no se asigna por defecto
    @Value("${security.jwt.grant-type}")
    private String grantType;

    // Define el nivel de acceso al recurso
    @Value("${security.jwt.scope-read}")
    private String scopeRead;

    // Define el nivel de acceso al recurso
    @Value("${security.jwt.scope-write}")
    private String scopeWrite;


    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    // Interfaz persistente para tokens OAuth2 (limita el acceso a un servicio HTTP a terceros)
    @Autowired
    private TokenStore tokenStore;

    // Clase helper que traduce los token JWT y la informacion de la autenticaciñon OAuth
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    // Procesa una peticion de autenticación
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Setea la configuración con los datos y permisos proporcionados
     * @param configurer
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer
                .inMemory()
                .withClient(clientId)
                .secret(clientSecret)
                .authorizedGrantTypes(grantType)
                .scopes(scopeRead, scopeWrite)
                .resourceIds(resourceIds);
    }

    /**
     * Configura las propiedades y la funcionalidad de los end points del servidor de autorizacion
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // Permite encadenar multiplos tipos de requests que contienen informacion diferente
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager);
    }

}
