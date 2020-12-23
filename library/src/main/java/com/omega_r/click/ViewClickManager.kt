package com.omega_r.click

import android.view.View
import androidx.annotation.IdRes

/**
 * Created by Anton Knyazev on 13.11.2020.
 */
class ViewClickManager(private val view: View) : ClickManager() {

    init {
        viewFindable = ViewFindable()
    }

    private inner class ViewFindable : OmegaViewFindable {
        override fun <T : View> findViewById(id: Int): T? = view.findViewById(id)
    }

}

private class WrapperClickListener(private val block: () -> Unit) : View.OnClickListener {

    override fun onClick(v: View?) {
        if (ClickManager.canClickHandle()) {
            block()
        }
    }

}

fun <T : View> T.setClickListenerOptional(@IdRes id: Int, block: () -> Unit) = setClickListener(id, true, block)

fun <T : View> T.setClickListener(@IdRes id: Int, block: () -> Unit) = setClickListener(id, false, block)

fun <T : View> T.setClickListener(@IdRes id: Int, optional: Boolean, block: () -> Unit) {
    val clickManager = getTag(R.id.omega_click_manager) as? ClickManager ?: run {
        ViewClickManager(this).also {
            setTag(R.id.omega_click_manager, it)
        }
    }
    clickManager.setClickListener(id, block, optional)
}

fun <T : View> T.setClickListener(block: () -> Unit) {
    setOnClickListener(WrapperClickListener(block))
}
