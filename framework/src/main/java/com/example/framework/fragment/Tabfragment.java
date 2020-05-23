package com.example.framework.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.R;


/**
 * @filename: com.example.framework.fragment
 * @author: 60347
 * @description: 滑动界面
 * @time: 2020/5/23 14:06
 */
public class Tabfragment extends Fragment {

    private TextView mTvTitle;
    private String mTitle;
    private static final String BUNDLE_KEY_TITLE = "key_title";

    public interface onTitleClickListener {
        void onClick(String title);
    }

    private onTitleClickListener mListener;

    public void setOnTitleClickListener(onTitleClickListener listener) {
        mListener = listener;
    }

    public static Tabfragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_TITLE, title);
        Tabfragment tabfragment = new Tabfragment();
        tabfragment.setArguments(bundle);
        return tabfragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTitle = arguments.getString(BUNDLE_KEY_TITLE, "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvTitle.setText(mTitle);

        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取Activity对象
                if (mListener != null) {
                    mListener.onClick("微信已改变");
                }
            }
        });
    }

    public void changeTitle(String title) {
        if (!isAdded()) {
            return;
        }
        mTvTitle.setText(title);
    }
}
