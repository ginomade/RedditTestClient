package com.gino.reddittestclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gino.reddittestclient.R
import com.gino.reddittestclient.utils.RedditNewsItem
import com.gino.reddittestclient.utils.getFriendlyTime
import com.gino.reddittestclient.utils.loadImg
import kotlinx.android.synthetic.main.fragment_detail.*

private const val ARG_PARAM1 = "param1"

class DetailFragment : Fragment() {

    private var param1: Bundle? = null

    private var item: RedditNewsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getBundle(ARG_PARAM1)
            item = param1?.getParcelable("SELECTED_ITEM")
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item?.thumbnail?.let { img_thumbnail?.loadImg(it) }
        description.setText(item?.title)
        author.setText(item?.author)
        comments.setText("${item?.numComments} comments")
        time.text = item?.created?.getFriendlyTime()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Bundle?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putBundle(ARG_PARAM1, param1)
                }
            }
    }
}