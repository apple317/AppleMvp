/**
 * 公共父类弹出框
 */
package com.base.applemvp.common

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup

/**
 * @author applehsp
 */
abstract class BaseDialog(var activity: Activity, style: Int) : Dialog(activity, style) {

    internal  var contentView: View

    override fun dismiss() {
        super.dismiss()
    }

    private fun getDensity(context: Context): Float {
        val resources = context.resources
        val dm = resources.displayMetrics
        return dm.density
    }

    protected abstract fun layoutId(): Int
    protected abstract fun initView()
    protected abstract fun initData()
    /**
     * 显示对话框
     */
    fun goShow() {
        setCanceledOnTouchOutside(true)
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        show()
    }

    init {
        contentView = layoutInflater.inflate(layoutId(), null)
        setContentView(contentView)
        initView()
        initData()
    }
}