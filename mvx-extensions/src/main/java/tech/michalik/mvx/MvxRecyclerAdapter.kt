package tech.michalik.mvx

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by jaroslawmichalik on 15/05/2020
 */
class MvxRecyclerAdapter<Displayable, Binding : ViewDataBinding>(
    private val clickCallback: (Displayable) -> Unit = {},
    private val longClick: (Displayable) -> Unit = {},
    private val viewItemBr: Int,
    private val provideBinding: (ViewGroup, ViewType) -> Binding
) :
    RecyclerView.Adapter<BindableViewHolder<Displayable>>() {


    var items: List<Displayable> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindableViewHolder<Displayable> {
        return object : BindableViewHolder<Displayable>(provideBinding(parent, viewType)) {
            override fun bind(viewModel: Displayable) {
                binding.setVariable(viewItemBr, viewModel)
                super.bind(viewModel)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BindableViewHolder<Displayable>, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            clickCallback(items[position])
        }
        holder.itemView.setOnLongClickListener {
            longClick(items[position])
            true
        }
    }
}

typealias ViewType = Int