package com.vkstech.resourceserver.exceptionHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vkstech.resourceserver.constants.ResponseMessages;
import com.vkstech.resourceserver.utils.ResponseObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        ResponseObject response = new ResponseObject(ResponseMessages.ACCESS_DENIED);
        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, response);
        out.flush();
    }
}