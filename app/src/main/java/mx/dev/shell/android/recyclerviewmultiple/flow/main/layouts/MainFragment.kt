package mx.dev.shell.android.recyclerviewmultiple.flow.main.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import mx.dev.shell.android.recyclerviewmultiple.core.model.ItemAlert
import mx.dev.shell.android.recyclerviewmultiple.core.model.ItemCommon
import mx.dev.shell.android.recyclerviewmultiple.core.model.ItemPermission
import mx.dev.shell.android.recyclerviewmultiple.databinding.FragmentMainBinding
import mx.dev.shell.android.recyclerviewmultiple.flow.main.adapter.ItemAdapter

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val items = listOf(
        ItemCommon(
            title = "Option 1",
            content = "This is the option 1"
        ),
        ItemCommon(
            title = "Option 2",
            content = "This is the option 2"
        ),
        ItemAlert(
            title = "Alert. You require to make something here!"
        ),
        ItemPermission("Go to ask permission")
    )

    private val itemAdapter = ItemAdapter(items) {
        when(it) {
            is ItemPermission -> {
                Snackbar.make(binding.mainContainer,"Item to ask permission pressed",Snackbar.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Snackbar.make(binding.mainContainer,"Item pressed",Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = itemAdapter
            this.setHasFixedSize(true)
        }

        return binding.root
    }
}