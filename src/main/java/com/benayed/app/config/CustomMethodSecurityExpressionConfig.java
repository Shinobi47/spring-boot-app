package com.benayed.app.config;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomMethodSecurityExpressionConfig extends GlobalMethodSecurityConfiguration {
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        CustomMethodSecurityExpressionHandler expressionHandler = 
          new CustomMethodSecurityExpressionHandler();
//        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return expressionHandler;
    }
    
    public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
    	
        @Override
        protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
          Authentication authentication, MethodInvocation invocation) {
            CustomMethodSecurityExpressionRoot root = 
              new CustomMethodSecurityExpressionRoot(authentication);
            root.setPermissionEvaluator(getPermissionEvaluator());
            root.setTrustResolver(this.getTrustResolver());
            root.setRoleHierarchy(getRoleHierarchy());
            return root;
        }
    	
    }
    
    public class CustomMethodSecurityExpressionRoot 
    extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
   
      public CustomMethodSecurityExpressionRoot(Authentication authentication) {
          super(authentication);
      }
   
      public boolean isTrue() {
//        User user = ((MyUserPrincipal) this.getPrincipal()).getUser();
//        return user.getOrganization().getId().longValue() == OrganizationId.longValue();
  	  System.out.println( this.getPrincipal());
  	  return true;
  	  
    }
      
      public boolean isFalse() {
  	  System.out.println( this.getPrincipal());
  	  return false;
  	  
    }

	@Override
	public void setFilterObject(Object filterObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getFilterObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getReturnObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getThis() {
		// TODO Auto-generated method stub
		return null;
	}
   
  }
}
