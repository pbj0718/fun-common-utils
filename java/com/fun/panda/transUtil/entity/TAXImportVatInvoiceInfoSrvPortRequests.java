package com.fun.panda.transUtil.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: panbojin
 * @date: 2023-06-07
 * @version: V1.0
 */
@Data
public class TAXImportVatInvoiceInfoSrvPortRequests {

    private String userId;
    private String userName;
    private String balanceNo;
    private String prikey;
    private String batchid;
    private String transnum;
    private String provincecode;
    private String vendornumber;
    private String vendorname;
    private String salestaxcode;
    private String purchasetaxcode;
    private String purchasetaxname;
    private String salesrole;
    private BigDecimal applycomid;
    private String applycomname;
    private BigDecimal applydeptid;
    private String applydeptname;
    private BigDecimal applyseconddeptid;
    private String applyseconddeptname;
    private String applyuseremail;
    private String applyusertel;
    private String applydate;
    private BigDecimal applyuserid;
    private String applyusername;
    private String invoicebusinesstype;
    private String reserved1;
    private String reserved2;
    private String reserved3;
    private String reserved4;
    private String reserved5;
    private String reserved6;
    private String reserved7;
    private String reserved8;
    private String reserved9;
    private String reserved10;
    private String reserved11;
    private String reserved12;
    private String reserved13;
    private String reserved14;
    private String reserved15;
    private List<InvoiceLineInfoItem> invoiceLineInfoItem;

}
