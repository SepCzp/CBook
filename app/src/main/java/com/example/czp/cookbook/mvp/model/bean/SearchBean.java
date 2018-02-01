package com.example.czp.cookbook.mvp.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/19.
 * function:
 */

public class SearchBean {

    public String status;
    public String msg;
    public ResultBean result;


    public static class ResultBean {

        public String num;
        public List<ListBean> list;

        public static class ListBean implements MultiItemEntity {

            public String id;
            public String classid;
            public String name;
            public String peoplenum;
            public String preparetime;
            public String cookingtime;
            public String content;
            public String pic;
            public String tag;
            public List<MaterialBean> material;
            public List<ProcessBean> process;

            @Override
            public int getItemType() {
                return 2;
            }
        }

        public static class MaterialBean {
            /**
             * mname : 干虾米
             * type : 0
             * amount : 10g
             */

            public String mname;
            public String type;
            public String amount;

        }

        public static class ProcessBean {
            /**
             * pcontent : 按照食材与调料准备好菜品所需主料及辅料；
             * pic : http://api.jisuapi.com/recipe/upload/20160719/162558_54557.jpg
             */

            public String pcontent;
            public String pic;


        }

    }
}
