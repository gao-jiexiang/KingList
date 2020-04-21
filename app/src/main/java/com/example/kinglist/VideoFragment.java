package com.example.kinglist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinglist.persener.NetPersener;
import com.example.kinglist.view.NetView;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {
    private RecyclerView mRv;
    private RecAdapter adapter;
    private ArrayList<VideoBean.ListBean> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_video, null);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mRv = (RecyclerView) itemView.findViewById(R.id.rv);

        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mData = new ArrayList<>();
        adapter = new RecAdapter(getActivity(),mData);
        mRv.setAdapter(adapter);
        adapter.setOnItemLongClickListener(new RecAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                initListener(position);
            }
        });
    }

    private void initListener(final int posi) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否删除这条数据")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        adapter.deleteData(posi);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.show();
    }

    public void setData(VideoBean videoBean) {
        ArrayList<VideoBean.ListBean> finalBean = new ArrayList<>();
        List<VideoBean.ListBean> list = videoBean.getList();
        for (int i = 0; i < list.size(); i++) {
            VideoBean.ListBean listBean = list.get(i);
            if (listBean.getType()==1){
                finalBean.add(listBean);
            }
        }
        adapter.addData(videoBean);
    }
}
