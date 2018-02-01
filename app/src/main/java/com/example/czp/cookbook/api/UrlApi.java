package com.example.czp.cookbook.api;

import com.example.czp.cookbook.mvp.model.bean.ClassifyBean;
import com.example.czp.cookbook.mvp.model.bean.CookDetailBean;
import com.example.czp.cookbook.mvp.model.bean.HttpMsg;
import com.example.czp.cookbook.mvp.model.bean.SearchBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public interface UrlApi {
    String baseUrl = "https://way.jd.com/jisuapi/";

    @GET("recipe_class")
    Observable<HttpMsg<ClassifyBean>> getdata(@Query("appkey") String key);

    @POST("search")
    Observable<HttpMsg<SearchBean>> getSearchData(@Query("keyword") String name
            , @Query("num") int num, @Query("appkey") String key);

    @POST("detail")
    Observable<HttpMsg<CookDetailBean>> getCookDetailData(@Query("id") int id,
                                                          @Query("appkey") String key);

    @POST("byclass")
    Observable<HttpMsg<SearchBean>> getSearchMore(@Query("classid") int classid,
                                                          @Query("start") int start,@Query("num") int num,@Query("appkey") String key);

}
