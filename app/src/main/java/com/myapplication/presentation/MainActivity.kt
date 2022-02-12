package com.myapplication.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.data.cache.SharedPreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.myapplication.core.ScreenState
import com.myapplication.domain.models.character.CharacterEntity
import  androidx.activity.viewModels
import com.myapplication.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mViewModel: MainActivityViewModel by viewModels()
    private var mAdapter: CharactersAdapter? = null

    @Inject
    lateinit var sharedPrefs: SharedPreferencesManager

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initStates()
    }


    private fun initStates(){
        mViewModel.state.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(state: ScreenState<MainActivityScreenState>?){
        when(state) {
            is ScreenState.Loading -> {
                //pb_login.visibility = View.VISIBLE
                toggleControls(false)
            }
            is ScreenState.Render -> {
                toggleControls(true)
                //pb_login.visibility = View.GONE
                renderScreenState(state.renderState)
            }
        }
    }

    private fun renderScreenState(screenState: MainActivityScreenState) {
        when(screenState) {
            is MainActivityScreenState.Init -> {
                mViewModel.obtainCharacters()
            }
            is MainActivityScreenState.ErrorShowCharacters ->{

                Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
            }
            is MainActivityScreenState.ShowCharacters ->{
                updateAdapter(screenState.charactersListEntity)
            }


        }
    }

    private fun updateAdapter(characters: List<CharacterEntity>) {
        mAdapter = CharactersAdapter(characters)
        binding.rvCharacters.adapter = mAdapter
        binding.rvCharacters.layoutManager = LinearLayoutManager(this)
    }

    private fun toggleControls(enabled:Boolean) {

    }


}