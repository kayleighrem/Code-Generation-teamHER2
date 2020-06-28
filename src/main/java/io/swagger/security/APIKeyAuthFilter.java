//package io.swagger.security;
//
//import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class APIKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {
//
//    private String headerName;
//
//    public APIKeyAuthFilter(String headerName) {
//        this.headerName = headerName;
//    }
//
//    @Override
//    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
//        return httpServletRequest.getHeader(headerName);
//    }
//
//    @Override
//    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest)
//    { return "N/A"; }
//
//}
