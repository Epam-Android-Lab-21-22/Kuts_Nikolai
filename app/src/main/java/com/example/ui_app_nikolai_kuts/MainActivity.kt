package com.example.ui_app_nikolai_kuts

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.annotation.AnimRes
import androidx.core.view.ViewCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui_app_nikolai_kuts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private val textAdapter: TextAdapter = TextAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.enteredTextRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            adapter = textAdapter
        }

        viewModel.enteredText.observe(this@MainActivity) { enteredText ->
            textAdapter.characters = enteredText.getTrimmedCharacterList()
        }

        viewModel.clickQuantity.observe(this@MainActivity) { clickQuantity ->
            binding.clickCounterTextView.apply {
                text = clickQuantity.toString()
                setRandomBackgroundColor()
                animateView(animationId = R.anim.counter_animation)
            }
        }

        viewModel.characterQuantity.observe(this@MainActivity) { characterQuantity ->
            binding.characterCounterTextView.apply {
                text = characterQuantity.toString()
                setRandomBackgroundColor()
                animateView(animationId = R.anim.counter_animation)
            }
        }

        binding.clickButton.setOnClickListener { onEnterTextButtonClick() }

        binding.enteredTextEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.setCharacterQuantity(text.toString().length)
            textAdapter.characters = text.toString().getTrimmedCharacterList()
        }
    }

    private fun String.getTrimmedCharacterList(): List<String> {
        return this.trim()
            .split("")
            .drop(1)
            .dropLast(1)
    }

    private fun onEnterTextButtonClick() {
        viewModel.click()
        viewModel.saveText(binding.enteredTextEditText.text.toString().trim())
        binding.clickButton.animateView(R.anim.click_animation)
    }

    private fun View.animateView(@AnimRes animationId: Int) {
        val animation = AnimationUtils.loadAnimation(this@MainActivity, animationId)
        startAnimation(animation)
    }

    private fun View.setRandomBackgroundColor() {
        val colorAssistant = ColorAssistant(context = this@MainActivity)

        ViewCompat.setBackgroundTintList(
            this,
            ColorStateList.valueOf(colorAssistant.getRandomColor())
        )
    }
}