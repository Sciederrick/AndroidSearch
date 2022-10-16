package ke.derrick.mysearch

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import ke.derrick.mysearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        toolbar = binding.myToolbar

        setContentView(binding.root)

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_items, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu!!.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.isIconifiedByDefault = false

        val componentName = ComponentName(this, SearchableActivity::class.java)
        val searchableInfo = searchManager.getSearchableInfo(componentName)

        Log.d(this::class.java.name, "$searchableInfo")

        searchView.setSearchableInfo(searchableInfo)

        return super.onCreateOptionsMenu(menu)
    }
}