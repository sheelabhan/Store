package com.sheela.mobilestore.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.adapter.ContactsAdapter;
import com.sheela.mobilestore.adapter.LaunchedAdapter;
import com.sheela.mobilestore.adapter.OppoAdapter;
import com.sheela.mobilestore.adapter.SamsungAdapter;
import com.sheela.mobilestore.adapter.SellingAdapter;
import com.sheela.mobilestore.api.BestSellingApi;
import com.sheela.mobilestore.api.LaunchedApi;
import com.sheela.mobilestore.api.OppoApi;
import com.sheela.mobilestore.api.SamsungApi;
import com.sheela.mobilestore.model.Contacts;
import com.sheela.mobilestore.model.Launched;
import com.sheela.mobilestore.model.Oppo;
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
    private EditText et_search_samsung;
    List<Selling> sellingsList;
    SellingAdapter sellingAdapter;
    List<Samsung> samsungList;
  SamsungAdapter samsungAdapter;
    List<Launched> launchedList;
   LaunchedAdapter launchedAdapter;
    List<Oppo> oppoList;
  OppoAdapter oppoAdapter;
    RecyclerView  recyclerview,recyclerview1, recyclerview2,recyclerview3,recyclerview4;
    ImageView imgProfile,imgSamsung,imgsheela,imgOppo,insta,facebook;
    private int[] mImages = new int[]{
            R.drawable.slider2, R.drawable.slider3, R.drawable.slider1
    };
    private String[] mImageTitle = new String[]{
            "F11", "V17 Pro", "ssamsung"
    };
    CarouselView carouselView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = view.findViewById(R.id.caral);
        imgSamsung= view.findViewById(R.id.imgSamsung);


        et_search_samsung=view.findViewById(R.id.et_search_samsung);
        recyclerview=view.findViewById(R.id.recyclerview);
        recyclerview1=view.findViewById(R.id.recyclerview1);
        recyclerview2=view.findViewById(R.id.recyclerview2);
        recyclerview3=view.findViewById(R.id.recyclerview3);
        recyclerview4=view.findViewById(R.id.recyclerview4);
        facebook=view.findViewById(R.id.facebook);
        insta=view.findViewById(R.id.insta);
        imgsheela=view.findViewById(R.id.imgsheela);
        imgProfile = view.findViewById(R.id.imgProfile);
        imgOppo = view.findViewById(R.id.imgOppo);
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


        et_search_samsung.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                samsungAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        recyclerview2.setAdapter(contactsAdapter);
        recyclerview2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        bestselling();
        samsung();
        justlaunched();
        oppo();
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://www.facebook.com");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://www.insta.com");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

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
                recyclerview1.setAdapter(sellingAdapter);
                recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerview1.setHasFixedSize(true);
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
                recyclerview.setAdapter(samsungAdapter);
                recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerview.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Samsung>> call, Throwable t) {
                Log.d("Error Message", "Error " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void justlaunched() {
       launchedList= new ArrayList<>();
       LaunchedApi launchedApi = Url.getInstance().create(LaunchedApi.class);
//
        Call<List<Launched>> ListCall3 = launchedApi.getlaunched();
        ListCall3.enqueue(new Callback<List<Launched>>() {
            @Override
            public void onResponse(Call<List<Launched>> call, Response<List<Launched>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Launched> detailsList3= response.body();
             launchedAdapter = new LaunchedAdapter(getContext(), detailsList3);
                recyclerview3.setAdapter(launchedAdapter);
                recyclerview3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerview3.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Launched>> call, Throwable t) {
                Log.d("Error Message", "Error " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void oppo() {
      oppoList= new ArrayList<>();
       OppoApi oppoApi = Url.getInstance().create(OppoApi.class);
//
        Call<List<Oppo>> ListCall4 = oppoApi.getoppo();
        ListCall4.enqueue(new Callback<List<Oppo>>() {
            @Override
            public void onResponse(Call<List<Oppo>> call, Response<List<Oppo>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Oppo> detailsList4= response.body();
              oppoAdapter= new OppoAdapter(getContext(), detailsList4);
                recyclerview4.setAdapter(oppoAdapter);
                recyclerview4.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerview4.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Oppo>> call, Throwable t) {
                Log.d("Error Message", "Error " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}