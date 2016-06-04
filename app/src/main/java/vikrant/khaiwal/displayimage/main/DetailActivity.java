/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vikrant.khaiwal.displayimage.main;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import vikrant.khaiwal.displayimage.R;
import vikrant.khaiwal.displayimage.utility.MyUtility;
import vikrant.khaiwal.displayimage.restModel.MainClass;
import vikrant.khaiwal.displayimage.restclient.RestClient;

public class DetailActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Context mContext;

    @SuppressWarnings("ConstantConditions")
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initActivityTransitions();
        setContentView(R.layout.activity_detail);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), MyUtility.EXTRA_IMAGE);
        supportPostponeEnterTransition();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getImageDetails(getIntent().getStringExtra(MyUtility.EXTRA_PID));
    }

    @Override public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }
    private void getImageDetails(String imageId) {

        RestClient.RestInterface service = RestClient.getClient(mContext);
        Call<MainClass> call = service.getDetailImages("pid:"+imageId,"pid,large_image_url,title","1","json");
        call.enqueue(new retrofit2.Callback<MainClass>() {
            @Override
            public void onResponse(Call<MainClass> call, retrofit2.Response<MainClass> response) {
                if (response != null && response.isSuccessful() && response.errorBody() == null){
                    String itemTitle = response.body().getResponse().getDocs().get(0).getTitle();
                    collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
                    collapsingToolbarLayout.setTitle(response.body().getResponse().getDocs().get(0).getTitle());
                    collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

                    final ImageView image = (ImageView) findViewById(R.id.image);
                    Picasso.with(image.getContext()).load(response.body().getResponse().getDocs().get(0).getLarge_image_url()).into(image);

                    TextView title = (TextView) findViewById(R.id.title);
                    title.setText(itemTitle);
                }
            }

            @Override
            public void onFailure(Call<MainClass> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
