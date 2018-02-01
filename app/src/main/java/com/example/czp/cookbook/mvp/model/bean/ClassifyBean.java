package com.example.czp.cookbook.mvp.model.bean;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/19.
 * function:
 */

public class ClassifyBean {


    public String status;
    public String msg;
    public List<ResultBean> result;

    public static class ResultBean {


        public String classid;
        public String name;
        public String parentid;
        public List<ListBean> list;


        public static class ListBean {
            /**
             * classid : 2
             * name : 减肥
             * parentid : 1
             */

            public String classid;
            public String name;
            public String parentid;


        }
    }

}
