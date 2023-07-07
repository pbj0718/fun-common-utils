# 记录疑难杂症

## 1.Mysql数据库使用MybatisPlus插入长字符串报错
MybatisPlus 插入数据到Mysql的时候提示
com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'xxx' at row 1

解决方案: 
1. 检查数据库表字段配置长度是否足够
2. 如果数据库表中长字符串可以正常插入，则需要在jdbc连接配置中添加如下内容：&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false