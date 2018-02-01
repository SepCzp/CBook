package com.example.czp.cookbook.mvp.model.bean;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/22.
 * function:
 */

public class CookDetailBean {


    /**
     * status : 0
     * msg : ok
     * result : {"id":"5","classid":"2","name":"翡翠彩蔬卷","peoplenum":"1-2人","preparetime":"10分钟内","cookingtime":"10分钟内","content":"春天是为夏天做准备的刮油季，为了夏天能够美美的穿上漂亮的花裙子，让我们一起来狠狠的刮油吧。<br />这个色彩缤纷的彩蔬卷，低热量，高营养，是一道秀色可餐的减肥餐~","pic":"http://api.jisuapi.com/recipe/upload/20160719/115138_19423.jpg","tag":"减肥,咸香,宴请,抗氧化,抗衰老,私房菜,聚会","material":[{"mname":"盐","type":"0","amount":"1勺"},{"mname":"鲍汁","type":"0","amount":"1茶勺"},{"mname":"糖","type":"0","amount":"适量"},{"mname":"水淀粉","type":"0","amount":"1勺"},{"mname":"大白菜","type":"1","amount":"3片"},{"mname":"菠菜","type":"1","amount":"30g"},{"mname":"红萝卜","type":"1","amount":"50g"},{"mname":"彩椒","type":"1","amount":"50g"}],"process":[{"pcontent":"彩椒，红萝卜切丝","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_72503.jpg"},{"pcontent":"白菜和菠菜飞水","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_29860.jpg"},{"pcontent":"将蔬菜丝码在白菜叶上","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_92740.jpg"},{"pcontent":"卷成卷","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_38394.jpg"},{"pcontent":"切成段","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_26158.jpg"},{"pcontent":"锅中放少许水，加入盐，鲍汁（蚝油），水淀粉，煮至汤汁粘稠","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_40907.jpg"},{"pcontent":"浇在彩蔬卷上即可","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_40860.jpg"}]}
     */

    public String status;
    public String msg;
    public ResultBean result;


    public static class ResultBean {
        /**
         * id : 5
         * classid : 2
         * name : 翡翠彩蔬卷
         * peoplenum : 1-2人
         * preparetime : 10分钟内
         * cookingtime : 10分钟内
         * content : 春天是为夏天做准备的刮油季，为了夏天能够美美的穿上漂亮的花裙子，让我们一起来狠狠的刮油吧。<br />这个色彩缤纷的彩蔬卷，低热量，高营养，是一道秀色可餐的减肥餐~
         * pic : http://api.jisuapi.com/recipe/upload/20160719/115138_19423.jpg
         * tag : 减肥,咸香,宴请,抗氧化,抗衰老,私房菜,聚会
         * material : [{"mname":"盐","type":"0","amount":"1勺"},{"mname":"鲍汁","type":"0","amount":"1茶勺"},{"mname":"糖","type":"0","amount":"适量"},{"mname":"水淀粉","type":"0","amount":"1勺"},{"mname":"大白菜","type":"1","amount":"3片"},{"mname":"菠菜","type":"1","amount":"30g"},{"mname":"红萝卜","type":"1","amount":"50g"},{"mname":"彩椒","type":"1","amount":"50g"}]
         * process : [{"pcontent":"彩椒，红萝卜切丝","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_72503.jpg"},{"pcontent":"白菜和菠菜飞水","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_29860.jpg"},{"pcontent":"将蔬菜丝码在白菜叶上","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_92740.jpg"},{"pcontent":"卷成卷","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_38394.jpg"},{"pcontent":"切成段","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_26158.jpg"},{"pcontent":"锅中放少许水，加入盐，鲍汁（蚝油），水淀粉，煮至汤汁粘稠","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_40907.jpg"},{"pcontent":"浇在彩蔬卷上即可","pic":"http://api.jisuapi.com/recipe/upload/20160719/162546_40860.jpg"}]
         */

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

        public static class MaterialBean {
            /**
             * mname : 盐
             * type : 0
             * amount : 1勺
             */

            public String mname;
            public String type;
            public String amount;

        }

        public static class ProcessBean {
            /**
             * pcontent : 彩椒，红萝卜切丝
             * pic : http://api.jisuapi.com/recipe/upload/20160719/162546_72503.jpg
             */

            public String pcontent;
            public String pic;


        }
    }

}
