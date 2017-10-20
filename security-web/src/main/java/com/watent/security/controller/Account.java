package com.watent.security.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Account
 * <p>
 * Created by Atom on 2017/7/17.
 */
@Data
public class Account implements Serializable {
    /**
     * ID
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 权限
     */
    private String authority;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
