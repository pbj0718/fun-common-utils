package com.fun.panda.transUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: panbojin
 * @date: 2023-06-07
 * @version: V1.0
 */
public class StringToJson {

    public static void main(String[] args) {
        String json = "{userId=fuz10,userName=付庄,balanceNo=EMS-jdsd-20230504162841475,prikey=1654040293372497922,batchid=EMS-jdsd-20230504162841475837,transnum=EMS-jdsd-20230504162841475,provincecode=10,vendornumber=101027357,vendorname=北京京东世纪信息技术有限公司,salestaxcode=91110302562134916R,purchasetaxcode=91110000710939135P,purchasetaxname=中国联合网络通信有限公司,salesrole=10,applycomid=6928,applycomname=联通（山东）产业互联网有限公司,applydeptid=692876559,applydeptname=联通（山东）产业互联网有限公司-财务部,applyseconddeptid=692876559,applyseconddeptname=联通（山东）产业互联网有限公司-财务部,applyuseremail=fuz10@chinaunicom.cn,applyusertel=17686808643,applydate=2023-05-0416:28:42,applyuserid=907610,applyusername=fuz10,invoicebusinesstype=11,reserved1=01,reserved2=0,reserved3=0,reserved4=null,reserved5=null,reserved6=null,reserved7=null,reserved8=null,reserved9=null,reserved10=null,reserved11=null,reserved12=null,reserved13=null,reserved14=null,reserved15=null,invoiceLineInfoItem=[{transnum=EMS-jdsd-20230504162841475,batchid=EMS-jdsd-20230504162841475837,invoiceprovincecode=69,orgcode=00692876559,orgname=联通（山东）产业互联网有限公司-财务部,invoicecode=1100224130,invoicenum=11489283,makeinvoicedate=2023-05-12,purchasename=联通（山东）产业互联网有限公司,taxpayerno=91370100MA3D98AL0X,salesnum=101027357,inputtax=1991.56,inputnotax=15319.65,inputtotaltax=17311.21,reserved1=0.13,reserved2=11,reserved3=商城物资,reserved4=null,reserved5=null,reserved6=null,reserved7=null,reserved8=null,reserved9=null,reserved10=null,reserved11=null,reserved12=null,reserved13=null,reserved14=null,reserved15=null},{transnum=EMS-jdsd-20230504162841475,batchid=EMS-jdsd-20230504162841475837,invoiceprovincecode=69,orgcode=00692876559,orgname=联通（山东）产业互联网有限公司-财务部,invoicecode=1100224130,invoicenum=11511442,makeinvoicedate=2023-05-15,purchasename=联通（山东）产业互联网有限公司,taxpayerno=91370100MA3D98AL0X,salesnum=101027357,inputtax=5041.31,inputnotax=38779.41,inputtotaltax=43820.72,reserved1=0.13,reserved2=11,reserved3=商城物资,reserved4=null,reserved5=null,reserved6=null,reserved7=null,reserved8=null,reserved9=null,reserved10=null,reserved11=null,reserved12=null,reserved13=null,reserved14=null,reserved15=null}]}";
        String jsonList = json.substring(json.indexOf("["), json.lastIndexOf("]"));
        System.out.println(getValue(json).toString());
        Map<String, Object> map = getValue(json);
        for (Map.Entry a : map.entrySet()) {
            System.out.println(a.getKey());
            System.out.println(a.getValue());
        }
    }

    public static Map<String, Object> getValue(String param) {
        Map<String, Object> map = new HashMap<>();
        String str = "";
        String key = "";
        Object value = "";
        char[] charList = param.toCharArray();
        boolean valueBegin = false;
        for (int i = 0; i < charList.length; i++) {
            char c = charList[i];
            if (c == '{') {
                if (valueBegin == true) {
                    value = getValue(param.substring(i, param.length()));
                    i = param.indexOf('}', i) + 1;
                    map.put(key, value);
                }
            } else if (c == '=') {
                valueBegin = true;
                key = str;
                str = "";
            } else if (c == ',') {
                valueBegin = false;
                value = str;
                str = "";
                map.put(key, value);
            } else if (c == '}') {
                if (str != "") {
                    value = str;
                }
                map.put(key, value);
                return map;
            } else if (c != ' ') {
                str += c;
            }
        }
        return map;
    }

}
