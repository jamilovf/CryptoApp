package com.example.cryptoapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>{
    private List<CryptoModel> mItemList;
    private LayoutInflater mInflater;
    Context context;

    public ItemListAdapter(Context context, List<CryptoModel> itemList){
        mInflater = LayoutInflater.from(context);
        this.mItemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycle_item, parent, false);
        return new ItemViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CryptoModel mCurrent = mItemList.get(position);
        String formattedPrice = context.getResources().getString(R.string.USD) + mCurrent.getPrice();

        holder.currencyTextView.setText(mCurrent.getCurrency());
        holder.priceTextView.setText(formattedPrice);
        GlideToVectorYou.init().with(context)
                .load(Uri.parse(mCurrent.getLogoUrl()), holder.logoImageView);

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public final TextView currencyTextView;
        public final TextView priceTextView;
        public final ImageView logoImageView;

        final ItemListAdapter mAdapter;

        public ItemViewHolder(View itemView, ItemListAdapter adapter) {
            super(itemView);
            currencyTextView = itemView.findViewById(R.id.currencyTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            logoImageView = itemView.findViewById(R.id.logoImageView);
            this.mAdapter = adapter;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    Intent intent = new Intent(view.getContext(), CryptoMetadataActivity.class);
                    intent.putExtra("currency", mItemList.get(position).getCurrency());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
