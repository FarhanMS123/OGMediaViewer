package id.adipati.ogmediaviewer;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVPostsAdapter extends RecyclerView.Adapter<RVPostsAdapter.ViewHolder> {

    private ArrayList<Post> posts;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_photos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVPostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.getName().setText(post.name);
        Picasso.get()
                .load(post.thumbnail)
                .into(holder.getThumbnail());
        holder.setId(post.id);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final Context context;

        private final TextView txtName;
        private final ImageView imgThumbnail;
        private int id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();

            txtName = (TextView) itemView.findViewById(R.id.txtName);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.imgThumbnail2);

            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("status", "from_id");
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        }

        public TextView getName(){
            return txtName;
        }

        public ImageView getThumbnail(){
            return imgThumbnail;
        }

        public void setId(int id){
            this.id = id;
        }
    }

    public RVPostsAdapter(ArrayList<Post> posts){
        this.posts = posts;
    }
}
