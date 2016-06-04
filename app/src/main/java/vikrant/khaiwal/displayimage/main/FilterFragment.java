package vikrant.khaiwal.displayimage.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import vikrant.khaiwal.displayimage.LoadMoreData;
import vikrant.khaiwal.displayimage.R;
import vikrant.khaiwal.displayimage.model.ViewModel;
import vikrant.khaiwal.displayimage.adapter.RecyclerViewAdapter;
import vikrant.khaiwal.displayimage.restModel.MainClass;
import vikrant.khaiwal.displayimage.restclient.RestClient;

/**
 * Created by vikrant on 22/5/16.
 */
public class FilterFragment extends Fragment{
    private RecyclerView recyclerView;
    private static List<ViewModel> items = new ArrayList<>();
    private View root;
    protected Handler handler;
    private RecyclerViewAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        root = (View) inflater.inflate(R.layout.content_main, null);
        handler = new Handler();
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        initRecyclerView();
        getFilterPageImages();
        return root;
    }
    private void initRecyclerView() {
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(mLayoutManager);
    }
    private void setRecyclerAdapter(RecyclerView recyclerView) {
        mAdapter = new RecyclerViewAdapter(getActivity(), items, recyclerView);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new LoadMoreData() {
            @Override
            public void onLoadMore() {
                //add null , so the adapter will check view_type and show progress bar at bottom
                items.add(null);
                mAdapter.notifyItemInserted(items.size() - 1);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //   remove progress item
                        items.remove(items.size() - 1);
                        mAdapter.notifyItemRemoved(items.size());
                        getFilterPaginationImages();
                    }
                }, 2000);

            }
        });

    }
    private void getFilterPageImages() {

        RestClient.RestInterface service = RestClient.getClient(getActivity());
        Call<MainClass> call = service.getFilterImages();
        call.enqueue(new retrofit2.Callback<MainClass>() {
            @Override
            public void onResponse(Call<MainClass> call, retrofit2.Response<MainClass> response) {
                if (response != null && response.isSuccessful() && response.errorBody() == null){
                    items.clear();
                    int end = Integer.parseInt(response.body().getResponseHeader().getParams().getRows());
                    for (int i = 0; i < end; i++) {
                        ViewModel viewModel = new ViewModel();
                        viewModel.setText(response.body().getResponse().getDocs().get(i).getTitle());
                        viewModel.setImage(response.body().getResponse().getDocs().get(i).getLarge_image_url());
                        viewModel.setImageId(response.body().getResponse().getDocs().get(i).getPid());
                        items.add(viewModel);
                    }
                    setRecyclerAdapter(recyclerView);
                }

            }

            @Override
            public void onFailure(Call<MainClass> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
    private void getFilterPaginationImages() {
        RestClient.RestInterface service = RestClient.getClient(getActivity());
        Call<MainClass> call = service.getFilterPagination(items.size()+1, "json");
        call.enqueue(new retrofit2.Callback<MainClass>() {
            @Override
            public void onResponse(Call<MainClass> call, retrofit2.Response<MainClass> response) {
                if (response != null && response.isSuccessful() && response.errorBody() == null) {
                    int end = Integer.parseInt(response.body().getResponseHeader().getParams().getRows());
                    for (int i = 0; i < end - 1; i++) {
                        ViewModel viewModel = new ViewModel();
                        viewModel.setText(response.body().getResponse().getDocs().get(i).getTitle());
                        viewModel.setImage(response.body().getResponse().getDocs().get(i).getLarge_image_url());
                        viewModel.setImageId(response.body().getResponse().getDocs().get(i).getPid());
                        items.add(viewModel);
                        mAdapter.notifyItemInserted(items.size());
                    }
                    mAdapter.setLoaded();
                }

            }

            @Override
            public void onFailure(Call<MainClass> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
    @Override
    public void onPause(){
        super.onPause();
        items.clear();
    }
}
