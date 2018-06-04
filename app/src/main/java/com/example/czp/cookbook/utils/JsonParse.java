package com.example.czp.cookbook.utils;

/**
 * Created by chenzipeng on 2018/1/18.
 * function:
 */
@Deprecated
public class JsonParse {

//    public static HttpMsg<ClassifyBean> parseClassifyBean(String json) {
//        HttpMsg<ClassifyBean> httpmsg = new HttpMsg<>();
//        ClassifyBean classifyBean;
//        ClassifyBean.ListBean listBean;
//        try {
//            JSONObject jsons = new JSONObject(json);
//            JSONObject result = jsons.optJSONObject("result");
//            String msg = result.optString("msg");
//            String status = result.optString("status");
//            JSONArray jsonArray = result.optJSONArray("result");
//            List<ClassifyBean> lists = new ArrayList<>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject data = jsonArray.optJSONObject(i);
//                String classid = data.optString("classid");
//                String name = data.optString("name");
//                String parentid = data.optString("parentid");
//                JSONArray list = data.optJSONArray("list");
//
//                List<ClassifyBean.ListBean> listBeans = new ArrayList<>();
//                for (int j = 0; j < list.length(); j++) {
//                    JSONObject object = list.optJSONObject(j);
//                    String classid1 = object.optString("classid");
//                    String name1 = object.optString("name");
//                    String parentid1 = object.optString("parentid");
//
//                    listBean = new ClassifyBean.ListBean();
//                    listBean.classid = classid1;
//                    listBean.name = name1;
//                    listBean.parentid = parentid1;
//                    listBeans.add(listBean);
//                }
//                classifyBean = new ClassifyBean();
//                classifyBean.classid = classid;
//                classifyBean.name = name;
//                classifyBean.parentid = parentid;
//                classifyBean.list = listBeans;
//                lists.add(classifyBean);
//            }
//            httpmsg.msg = msg;
//            httpmsg.status = status;
//            httpmsg.data = lists;
//            return httpmsg;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static HttpMsg<SearchBean> parseSearch(String json) {
//        HttpMsg<SearchBean> httpmsg = new HttpMsg<>();
//        List<SearchBean> searchBeens = new ArrayList<>();
//        List<SearchBean.Result> seachBeens = null;
//        SearchBean searchBean = new SearchBean();
//        SearchBean.Result seachBean;
//        SearchBean.Result.Material material;
//        SearchBean.Result.Process process;
//        try {
//            JSONObject jsons = new JSONObject(json);
//            JSONObject result = jsons.optJSONObject("result");
//            String msg = result.optString("msg");
//            String status = result.optString("status");
//            JSONObject rs = result.optJSONObject("result");
//            String num = rs.optString("num");
//
//            JSONArray list = rs.optJSONArray("list");
//
//            seachBeens = new ArrayList<>();
//            for (int i = 0; i < list.length(); i++) {
//                seachBean = new SearchBean.Result();
//                List<SearchBean.Result.Material> materials = new ArrayList<>();
//                List<SearchBean.Result.Process> processes = new ArrayList<>();
//
//                JSONObject seach = list.optJSONObject(i);
//
//                String classid = seach.optString("classid");
//                String content = seach.optString("content");
//                String cookingtime = seach.optString("cookingtime");
//                String id = seach.optString("id");
//
//                JSONArray mater = seach.optJSONArray("material");
//                for (int j = 0; j < mater.length(); j++) {
//                    material = new SearchBean.Result.Material();
//                    JSONObject jsonObject = mater.optJSONObject(i);
//                    String amount = jsonObject.optString("amount");
//                    String mname = jsonObject.optString("mname");
//                    String type = jsonObject.optString("type");
//
//                    material.amount = amount;
//                    material.mname = mname;
//                    material.type = type;
//                    materials.add(material);
//                }
//
//                String name = seach.optString("name");
//                String peoplenum = seach.optString("peoplenum");
//                String pic = seach.optString("pic");
//                String preparetime = seach.optString("preparetime");
//
//                JSONArray pro = seach.optJSONArray("process");
//                for (int j = 0; j < pro.length(); j++) {
//                    JSONObject p = pro.optJSONObject(i);
//                    String pcontent = p.optString("pcontent");
//                    String pics = p.optString("pic");
//
//                    process = new SearchBean.Result.Process();
//                    process.pcontent = pcontent;
//                    process.pic = pics;
//                    processes.add(process);
//                }
//
//                String tag = seach.optString("tag");
//
//                seachBean.classid = classid;
//                seachBean.content = content;
//                seachBean.cookingtime = cookingtime;
//                seachBean.id = id;
//                seachBean.name = name;
//                seachBean.peoplenum = peoplenum;
//                seachBean.pic = pic;
//                seachBean.preparetime = preparetime;
//                seachBean.tag = tag;
//                seachBean.Materials = materials;
//                seachBean.processList = processes;
//                seachBeens.add(seachBean);
//            }
//            searchBean.num = num;
//            searchBean.results = seachBeens;
//            searchBeens.add(searchBean);
//            httpmsg.msg = msg;
//            httpmsg.status = status;
//            httpmsg.data = searchBeens;
//            return httpmsg;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}
