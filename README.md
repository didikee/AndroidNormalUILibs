# AndroidNormalUILibs

### ListViewForScrollView

ListView 嵌套在 ScrollView 中,可以将ScrollView的onTouch事件交给 ListViewForScrollView 过滤.

使用方法:
	mShopAdapter = new GoodsDetailShopListAdapter(shop_list,this);
	//调用setScrollView(mscrollview)
    mShopListView.setScrollView(mSScrollView);
    mShopListView.setAdapter(mShopAdapter);
