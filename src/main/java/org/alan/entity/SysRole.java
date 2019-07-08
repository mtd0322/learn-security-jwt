package org.alan.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * @program: learn-security-jwt
 * @ClassName: SysRole
 * @description:
 * @author: AlanMa
 * @create: 2019-07-08 14:23
 */

@Entity
@Table(name = "t_role")
@Data
public class SysRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String remark;

    @ManyToMany(mappedBy="authorities")
    @JSONField(serialize = false)
    private List<SysUser> sysUsers;

    @Override
    public String getAuthority() {
        return name;
    }
}