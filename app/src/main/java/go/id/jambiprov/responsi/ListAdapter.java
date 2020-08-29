package go.id.jambiprov.responsi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> implements Filterable {
        ArrayList<DataBerita> result;
        ArrayList<DataBerita> resultAll;
        Context context;

        public ListAdapter(Context context, ArrayList<DataBerita> result) {
            super();
            this.result = result;
            this.context = context;
            this.resultAll = new ArrayList<>(result);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.list_item,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String BaseGambar = "http://corona.jambiprov.go.id/api/responsi/gambar/";
            holder.id_lapak.setText(result.get(position).getJudul());
            holder.ket.setText(result.get(position).getIsi()+"...");
            if(result.get(position).getGambar()!=""){
                Picasso.get().load(BaseGambar+result.get(position).getGambar()).into(holder.gambar);
            }else{
                Picasso.get().load(R.drawable.splash_bg).into(holder.gambar);
            }
        }

        @Override
        public int getItemCount() {
            return result.size();
        }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList filterList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filterList.addAll(resultAll);
            }else{
                for(DataBerita dataBerita:resultAll){
                    if(dataBerita.getJudul().toString().toLowerCase().contains(constraint.toString().toLowerCase()) || dataBerita.getIsi().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterList.add(dataBerita);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            result.clear();
            result.addAll((Collection<? extends DataBerita>) results.values);
            notifyDataSetChanged();
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView id_lapak,ket;
            ImageView gambar;
            public MyViewHolder(View itemView) {
                super(itemView);
                id_lapak = itemView.findViewById(R.id.no_lapak);
                ket      = itemView.findViewById(R.id.ket_lapak);
                gambar   = itemView.findViewById(R.id.gambar);
                itemView.setOnClickListener(this);
            }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("IDBERITA", result.get(this.getLayoutPosition()).getIdberita().toString());
            context.startActivity(intent);
        }
    }
}
