package org.alan.repository;

import org.alan.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: learn-security-jwt
 * @ClassName: SysUserRepository
 * @description:
 * @author: AlanMa
 * @create: 2019-07-08 14:42
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Long>{

    SysUser findSysUserByUsername(String username);
}