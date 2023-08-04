package cn.einblatt.mryce.pojo;

import cn.einblatt.mryce.commons.AbstractBaseDomain;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author create by einblatt
 * @Database table:app_user
 */
@Data
@Table(name = "app_user")
public class AppUser extends AbstractBaseDomain {
    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 渠道号
     */
    @Column(name = "channel_num")
    private String channelNum;

    /**
     * 渠道id
     */
    @Column(name = "channel_id")
    private String channelId;

    /**
     * 注册ip
     */
    @Column(name = "register_ip")
    private String registerIp;

    /**
     * 账号状态(0:禁用 1:启用)
     */
    @Column(name = "account_status")
    private String accountStatus;

    /**
     * 登录状态(0:未登录 1:已登录)
     */
    @Column(name = "login_status")
    private String loginStatus;

    /**
     * 银行名称
     */
    @Column(name = "branch_name")
    private String branchName;

    /**
     * 银行卡号
     */
    @Column(name = "bank_account_no")
    private String bankAccountNo;

    /**
     * 资料认证状态(0:未认证 1:已认证 2:审核中 3:开始认证)
     */
    @Column(name = "info_auth_status")
    private String infoAuthStatus;

    /**
     * 用户最后登录时间
     */
    @Column(name = "last_login_time")
    private String lastLoginTime;

    /**
     * 次数
     */
    @Column(name = "frequency")
    private Integer frequency;
}