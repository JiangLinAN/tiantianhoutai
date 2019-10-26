package com.noe.realm;

import com.noe.dao.PermissionDAO;
import com.noe.pojo.User;
import com.noe.service.PermissionService;
import com.noe.service.RoleService;
import com.noe.service.UserService;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author:nore
 */
@Setter
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("查询授权");
        //获取用户名
        String username = (String) principals.getPrimaryPrincipal();
        //根据用户名查询权限
        Set<String> role = roleService.queryAllRolenameByUsername(username);
        Set<String> permission = permissionService.queryAllPermissionByUsername(username);
        //封装对象
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo(role);
        simpleAuthorizationInfo.setStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("在realm中查询身份");
        String username = (String) token.getPrincipal();
        //查询用户信息,查询错误又异常器捕获报错
        User user = userService.queryUserByname(username);
        //判断用户是否为空
        if (user==null){
            return null;
        }
        return new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                this.getName()
        );
    }
}
