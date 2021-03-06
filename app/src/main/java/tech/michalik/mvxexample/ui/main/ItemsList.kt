package tech.michalik.mvxexample.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.michalik.mvx.MvxRecyclerAdapter
import tech.michalik.mvxexample.BR
import tech.michalik.mvxexample.databinding.ItemLayoutBinding

/**
 * Created by jaroslawmichalik on 15/05/2020
 */
class ItemsListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val itemsAdapter by lazy {
        MvxRecyclerAdapter<ItemDisplayable, ItemLayoutBinding>(viewItemBr = BR.viewItem) { viewGroup, _ ->
            ItemLayoutBinding.inflate(LayoutInflater.from(context), viewGroup, false)
        }
    }

    init {
        adapter = itemsAdapter
        layoutManager = LinearLayoutManager(context, VERTICAL, true)
    }

    fun setItems(list: List<ItemDisplayable>) {
        itemsAdapter.items = list
    }
}

data class ItemDisplayable(
    val name: String,
    val id: String
)