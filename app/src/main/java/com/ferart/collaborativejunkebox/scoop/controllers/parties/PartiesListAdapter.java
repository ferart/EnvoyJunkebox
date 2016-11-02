package com.ferart.collaborativejunkebox.scoop.controllers.parties;

import com.ferart.collaborativejunkebox.R;
import com.ferart.collaborativejunkebox.model.Party;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ferart on 11/1/16.
 */

public class PartiesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Party> parties=new ArrayList<>();



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.party_list_item,parent,false);
        RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(itemView) {
            @Override
            public String toString() {
                return super.toString();
            }
        };

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View viewHolder = holder.itemView;
        Party party= getItemAt(position);
        ((TextView)viewHolder.findViewById(R.id.host_name_text)).setText(party.getUserUID());
        ((TextView)viewHolder.findViewById(R.id.party_name_text)).setText(party.getName());
    }

    public Party getItemAt(final int index){
        return parties.get(index);
    }

    @Override
    public int getItemCount() {
        return parties.size();
    }

    public void setParties(List<Party> parties){
        this.parties=parties;
        notifyDataSetChanged();
    }

    public void addParty(Party party){
        parties.add(party);
        notifyDataSetChanged(); //todo: change for notify item position changed
    }

    public void clear() {
        parties = new ArrayList<>();
    }
}
