package com.serdnito.pokeapi.core.mvp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.serdnito.pokeapi.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.frame_failure.*
import kotlinx.android.synthetic.main.frame_loading.*

@SuppressLint("Registered")
abstract class BaseActivity(
    private val graphResId: Int
) : AppCompatActivity(R.layout.activity_base), BaseView {

    private val navController by lazy {
        // As per an issue in library (version 2.2.1), when using FragmentContainerView, we need to
        // find the NavController using findFragmentById() rather than using findNavController()
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
    }

    override fun hideLoading() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        frameLoading?.visibility = View.GONE
    }

    private fun initToolbar() {
        navController.setGraph(graphResId)
    }

    override fun showError(message: String, retryAction: () -> Unit) {
        viewStubFailure?.run {
            setOnInflateListener { _, _ -> showFailureFrame(message, retryAction) }
            inflate()
        } ?: showFailureFrame(message, retryAction)
    }

    private fun showFailureFrame(message: String, retryAction: () -> Unit) {
        fragmentContainerView?.visibility = View.GONE
        frameFailure?.visibility = View.VISIBLE
        imgClose?.setOnClickListener { onBackPressed() }
        txtFailureMessage?.text = message
        btnFailureRetry?.setOnClickListener {
            frameFailure?.visibility = View.GONE
            fragmentContainerView?.visibility = View.VISIBLE
            retryAction()
        }
    }

    override fun showLoading() {
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        frameLoading?.visibility = View.VISIBLE
    }

    override fun showMessage(message: String?) {
        fragmentContainerView?.run {
            Snackbar.make(
                this,
                message ?: context.getString(R.string.error_default),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

}
