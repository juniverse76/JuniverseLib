package xyz.juniverse.lib.ui

import android.support.v7.app.AppCompatActivity

/**
 * Created by juniverse on 05/03/2018.
 *
 * a simple Activity
 */
open class FragActivity : AppCompatActivity() {

    /**
     * starting initial fragment.
     * rest of navigation is done in SubFrag
     * holderId : a ViewGroup that will hold the fragment
     */
    fun startFragment(fragment: SubFrag, holderId: Int) {
        // save holder id
        SubFrag.holderId = holderId
        supportFragmentManager.beginTransaction().add(holderId, fragment, SubFrag.tag).commit()
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentByTag(SubFrag.tag) as? SubFrag

        // none or didn't process back button
        if (frag == null || !frag.onBackPressed())
            super.onBackPressed()
    }
}