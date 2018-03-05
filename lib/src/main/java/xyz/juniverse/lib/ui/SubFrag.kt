package xyz.juniverse.lib.ui

import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import xyz.juniverse.lib.R
import xyz.juniverse.lib.console

/**
 * Created by juniverse on 05/03/2018.
 */
open class SubFrag: Fragment(), View.OnClickListener {

    companion object {
        val tag = "subfrag"
        var holderId = 0
    }

    /**
     * starting next fragment
     * name : for popping to this specific fragment (see: popToFragment)
     * anim... : for custom transition animation
     */
    protected fun startFragment(fragment: SubFrag, name: String = "",
                                animEnter: Int = R.anim.slide_from_right, animExit: Int = R.anim.slide_to_left,
                                animPopEnter: Int = R.anim.slide_from_left, animPopExit: Int = R.anim.slide_to_right) {
        if (holderId == 0) {
            console.e("something is wrong. no holder id!")
            return
        }

        fragmentManager.beginTransaction()
                .setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit)
                .addToBackStack(name)
                .replace(holderId, fragment, tag)
                .commit()
    }

    /**
     * pop to the specific fragment.
     * only exclusive, meaning that the fragment will be showing
     */
    protected fun popToFragment(name: String) {
        fragmentManager.popBackStack(name, 0)
    }

    /**
     * should be called in onCreateView.
     * this will register all the Button or ImageButton views with onClickListener.
     * the event will be sent to handleButton (see: handleButton)
     */
    protected fun registerButtons(view: View) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                registerButtons(view.getChildAt(i))
            }
        } else if (view is Button || view is ImageButton) {
            view.setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        handleButton(view)
    }

    /**
     * if registerButtons is called, all the button click event will be sent here.
     * should be override to use
     */
    open fun handleButton(view: View) {
        console.d("handling click", view.id)
    }

    /**
     * if want to use back button event, override this function.
     * if true is returned, the event will be consumed, otherwise passed to super.
     */
    open fun onBackPressed(): Boolean {
        return false
    }
}