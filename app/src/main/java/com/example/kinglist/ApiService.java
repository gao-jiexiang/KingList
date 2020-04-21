package com.example.kinglist;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    String Base_Url="http://yun918.cn/";
    @GET("live/xsxcjfsj.json")
    Observable<VideoBean> getData();

}
