package com.netforceinfotech.tagalong.home.findride.paymentmodes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.netforceinfotech.tagalong.R;
import java.util.List;

/**
 * Created by JitendraSingh on 11/24/2016.
 */

public class CardCustomAdapter extends RecyclerView.Adapter<CardListVieHolder>{

    private Context mContext;
    private List<CardListData> cardListDatas;

    public CardCustomAdapter(Context mContext, List<CardListData> cardListDatas) {
        this.mContext = mContext;
        this.cardListDatas=cardListDatas;


    }
    @Override
    public CardListVieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_card_row_list, parent, false);
        return new CardListVieHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CardListVieHolder holder, int position) {

         CardListData cardlist =cardListDatas.get(position);

        holder.cardNoTextView.setText(cardlist.getCardNumber());

    }

    @Override
    public int getItemCount() {
        return cardListDatas.size();
    }
}
