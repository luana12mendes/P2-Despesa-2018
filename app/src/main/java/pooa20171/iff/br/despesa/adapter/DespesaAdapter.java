package pooa20171.iff.br.despesa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pooa20171.iff.br.despesa.R;
import pooa20171.iff.br.despesa.model.Despesa;

public class DespesaAdapter extends RecyclerView.Adapter {

    private List<Despesa> despesas;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public DespesaAdapter(List<Despesa> despesas, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.despesas = despesas;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_despesa, parent, false);
        DespesaViewHolder DespesaViewHolder = new DespesaViewHolder(view);
        return DespesaViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        DespesaViewHolder holder = (DespesaViewHolder) viewHolder;

        Despesa despesa  = despesas.get(position) ;

        holder.nomeLivro.setText(despesa.getNome());
        holder.nomeAutor.setText(despesa.getAutor());
        holder.descricao.setText(despesa.getDescricao());



    }

    @Override
    public int getItemCount() {

        return despesas.size();
    }

    private void updateItem(int position) {

    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {

    }

    public class DespesaViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeLivro;
        private final TextView nomeAutor;
        private final TextView descricao;


        public DespesaViewHolder(View itemView) {
            super(itemView);
            nomeLivro = (TextView) itemView.findViewById(R.id.nomeLivro);
            nomeAutor = (TextView) itemView.findViewById(R.id.nomeAuto);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(despesas.get(getLayoutPosition()));

                }
            });


        }
    }
}