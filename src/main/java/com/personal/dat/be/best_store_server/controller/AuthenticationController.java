package com.personal.dat.be.best_store_server.controller;

import com.nimbusds.jose.JOSEException;
import com.personal.dat.be.best_store_server.dto.request.ApiRespronse;
import com.personal.dat.be.best_store_server.dto.request.AuthenticationRequest;
import com.personal.dat.be.best_store_server.dto.request.IntrospectRequest;
import com.personal.dat.be.best_store_server.dto.response.AuthenticationResponse;
import com.personal.dat.be.best_store_server.dto.response.IntrospectResponse;
import com.personal.dat.be.best_store_server.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    private final RestClient.Builder builder;

    @PostMapping("/log-in")
    ApiRespronse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiRespronse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiRespronse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiRespronse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
