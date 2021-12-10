package com.example.musicproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter {
    public class MusicAdapter extends RecyclerView.Adapter {

        private ArrayList<Music> musicData;
        private View.OnClickListener onClickListener;


        public class MusicViewHolder extends RecyclerView.ViewHolder {

            public TextView textViewMusic;
            public TextView textViewCity;


            public ContactViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewContact = itemView.findViewById(R.id.textViewName);
                textViewCity = itemView.findViewById(R.id.textViewCity);
                itemView.setTag(this);
                itemView.setOnClickListener(onClickListener);
            }

            public TextView getTextViewContact() {
                return textViewContact;
            }

            public TextView getTextViewCity() {return textViewCity; }

        }

        public void setOnClickListener(View.OnClickListener listener) {
            onClickListener = listener;
        }

        public  ContactAdapter(ArrayList<Contact> arrayList) {
            contactData = arrayList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view,parent,false);
            return new ContactViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ContactViewHolder cvh = (ContactViewHolder) holder;
            cvh.getTextViewContact().setText(contactData.get(position).getName());
            cvh.getTextViewCity().setText(contactData.get(position).getCity());
        }

        @Override
        public int getItemCount() {
            return contactData.size();
        }
}
