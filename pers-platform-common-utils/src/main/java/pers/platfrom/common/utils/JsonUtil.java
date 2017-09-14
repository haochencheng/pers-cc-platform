package pers.platfrom.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

/**
 * Created by cc on  2017/9/12
 */
public class JsonUtil {

    public static String TEST_XML_STRING =
            "<FANAData>" +
                    "  <ProductNumber>65906TDR8YTY4qw</ProductNumber>" +
                    "  <FileName></FileName>" +
                    "  <FileCode>1234</FileCode>" +
                    "  <FileVersion>1.0</FileVersion>" +
                    "  <FileGuid>ObjectID</FileGuid>" +
                    "  <FileByte>文件Base64编码</FileByte>" +
                    "</FANAData>";

    public static void main(String[] args) {
        System.out.println(xmlToJsonobject(TEST_XML_STRING));
    }

    /**
     * xml转化为JsonObj
     * @parm xmlStr
     * return
     */
    public  static JSONObject xmlToJsonobject(String xmlStr){
        return (JSONObject) JSONObject.parse( new XMLSerializer().read(xmlStr).toString().replace("[]", "\"\""));
    }

    /**
     *xml转化为JsonArray
     * @parm xmlStr
     * return
     */
    public  static JSONArray xmlToJsonArray(String xmlStr){
        return (JSONArray) JSONObject.parse( new XMLSerializer().read(xmlStr).toString().replace("[]", "\"\""));
    }

    /**
     * json转化为xml
     * @parm json jsoStr
     * return
     */
    public static String jsonToxml(String json){
        XMLSerializer xmlSerializer=new XMLSerializer();
        xmlSerializer.setRootName("FANAData");
        return xmlSerializer.write(net.sf.json.JSONObject.fromObject(json));
    }


    public JSONArray resultSetToJson(ResultSet rs) throws Exception {
        ResultSetMetaData rsmd = rs.getMetaData();
        int coulmnCount = rsmd.getColumnCount();
        JSONArray jsonArray = new JSONArray();
        while (rs.next()) {
            JSONObject mapOfColValues = new JSONObject();
            for (int i = 1; i <= coulmnCount; i++) {
                Object o = rs.getObject(i);
                String columnName = rsmd.getColumnName(i);
                if (o instanceof Date) {
                    mapOfColValues.put(columnName,
                            DateUtil.formatDate((Date) o, "yyyy-MM-dd"));
                } else {
                    mapOfColValues.put(columnName, o);
                }

            }
            jsonArray.add(mapOfColValues);
        }
        return jsonArray;
    }

}
