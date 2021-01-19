package com.xingzhi.android.biz.today.banner.model

import com.xingzhi.android.biz.model.Model

/**
 * Created by xiedongdong on 2021/01/18
 */
class Banner(
    var id: Int,
    var title: String,
    var imagePath: String,
    var desc: String,
    var isVisible: Int,
    var order: Int,
    var type: Int,
    var url: String
) : Model()