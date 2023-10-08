package kynv1.it.fsoft.learnjetpack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import java.lang.IllegalArgumentException

abstract class BaseDialogFragment<VB: ViewBinding> : DialogFragment() {
    private var _binding:VB? = null
    protected  val binding:VB
        get() = _binding ?: throw IllegalArgumentException(
            "binding is only valid between onCreateView and onDestroyView"
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=getViewBinding()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
    }

    protected open fun initAction(){
    }

    protected open fun initView(){}

    abstract fun getViewBinding(): VB

}