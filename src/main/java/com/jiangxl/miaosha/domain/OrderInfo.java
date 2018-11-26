package com.jiangxl.miaosha.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangxl
 * @since 2018-10-22
 */
@TableName("order_info")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Long id;
	@TableField("USER_ID")
	private Long userId;
	@TableField("GOODS_ID")
	private Long goodsId;
	@TableField("DELIVERY_ADDR_ID")
	private Long deliveryAddrId;
	@TableField("GOODS_NAME")
	private String goodsName;
	@TableField("GOODS_COUNT")
	private Integer goodsCount;
	@TableField("GOODS_PRICE")
	private Double goodsPrice;
	@TableField("ORDER_CHANNEL")
	private String orderChannel;
	@TableField("STATUS")
	private String status;
	@TableField("CREATE_DATE")
	private Date createDate;
	@TableField("PAY_DATE")
	private Date payDate;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getDeliveryAddrId() {
		return deliveryAddrId;
	}

	public void setDeliveryAddrId(Long deliveryAddrId) {
		this.deliveryAddrId = deliveryAddrId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
