package com.fun.panda.transUtil;


import com.fun.panda.transUtil.entity.InvoiceLineInfoItem;
import com.fun.panda.transUtil.entity.TAXImportVatInvoiceInfoSrvPortRequests;
import net.sf.json.JSONObject;

/**
 * @author: panbojin
 * @date: 2023-06-07
 * @version: V1.0
 */
public class POJOtoJSON {

    public static void main(String[] args) {
        //实体转Json
        //JSONObject json = JSONObject.fromObject(new TAXImportVatInvoiceInfoSrvPortRequests());
        JSONObject json = JSONObject.fromObject(new InvoiceLineInfoItem());
        System.out.println(json);
    }



}
