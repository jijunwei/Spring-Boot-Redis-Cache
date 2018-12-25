package com.springboot.model.pay;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @version 创建时间：2018年5月16日 上午8:58:24
 * 拼单支付状态实体
 */
@Data
@Table(name = "pay_status")
public class PayStatus implements Serializable {

    /** **/
    private static final long serialVersionUID = -8014413597588737701L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//主键

    @Column(name = "product_id")
    private Long productId;//产品id

    @Column(name = "order_id")
    private Long orderId;//订单编号

    @Column(name = "account_id")
    private Long accountId;//账户id

    @Column(name = "investment_amount")
    private BigDecimal investmentAmount;//投资金额

    @Column(name = "pay_status")
    private Integer payStatus;//0:支付中 1:支付成功 2:支付失败

    @Column(name = "pay_type")
    private Integer payType;//0:发起拼单 1:参与拼单

    @Column(name = "card_id")
    private String cardId;    //卡号

    @Column(name = "requestno")
    private String requestno;    //交易流水号

    @Column(name = "create_time")
    private Date createTime;//创建时间

    @Column(name = "update_time")
    private Date updateTime;//更新时间

    @Column(name = "is_deleted")
    private Integer isDeleted;//是否删除

    @Column(name = "tx_date")
    String txDate;

    @Column(name = "trace_no")
    String traceNo;

}
