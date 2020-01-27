package com.sheela.mobilestore.ui.mycart;

import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheela.mobilestore.R;
import com.sheela.mobilestore.adapter.CartAdapter;
import com.sheela.mobilestore.adapter.LaunchedAdapter;
import com.sheela.mobilestore.adapter.OppoAdapter;
import com.sheela.mobilestore.adapter.SamsungAdapter;
import com.sheela.mobilestore.adapter.SellingAdapter;
import com.sheela.mobilestore.api.BestSellingApi;
import com.sheela.mobilestore.api.CartApi;
import com.sheela.mobilestore.api.MyCartApi;
import com.sheela.mobilestore.api.SamsungApi;
import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.model.Launched;
import com.sheela.mobilestore.model.Oppo;
import com.sheela.mobilestore.model.Samsung;
import com.sheela.mobilestore.model.Selling;
import com.sheela.mobilestore.url.Url;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToolsFragment extends Fragment {
   RecyclerView recyclerViewone;
    List<Cart> cartList;
  CartAdapter cartAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        recyclerViewone=root.findViewById(R.id.recyclerviewone);
        cart();
        return root;

    }
    private void cart() {
       cartList = new ArrayList<>();
      CartApi cartApi = Url.getInstance().create(CartApi.class);
//
        Call<List<Cart>> ListCall5 = cartApi.getcart();
        ListCall5.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Cart> detailsList5 = response.body();
                cartAdapter = new CartAdapter(getContext(), detailsList5);
                recyclerViewone.setAdapter(cartAdapter);
                recyclerViewone.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Log.d("Error Message", "Error " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}