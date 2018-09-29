package com.autotest.demo;


import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CalDateTest {

    String url = "http://test-workflow.kuainiu.io/workflow/common/work-time";

    @DataProvider(name = "accountData")
    public static Object[][] accounts(){
        // 测试数据准备
        // 这里写入excel的路径
//        String file = System.getProperty("user.dir")+"/src/main/resources/testData.xlsx";
//        Object[][] records;
//        //这里要写入Excel中的sheetname
//        records = ExcelReader.getExpectationData(file, "transferData");
//        return records;

        return new Object[][] {
                {"法定年假", "2018-10-08 10:00:00", "2018-10-08 19:00:00", 8},
                {"法定年假", "2018-10-08 10:00:00", "2018-10-08 15:00:00", 4},
                {"法定年假", "2018-10-08 10:00:00", "2018-10-09 19:00:00", 16},
                {"法定年假", "2018-10-08 10:00:00", "2018-10-09 15:20:00", 16},
                {"法定年假", "2018-10-08 10:00:00", "2018-10-09 15:00:00", 12},
                {"法定年假", "2018-10-08 12:00:00", "2018-10-08 15:20:00", 8},
                {"法定年假", "2018-10-08 15:00:00", "2018-10-09 19:00:00", 12}
        };

    }

    @Test(dataProvider = "accountData")
    public void testCal(String type, String start, String end,int expect){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("times[0]",start));
        list.add(new BasicNameValuePair("times[1]",end));
        list.add(new BasicNameValuePair("type",type));
        String result = HttpUtils.doGet(url,list);
        System.out.println(result);


        JSONObject jsonObject = JSONObject.fromObject(result);


        int hour = jsonObject.getJSONObject("data").getInt("hour");

        Assert.assertEquals(expect,hour,"数据不相等");
    }



}
