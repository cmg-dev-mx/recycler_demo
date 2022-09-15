package mx.dev.shell.android.recyclerviewmultiple.flow.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import mx.dev.shell.android.recyclerviewmultiple.core.model.ItemAlert
import mx.dev.shell.android.recyclerviewmultiple.core.model.ItemCommon
import mx.dev.shell.android.recyclerviewmultiple.core.model.ItemPermission
import mx.dev.shell.android.recyclerviewmultiple.databinding.ItemAlertBinding
import mx.dev.shell.android.recyclerviewmultiple.databinding.ItemCommonBinding
import mx.dev.shell.android.recyclerviewmultiple.databinding.ItemPermissionBinding

class ItemAdapter(
    private val list: List<ItemCommon>,
    private val onItemClick: (ItemCommon) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun getItemViewType(position: Int) = when (list[position]) {
        is ItemAlert -> ITEM_ALERT
        is ItemPermission -> ITEM_PERMISSION
        else -> ITEM_COMMON
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            ITEM_ALERT -> {
                ItemAlertViewHolder(
                    ItemAlertBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            ITEM_PERMISSION -> {
                ItemPermissionViewHolder(
                    ItemPermissionBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                ItemCommonViewHolder(
                    ItemCommonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(list[position], onItemClick)

    override fun getItemCount() = list.size

    inner class ItemCommonViewHolder(private val binding: ItemCommonBinding) : ItemViewHolder(binding) {

        override fun bind(item: ItemCommon, onItemClick: (ItemCommon) -> Unit) {
            binding.apply {
                itemCommonTitle.text = item.title
                itemCommonDescription.text = item.content
                itemCommonContainer.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

    inner class ItemAlertViewHolder(private val binding: ItemAlertBinding) : ItemViewHolder(binding) {

        override fun bind(item: ItemCommon, onItemClick: (ItemCommon) -> Unit) {
            binding.apply {
                itemAlertTitle.text = (item as ItemAlert).title
            }
        }
    }

    inner class ItemPermissionViewHolder(private val binding: ItemPermissionBinding) : ItemViewHolder(binding) {

        override fun bind(item: ItemCommon, onItemClick: (ItemCommon) -> Unit) {
            binding.apply {
                itemPermissionTitle.text = (item as ItemPermission).title
                itemPermissionBtn.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

    abstract class ItemViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: ItemCommon, onItemClick: (ItemCommon) -> Unit)
    }
}

private const val ITEM_COMMON = 0
private const val ITEM_ALERT = 1
private const val ITEM_PERMISSION = 2