package com.fun.panda.transUtil;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.fun.panda.transUtil.entity.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * json树解析工具类
 * @date: 2023-06-15
 * @version: V1.0
 */
@Slf4j
@Component
public class JsonParserUtil {

    /**
     * 根据jsonpath获取json文本对应节点路径信息
     * @param json
     * @param jsonPath
     * @return
     */
    private Map<String, Object> getJsonPathValue(String json, String jsonPath) {
        Map<String, Object> result = new HashMap<>();
        try {
            Object value = JsonPath.read(json, jsonPath, new Predicate[0]);
            if (value instanceof Integer) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "number");
            } else if (value instanceof String) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "string");
            } else if (value instanceof Boolean) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "boolean");
            } else if (value instanceof ArrayList) {
                if (ObjectUtil.isNotEmpty(value)) {
                    result.put(jsonPath, value.toString());
                    result.put(jsonPath + "#Type", "array[Object]");
                } else {
                    result.put(jsonPath, "");
                    result.put(jsonPath + "#Type", "array[Object]");
                }
            } else if (value instanceof LinkedHashMap) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "object");
            } else if (value instanceof Float) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "number");
            } else if (value instanceof Double) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "number");
            } else {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "Object");
            }
        } catch (Exception e) {
            log.error("path not found!");
        }
        return result;
    }

    /**
     * 根据jsonpath获取json文本对应节点路径信息 - 文本
     * @param json
     * @param jsonPath
     * @return
     */
    private Map<String, Object> getJsonPathValueText(String json, String jsonPath) {
        Map<String, Object> result = new HashMap<>();
        try {
            Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
            Object value = JsonPath.using(conf).parse(json).read(jsonPath);
            if (value instanceof Integer) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "数值");
            } else if (value instanceof String) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "文本");
            } else if (value instanceof Boolean) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "布尔");
            } else if (value instanceof ArrayList) {
                if (ObjectUtil.isNotEmpty(value)) {
                    result.put(jsonPath, value.toString());
                    result.put(jsonPath + "#Type", "数组");
                } else {
                    result.put(jsonPath, "");
                    result.put(jsonPath + "#Type", "数组");
                }
            } else if (value instanceof LinkedHashMap) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "对象");
            } else if (value instanceof Float) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "数值");
            } else if (value instanceof Double) {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "数值");
            } else if (null == value) {
                result.put(jsonPath, "");
                result.put(jsonPath + "#Type", "文本");
            } else {
                result.put(jsonPath, value.toString());
                result.put(jsonPath + "#Type", "对象");
            }
        } catch (Exception e) {
            log.error("path not found!");
        }
        return result;
    }

    /**
     * 获取并组装json树信息
     * @param jsonObject
     * @return
     */
    public List<JsonParser> getJsonPath(JSONObject jsonObject) {
        String jsonStr = jsonObject.toString();
        Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
        List<String> jsonPaths = JsonPath.using(conf)
                .parse(jsonStr).
                read("$..*");
        List<JsonParser> result = new ArrayList<>();
        Iterator<String> jsonpath = jsonPaths.iterator();
        //将/t替换成.
        while (jsonpath.hasNext()) {
            JsonParser jsonParser = new JsonParser();
            String tempjsonPath = jsonpath.next();
            tempjsonPath = tempjsonPath.replace("$", "");
            String parentNode = "";
            if (tempjsonPath.contains("']")) {
                if (tempjsonPath.indexOf("]") != tempjsonPath.lastIndexOf("]")) {
                    parentNode = tempjsonPath.substring(0, tempjsonPath.lastIndexOf("["));
                } else {
                    parentNode = tempjsonPath.substring(0, tempjsonPath.indexOf("']") + 2);
                }
            }
            String name = tempjsonPath.substring(tempjsonPath.lastIndexOf("["), tempjsonPath.lastIndexOf("]") + 1);
            name = name.replaceAll("'", "").replaceAll("\\[", "").replaceAll("]", "");
            jsonParser.setName(name);
            Map<String, Object> pathValue = getJsonPathValueText(jsonStr, tempjsonPath);
            jsonParser.setDefaultValue(pathValue.get(tempjsonPath));
            jsonParser.setType(pathValue.get(tempjsonPath + "#Type").toString());
            jsonParser.setJsonPath(tempjsonPath);
            jsonParser.setParentNode(StringUtils.isNotBlank(parentNode) && parentNode.equals(tempjsonPath) ? "$." : parentNode);
            result.add(jsonParser);
        }
        return result;
    }

    /**
     * 截取两个符号中间的字符串
     * @param str
     * @param startChar
     * @param endChar
     * @return
     */
    private String getStringBetweenTwoChars(String str, char startChar, char endChar) {
        String regex = startChar + "([^" + endChar + "]*)" + endChar;
        if ('$' == startChar && '$' == endChar) {
            regex = "\\$" + "([^" + endChar + "]*)" + "\\$";
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }


}
