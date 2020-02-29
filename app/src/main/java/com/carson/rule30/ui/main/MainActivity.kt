package com.carson.rule30.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.carson.rule30.BR
import com.carson.rule30.R
import com.carson.rule30.databinding.ActivityMainBinding
import com.carson.rule30.ui.base.BaseActivity
import com.kennyc.bottomsheet.BottomSheetListener
import com.kennyc.bottomsheet.BottomSheetMenuDialogFragment
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    @Inject
    override lateinit var viewModel: MainViewModel
        internal set

    var mActivityMainBinding: ActivityMainBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    /** Sets Layout File **/
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = viewDataBinding
        mActivityMainBinding?.lifecycleOwner = this
        viewModel.navigator = this
    }

    /**
     * Resets the view, goes back to original camera position
     */
    override fun resetCamera() {
        mActivityMainBinding?.worldView?.resetCamera()
    }

    /**
     * Handles errors. For simplicity, just shows a toast with relevant message.
     *
     * @param throwable - The exception to notify user about.
     */
    override fun handleError(throwable: Throwable) {
        Toast.makeText(this, getString(R.string.error_message, throwable.message), Toast.LENGTH_LONG).show()
    }

    /**
     * Shows the bottom sheet menu dialog.
     */
    override fun showMenu() {
        BottomSheetMenuDialogFragment.Builder(this)
            .setSheet(R.menu.menu_actions)
            .setListener(object: BottomSheetListener {
                override fun onSheetItemSelected(bottomSheet: BottomSheetMenuDialogFragment,
                                                 item: MenuItem,
                                                 `object`: Any?) {
                    when(item.itemId) {
                        R.id.item_execute_rule_30 -> {
                            viewModel.executeRule30()
                        }
                        R.id.item_execute_rule_30_from_center -> {
                            if (viewModel.pointsLiveData.value?.size == 1) {
                                handleError(Exception(getString(R.string.error_single_point_center_out)))
                            }
                            viewModel.executeRule30CenterOut()
                        }
                        R.id.item_randomize_world -> {
                            viewModel.randomlyGenerateWorld()
                        }
                        R.id.item_reset_world -> {
                            viewModel.resetWorld()
                        }
                        R.id.item_shuffle_world -> {
                            if (viewModel.pointsLiveData.value?.size == 1) {
                                handleError(Exception(getString(R.string.error_single_point_shuffle)))
                            } else {
                                viewModel.shuffleWorld()
                            }
                        }
                    }
                }

                override fun onSheetDismissed(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?, dismissEvent: Int) {}
                override fun onSheetShown(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?) {}
            }).show(supportFragmentManager)
    }
}
