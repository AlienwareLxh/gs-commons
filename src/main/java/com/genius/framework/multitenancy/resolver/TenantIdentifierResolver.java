package com.genius.framework.multitenancy.resolver;

import com.genius.framework.multitenancy.constants.TenantConstants;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes != null){
            HttpServletRequest httpServletRequest = requestAttributes.getRequest();
            String tenantId = httpServletRequest.getHeader(TenantConstants.REQUEST_HEADER_TENANT_FLAG);
            if(!StringUtils.isEmpty(tenantId)) return tenantId;
            return TenantConstants.DEFAULT_TENANT_ID;
        }
        return TenantConstants.DEFAULT_TENANT_ID;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
