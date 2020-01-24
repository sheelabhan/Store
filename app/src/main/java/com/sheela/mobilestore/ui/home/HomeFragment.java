package com.sheela.mobilestore.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.adapter.ContactsAdapter;
import com.sheela.mobilestore.adapter.SamsungAdapter;
import com.sheela.mobilestore.adapter.SellingAdapter;
import com.sheela.mobilestore.api.BestSellingApi;
import com.sheela.mobilestore.api.SamsungApi;
import com.sheela.mobilestore.model.Contacts;
import com.sheela.mobilestore.model.Samsung;
import com.sheela.mobilestore.model.Selling;
import com.sheela.mobilestore.url.Url;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    List<Selling> sellingsList;
    SellingAdapter sellingAdapter;
    List<Samsung> samsungList;
  SamsungAdapter samsungAdapter;
    RecyclerView  recyclerView,recyclerView1, recyclerView2;
    ImageView imgProfile,imgSamsung;
    private int[] mImages = new int[]{
            R.drawable.slider2, R.drawable.slider3, R.drawable.slider1
    };
    private String[] mImageTitle = new String[]{
            "F11", "V17 Pro", "ssamsung"
    };
    CarouselView carouselView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = view.findViewById(R.id.caral);
        imgSamsung= view.findViewById(R.id.imgSamsung);
        recyclerView=view.findViewById(R.id.recycleview);
        recyclerView1 = view.findViewById(R.id.recycleview1);
        recyclerView2 = view.findViewById(R.id.recyclerview2);
        imgProfile = view.findViewById(R.id.imgProfile);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), mImageTitle[position], Toast.LENGTH_SHORT).show();
            }
        });
        List<Contacts> contactsList = new ArrayList<>();
        contactsList.add(new Contacts("Cash On Delivery", "Free cash on Delivery Available", R.drawable.reone));
        contactsList.add(new Contacts("Best Price", "Best and Lowest price Guranteed", R.drawable.retwo));
        contactsList.add(new Contacts("2 Hour Delivery", "We Deliver in 2 Hours at Selected",
                R.drawable.rethree));
        contactsList.add(new Contacts("100% Original Products", "We Sell Only Original Products",
                R.drawable.refour));
        ContactsAdapter contactsAdapter = new ContactsAdapter(getContext(), contactsList);
        recyclerView2.setAdapter(contactsAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        bestselling();
        samsung();

        return view;
    }

    private void bestselling() {
    sellingsList = new ArrayList<>();
        BestSellingApi s = Url.getInstance().create(BestSellingApi.class);
//
        Call<List<Selling>> ListCall = s.getbestselling();
        ListCall.enqueue(new Callback<List<Selling>>() {
            @Override
            public void onResponse(Call<List<Selling>> call, Response<List<Selling>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Selling> detailsList1 = response.body();
           sellingAdapter = new SellingAdapter(getContext(), detailsList1);
                recyclerView1.setAdapter(sellingAdapter);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Selling>> call, Throwable t) {
                Log.d("Error Message", "Error " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void samsung() {
       samsungList = new ArrayList<>();
       SamsungApi samsungApi = Url.getInstance().create(SamsungApi.class);
//
        Call<List<Samsung>> ListCall1 = samsungApi.getsamsung();
        ListCall1.enqueue(new Callback<List<Samsung>>() {
            @Override
            public void onResponse(Call<List<Samsung>> call, Response<List<Samsung>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Samsung> detailsList= response.body();
              samsungAdapter = new SamsungAdapter(getContext(), detailsList);
                recyclerView.setAdapter(samsungAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Samsung>> call, Throwable t) {
                Log.d("Error Message", "Error " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}