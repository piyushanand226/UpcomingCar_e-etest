package com.renault.upcomingcar.api.dto;

import java.io.Serializable;
import java.util.Date;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private final String username;
    private final Date expiration;

    public JwtResponse(String jwtToken, String username, Date expiration) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.expiration = expiration;
    }

    public String getJwtToken() { return jwtToken; }
    public String getUsername() { return username; }
    public Date getExpiration() { return expiration; }
}
