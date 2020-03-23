package com.example.jsonplaceholder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jsonplaceholder.Bean.DataBean;
import com.example.jsonplaceholder.Network.GetImageByUrl;
import com.example.jsonplaceholder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DataBean> list;
    private OnItemClickHandler mClickHandler;

    public interface OnItemClickHandler{
        void onItemClick(int position, DataBean contentObject);
    }

    public ApiAdapter(Context context,ArrayList<DataBean> list, OnItemClickHandler mClickHandler) {
        this.context = context;
        this.list = list;
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_content_api,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataBean bean = list.get(position);
        holder.textID.setText(bean.getId());
        holder.textTitle.setText(bean.getTitle());
        String url = bean.getThumbnailUrl();
        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(holder.img, url);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textID;
        private TextView textTitle;
        //private TextView textThumb;
        private ImageView img;
        private WebView webView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textID = (TextView)itemView.findViewById(R.id.textID);
            textTitle = (TextView)itemView.findViewById(R.id.textTitle);
            //textThumb = (TextView)itemView.findViewById(R.id.textThumb);
            img = (ImageView)itemView.findViewById(R.id.img);
            //webView = (WebView)itemView.findViewById(R.id.webView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    DataBean contentObject = list.get(position);
                    mClickHandler.onItemClick(position, contentObject);
                }
            });
        }
    }
}
