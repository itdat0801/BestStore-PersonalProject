package com.personal.dat.be.best_store_server.service;

import com.nimbusds.jose.JOSEException;
import com.personal.dat.be.best_store_server.dto.request.AuthenticationRequest;
import com.personal.dat.be.best_store_server.dto.request.IntrospectRequest;
import com.personal.dat.be.best_store_server.dto.request.LogoutRequest;
import com.personal.dat.be.best_store_server.dto.response.AuthenticationResponse;
import com.personal.dat.be.best_store_server.dto.response.IntrospectResponse;

import java.text.ParseException;
import java.util.Objects;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
}
