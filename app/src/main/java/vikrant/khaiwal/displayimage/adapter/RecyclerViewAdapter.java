package vikrant.khaiwal.displayimage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vikrant.khaiwal.displayimage.main.DetailActivity;
import vikrant.khaiwal.displayimage.LoadMoreData;
import vikrant.khaiwal.displayimage.R;
import vikrant.khaiwal.displayimage.utility.MyUtility;
import vikrant.khaiwal.displayimage.model.ViewModel;

/**
 * Created by vikrant on 22/5/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter  {

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private List<ViewModel> items;
    private Intent displayOrderDetail;
    private Context mContext;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private LoadMoreData onLoadMoreListener;

    public RecyclerViewAdapter(Context activity, List<ViewModel> items, RecyclerView recyclerView) {
        this.mContext = activity;
        this.items = items;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {

            final GridLayoutManager linearLayoutManager = (GridLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }
    public void setLoaded() {
        loading = false;
    }
    public void setOnLoadMoreListener(LoadMoreData onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }
    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

            vh = new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progressbar_item, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;

    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {

            ViewModel item = items.get(position);

            ((ViewHolder) holder).text.setText(item.getText());
            ((ViewHolder) holder).image.setImageBitmap(null);
            Picasso.with(((ViewHolder) holder).image.getContext()).load(item.getImage()).into(((ViewHolder) holder).image);
            holder.itemView.setTag(item);
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView image;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        public void onClick(View v) {
        displayOrderDetail = new Intent(mContext, DetailActivity.class);
        displayOrderDetail.putExtra(MyUtility.EXTRA_PID, items.get(getPosition()).getImageId());
        mContext.startActivity(displayOrderDetail);

                //onItemClickListener.onItemClick(v, (ViewModel) v.getTag());

        }
    }
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }

}
