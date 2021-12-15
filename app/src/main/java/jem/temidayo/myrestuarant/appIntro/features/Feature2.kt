package jem.temidayo.myrestuarant.appIntro.features

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jem.temidayo.myrestuarant.R


/**
 * A simple [Fragment] subclass.
 * Use the [Feature2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Feature2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.delivery_slide_layout, container, false)
    }
}