package com.example.kim.networkproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NaverApiService {
    /*https://openapi.naver.com/v1/search/book.json?query=" + key + "&display=20&start=1"**/
    @Headers({
            "X-Naver-Client-id: sg3ym0bsgTSLA9wj4W5P",
            "X-Naver-Client-Secret: KI1v3AuquJ"})
    //Naver API 요청시 ID와 Secret 코드 값

    //이는 BaseUrl 뒤에 붙는다. NaverApi는 BaseUrl 에 + (자신이 사용할 api uri)
    @GET("/v1/search/news.json")
    Call<NewsItems> getSearchItems(
            @Query(value = "query", encoded = true) String query,
            @Query(value = "display") String display,
            @Query(value = "start") String start
    );

}
