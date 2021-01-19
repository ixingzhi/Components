package com.xingzhi.android.biz.today.banner.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xingzhi.android.biz.today.R
import com.xingzhi.android.biz.today.banner.model.Banner
import kotlinx.android.synthetic.main.biz_today_item_banner_list.view.*

/**
 * Created by xiedongdong on 2021/01/19
 */
class BannerListAdapter :
    BaseQuickAdapter<Banner, BaseViewHolder>(R.layout.biz_today_item_banner_list) {

    override fun convert(holder: BaseViewHolder, item: Banner) {
        Glide.with(context).load(item.imagePath).into(holder.itemView.mIvCover)
        holder.itemView.mTvTitle.text = item.title
    }

}