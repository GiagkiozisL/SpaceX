package com.mindera.spacex.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.URLUtil
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mindera.spacex.R
import com.mindera.spacex.data.models.Company
import com.mindera.spacex.data.models.Launch
import com.mindera.spacex.data.repositories.MainRepository
import com.mindera.spacex.databinding.ActivityMainBinding
import com.mindera.spacex.ui.adapter.LaunchesAdapter
import com.mindera.spacex.ui.viewmodels.CompanyViewModel
import com.mindera.spacex.ui.viewmodels.ViewModelFactory
import com.mindera.spacex.utils.Resource

class MainActivity : BaseActivity(), LaunchesAdapter.LaunchListener {

    private lateinit var viewModel: CompanyViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: LaunchesAdapter
    private lateinit var launches: List<Launch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        initAdapter()

        setupLoadingView(binding.mainContainer, "Hold tight") // this will be used for loading animation
        initializeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_all -> {
                refreshAdapter(launches)
                return true
            }
            R.id.action_filter -> {
                // filter successful launched
                val filteredLaunches = launches.filter { it.launchSuccess }
                refreshAdapter(filteredLaunches)
                true
            }
            R.id.action_asc -> {
                // sort particular list asc
                val currentList = adapter.launchList
                val sortAscList = currentList.sortedBy { it.launchDateUnix }
                refreshAdapter(sortAscList)
                return true
            }
            R.id.action_desc -> {
                // sort particular list desc
                val currentList = adapter.launchList
                val sortDescList = currentList.sortedByDescending { it.launchDateUnix }
                refreshAdapter(sortDescList)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun initAdapter() {
        launches = listOf() /// ensures list is not null, if the request will fail
        adapter = LaunchesAdapter(this, launches, this)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.adapter = adapter
    }

    private fun refreshAdapter(launches: List<Launch>) {
        adapter.clear()
        adapter.addAll(launches)
    }

    private fun initializeViewModel() {
        val mainRepository = MainRepository()
        val viewModelFactory = ViewModelFactory(mainRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CompanyViewModel::class.java)

        // fetch company data & launches
        fetchCompanyInfo()
        fetchLaunches()
    }

    private fun fetchCompanyInfo() {
        viewModel.getCompanyInfo()
        observeCompanyInfo()
    }

    private fun fetchLaunches() {
        viewModel.getLaunches()
        observeLaunches()
    }

    private fun renderCompanyData(companyData: Company) {
        var description = getString(R.string.company_description, companyData.name, companyData.founder,
            companyData.founded, companyData.employees, companyData.launchSites, companyData.valuation)
        binding.mainCompanyDetailsTextView.text = description
    }

    private fun openChooserDialog(launch: Launch) {
        val choices = arrayOf(getString(R.string.wikipedia), getString(R.string.video_page))
        val selected = 0
        var position = 0

        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.dialog_link_chooser))
            .setNeutralButton(resources.getString(android.R.string.cancel)) { dialog, which ->
                //
            }
            .setPositiveButton(resources.getString(R.string.open)) { dialog, which ->
                val url = getUrlFromType(launch, position)
                openUrl(url)
            }
            // Single-choice items (initialized with checked item)
            .setSingleChoiceItems(choices, selected) { dialog, which ->
                position = which
            }

            .show()
    }

    private fun getUrlFromType(launch: Launch, pos: Int): String {
        when (pos) {
            0 -> return launch.links.wikipedia.toString()
            1 -> return launch.links.videoLink.toString()
            else -> return ""
        }
    }

    private fun openUrl(url: String) {
        if (!URLUtil.isValidUrl(url)) {
            Toast.makeText(this, "Link url not found", Toast.LENGTH_LONG).show()
            return
        }

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    // region // Observers //
    private fun observeCompanyInfo() {
        viewModel.company.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { renderCompanyData(it) }
                }
                is Resource.Error -> {
                    print("companyInfo Error")
                    hideProgressAnimation()
                }
            }
        })
    }

    private fun observeLaunches() {
        viewModel.launches.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressAnimation()
                    response.data?.let {
                        this.launches = it
                        refreshAdapter(it)
                    }
                }
                is Resource.Error -> {
                    hideProgressAnimation()
                }
                is Resource.Loading -> {
                    showProgressAnimation()
                }
            }
        })
    }

    override fun onItemClicked(launch: Launch) {
        openChooserDialog(launch)
    }
    // endregion // Observers //
}