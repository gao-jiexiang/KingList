package com.example.kinglist.persener;

import com.example.kinglist.ResultCallBack;
import com.example.kinglist.VideoBean;
import com.example.kinglist.model.NetModel;
import com.example.kinglist.view.NetView;

public class NetPersener {
    private NetView netView;
    private NetModel netModel;

    public NetPersener(NetView netView) {
        this.netView = netView;
        this.netModel=new NetModel();
    }

    public void getData() {
        netModel.getData(new ResultCallBack<VideoBean>() {
            @Override
            public void onSecuss(VideoBean videoBean) {
                netView.setData(videoBean);

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
