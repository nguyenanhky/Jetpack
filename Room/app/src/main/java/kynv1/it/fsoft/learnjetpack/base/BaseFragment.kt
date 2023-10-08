package kynv1.it.fsoft.learnjetpack.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.IllegalArgumentException

abstract class BaseFragment<VB:ViewBinding>:Fragment() {
    private var _binding :VB? = null
    protected val binding :VB
        get() = _binding ?: throw IllegalArgumentException(
            "binding is only valid between onCreateView and onDestroyView"
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
        observerLiveData()
    }

    open fun observerLiveData() {

    }

    open fun initAction() {

    }

    open fun initView() {

    }

    @CallSuper
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =getViewBinding()
        return binding.root
    }

    abstract fun getViewBinding(): VB

    protected fun showDialog(title:String ,message:String){
        AlertDialog.Builder(requireContext()).apply {
            setTitle(title)
            setMessage(message)
            setNegativeButton("Cancel"){_,_ ->}
            show()
        }
    }
}