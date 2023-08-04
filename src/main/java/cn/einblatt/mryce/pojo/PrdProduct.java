package cn.einblatt.mryce.pojo;

import cn.einblatt.mryce.commons.AbstractBaseDomain;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author create by einblatt
 * @Database table:prd_product
 */
@Data
@Table(name = "prd_product")
public class PrdProduct extends AbstractBaseDomain {

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 产品图片
     */
    @Column(name = "product_logo")
    private String productLogo;

    /**
     * 周期(天)
     */
    @Column(name = "cycle_")
    private Integer cycle;

    /**
     * 日产
     */
    @Column(name = "daily_product")
    private String dailyProduct;

    /**
     * 排序
     */
    @Column(name = "sort_val")
    private Integer sortVal;

    /**
     * 兑换限制
     */
    @Column(name = "exchange_limit")
    private String exchangeLimit;

    /**
     * 金额
     */
    @Column(name = "amount_")
    private BigDecimal amount;

    /**
     * 产品说明
     */
    @Column(name = "product_desc")
    private String productDesc;

    /**
     * 在线状态(0:下线   1:在线)
     */
    @Column(name = "status_")
    private String status;
}