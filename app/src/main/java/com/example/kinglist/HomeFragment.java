package com.example.kinglist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class HomeFragment extends Fragment implements NetView {
    private RecyclerView mRecy;
    private ArrayList<VideoBean.ListBean> mData;
    private VideoAdapter adapter;
    private NetPersener persener;
    private int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mRecy = (RecyclerView) itemView.findViewById(R.id.recy);

        mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecy.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mData = new ArrayList<>();
        adapter = new VideoAdapter(getContext(), mData);
        mRecy.setAdapter(adapter);
        initPre();
        initData();

        adapter.setOnItemLongClickListener(new VideoAdapter.OnItemLongClickListener() {
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


    private void initData() {
        persener.getData();
    }

    private void initPre() {
        persener = new NetPersener(this);
    }

    @Override
    public void setData(VideoBean videoBean) {
        adapter.addData(videoBean);
        //找到Acrtiity传值
        MainActivity activity = (MainActivity) getActivity();
        activity.sendData(videoBean);
    }


}
