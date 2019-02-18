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
import android.widget.Button;
import android.widget.TextView;

import com.example.wzh.bigbigshe.R;
import com.example.wzh.bigbigshe.dao.DaoMaster;
import com.example.wzh.bigbigshe.dao.RideRecord;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestDaoFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.generateDaoButton)
    Button generateDaoButton;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;

    ArrayList<RideRecord> rideRecords = new ArrayList<>();

    RecyclerView.Adapter<ViewHolder> adapter;

    DaoMaster daoMaster;

    public TestDaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_dao, container, false);
        unbinder = ButterKnife.bind(this, view);
        initLayout();
        return view;
    }

    private void initLayout() {
        DaoMaster.DevOpenHelper helper = new
                DaoMaster.DevOpenHelper( getContext(),getActivity().getPackageName() + "bike.db");
        daoMaster = new DaoMaster( helper.getWritableDb() );
        rideRecords = (ArrayList<RideRecord>) daoMaster.newSession().getRideRecordDao().loadAll();

        recycleView.setLayoutManager( new LinearLayoutManager( getContext(),LinearLayoutManager.VERTICAL,false ) );
        recycleView.addItemDecoration( new DividerItemDecoration( getContext(),DividerItemDecoration.VERTICAL ) );

        adapter = new RecyclerView.Adapter<ViewHolder>() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder( LayoutInflater.from( getContext() ).inflate( R.layout.item_layout,parent,false ) );
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.bikeNumber.setText( String.valueOf( rideRecords.get( position ).getBike_id() ) );
                holder.bikePayMoney.setText( String.valueOf( rideRecords.get( position ) ) );
                int time = rideRecords.get( position ).getEnd_at().compareTo( rideRecords.get( position ).getStart_at() );
                int bikeTime = time / ( 1000 * 60 );
                holder.bikeTime.setText( String.valueOf( bikeTime ) );
                holder.timeTv.setText( rideRecords.get( position ).getStart_at().toString() );
            }

            @Override
            public int getItemCount() {
                return rideRecords.size();
            }
        };

        recycleView.setAdapter( adapter );

        //绑定事件
        generateDaoButton.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generateDaoButton:
                generateDb();
                break;
        }
    }

    private void generateDb() {
        ArrayList<RideRecord> datas = new ArrayList<>();
        int current = (int) daoMaster.newSession().getRideRecordDao().count();
        for (int i = current; i < current + 10; i++) {
            RideRecord record = new RideRecord();
            record.setId( (long) i );
            record.setBike_id( i );
            record.setStart_at( new Date( 2017, 6 , 20) );
            record.setEnd_at( new Date(2017, 6, 21));
            record.setMoney( 100 );
            record.setIs_pay( true );
        }
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
