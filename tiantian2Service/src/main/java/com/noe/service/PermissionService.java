package com.noe.service;

import java.util.Set;

/**
 * @Author:nore
 */
public interface PermissionService {
    public Set<String> queryAllPermissionByUsername(String username);
}
