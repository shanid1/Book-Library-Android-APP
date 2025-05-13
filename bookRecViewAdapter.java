package com.example.myapplication;

import static com.example.myapplication.BookActivity.BOOK_ID_KEY;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class bookRecViewAdapter extends RecyclerView.Adapter<bookRecViewAdapter.ViewHolder>{
    private MaterialCardView parent;
    private ImageView imgBook;
    private TextView txtName;
    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;
    private String parentActivity;
    private static final String TAG = "bookRecViewAdapter";

    public bookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtName.setText(books.get(position).getName());
        holder.imgBook.setImageResource(books.get(position).getImageResId());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(mContext,BookActivity.class);
                intent.putExtra(BOOK_ID_KEY,books.get(position).getId());

                mContext.startActivity(intent);
            }
        });

        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
            holder.authText.setText(books.get(position).getAuthor());
            holder.txtShortDesc.setText(books.get(position).getShortDesc());
            if(parentActivity.equals("AllBooks")){
                holder.btnDelete.setVisibility(View.GONE);
            }else if(parentActivity.equals("currentlyReading")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to remove this book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeToCurrentlyReading(books.get(position))){
                                    Toast.makeText(mContext,"Removed from Currently read",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else{
                                    Toast.makeText(mContext,"Failed to remove " +books.get(position).getName()+" from already read",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }else if(parentActivity.equals("WantToRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to remove this book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeToWantToRead(books.get(position))){
                                    Toast.makeText(mContext,"Removed from Wishlist",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else{
                                    Toast.makeText(mContext,"Failed to remove " +books.get(position).getName()+" from already read",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }else if(parentActivity.equals("favouriteBooks")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to remove this book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeToFav(books.get(position))){
                                    Toast.makeText(mContext,"Removed from Favourites",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else{
                                    Toast.makeText(mContext,"Failed to remove " +books.get(position).getName()+" from already read",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }else if(parentActivity.equals("AlreadyRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure you want to remove this book?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Utils.getInstance(mContext).removeAlreadyRead(books.get(position))){
                                    Toast.makeText(mContext,"Removed from already read",Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else{
                                    Toast.makeText(mContext,"Failed to remove " +books.get(position).getName()+" from already read",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }

        }
        else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelativeLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private MaterialCardView parent;
        private ImageView imgBook;
        private TextView txtName;
        private ImageView downArrow,upArrow;
        private RelativeLayout expandedRelativeLayout;
        private TextView authText,txtShortDesc,btnDelete;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtBookName);

            downArrow =  itemView.findViewById(R.id.btnDownArrow);
            upArrow =  itemView.findViewById(R.id.btnUpArrow);
            expandedRelativeLayout =  itemView.findViewById(R.id.expandedRelativeLayout);
            authText =  itemView.findViewById(R.id.authText);
            txtShortDesc =  itemView.findViewById(R.id.txtShortDesc);
            btnDelete =  itemView.findViewById(R.id.deleteBtn);


            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }

    }
}
