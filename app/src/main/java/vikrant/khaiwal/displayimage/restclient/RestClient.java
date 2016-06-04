package vikrant.khaiwal.displayimage.restclient;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vikrant.khaiwal.displayimage.restModel.MainClass;

/**
 * Created by vikrant on 22/5/16.
 */
public class RestClient {
    private static String baseUrl = "http://103.224.241.148:2000/solr/zupigo/";

    public static RestInterface getClient(final Context context) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient ;

        okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        Retrofit retroFit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
//            }
        RestInterface retrofit = retroFit.create(RestInterface.class);
        return retrofit;
    }
    public interface RestInterface {

       //GET query for Home Page Images
        @GET("select?q=*&fl=pid,large_image_url,title&rows=30&wt=json")
        Call<MainClass> getAllImages() ;

        //GET query for Home Pagination
        @GET("select?q=*&rows=30")
        Call<MainClass> getAllImagePagination(@Query("start") Integer start, @Query("fl") String fl,@Query("wt") String wt) ;

        //GET query for Filter Page Images
        @GET("select?q=*&fq=manufacturer:DooDa&fl=pid,large_image_url,title&rows=30&wt=json")
        Call<MainClass> getFilterImages() ;

        //GET query for Home Pagination
        @GET("?q=*&fq=manufacturer:DooDa&fl=pid,large_image_url,title&rows=30")
        Call<MainClass> getFilterPagination(@Query("start") Integer start, @Query("wt") String wt) ;

        //GET query for Image detail
        @GET("select?q=*")
        Call<MainClass> getDetailImages(@Query("fq") String fq, @Query("fl") String fl, @Query("rows") String rows,@Query("wt") String wt) ;
    }


}
