package com.pow.mining.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pow.mining.databinding.InviterItemBinding;
import com.pow.mining.model.Referral;
import com.pow.mining.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReferralAdapter extends RecyclerView.Adapter<ReferralAdapter.MyHolder> {

    private final ArrayList<Referral> referralArrayList;

    public ReferralAdapter(ArrayList<Referral> referralArrayList) {
        this.referralArrayList = referralArrayList;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        InviterItemBinding recyclerRowBinding;

        public MyHolder(@NonNull InviterItemBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InviterItemBinding recyclerRowBinding = InviterItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        Referral referral = referralArrayList.get(position);


        getUserData(referral, holder);


    }

    private void getUserData(Referral referral, MyHolder holder) {

        FirebaseDatabase.getInstance()
                .getReference()
                .child("Users").child(referral.getReceiverId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);

                        if (user != null) {
                            holder.recyclerRowBinding.username.setText(user.getUsername());
                            Picasso.get().load(user.getImage()).into(holder.recyclerRowBinding.profileImage);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }


    @Override
    public int getItemCount() {
        return null != referralArrayList ? referralArrayList.size() : 0;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
