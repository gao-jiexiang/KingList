package com.example.kinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<VideoBean.ListBean> mVideo;

    public VideoAdapter(Context mContext, ArrayList<VideoBean.ListBean> mVideo) {
        this.mContext = mContext;
        this.mVideo = mVideo;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        VideoBean.ListBean listBean = mVideo.get(position);
        holder.tv_title.setText(listBean.getTheme());
        holder.tv_time.setText(listBean.getTime());
        holder.tv_desc.setText(listBean.getCollect_id());
        Glide.with(mContext).load(listBean.getImage_url()).into(holder.tv_img);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickListener.onItemLongClick(position);
                return true;
            }
        });

    }

    public void addData(VideoBean videoBean){
        mVideo.addAll(videoBean.getList());
        notifyDataSetChanged();
    }

    public void deleteData(int videoBean){
        mVideo.remove(videoBean);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView tv_img;
        private final TextView tv_desc;
        private final TextView tv_title;
        private final TextView tv_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_img = itemView.findViewById(R.id.tv_img);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }

    private OnItemLongClickListener onItemLongClickListener;

    public VideoAdapter setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
        return this;
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }
}
