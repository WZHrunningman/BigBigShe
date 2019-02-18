package com.example.wzh.bigbigshe.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wzh.bigbigshe.Message;
import com.example.wzh.bigbigshe.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripFragment extends Fragment {


    @BindView(R.id.RecyclerView_trip)
    RecyclerView RecyclerViewTrip;
    Unbinder unbinder;

    ArrayList<Message> messageArrayList = new ArrayList<>();

    public TripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip, container, false);
        unbinder = ButterKnife.bind(this, view);

        for (int i = 0; i < 50; i++) {
            Message message = new Message("timeTv" + i,"bikeNumber" + i,"bikeTime" + i,"bikePayMoney" + i);
            messageArrayList.add(message);
        }

        RecyclerViewTrip.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true) );
        RecyclerViewTrip.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL) );
        RecyclerViewTrip.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//重写viewholder的一个方法
                return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_layout,parent,false));
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.timeTv.setText(messageArrayList.get(position).getMy_time() );
                holder.bikeNumber.setText(messageArrayList.get(position).getBike_number() );
                holder.bikeTime.setText(messageArrayList.get(position).getCycling_time() );
                holder.bikePayMoney.setText(messageArrayList.get(position).getCycling_money() );
            }

            @Override
            public int getItemCount() {
                return messageArrayList.size();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.time_tv)
        TextView timeTv;
        @BindView(R.id.bike_number)
        TextView bikeNumber;
        @BindView(R.id.bike_time)
        TextView bikeTime;
        @BindView(R.id.bike_PayMoney)
        TextView bikePayMoney;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
