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
import com.sheela.mobilestore.adapter.OppoAdapter;
import com.sheela.mobilestore.adapter.SellingAdapter;
import com.sheela.mobilestore.api.BestSellingApi;
import com.sheela.mobilestore.api.MyCartApi;
import com.sheela.mobilestore.model.Cart;
import com.sheela.mobilestore.model.Oppo;
import com.sheela.mobilestore.model.Selling;
import com.sheela.mobilestore.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToolsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        return root;
    }

}