# AndroidNormalUILibs

### Gradle
```
compile 'com.didikee:UILibrary:0.8.9'
```

### ListViewForScrollView

ListView 嵌套在 ScrollView 中,可以将ScrollView的onTouch事件交给 ListViewForScrollView 过滤.

使用方法:
```
mShopAdapter = new GoodsDetailShopListAdapter(shop_list,this);
//调用setScrollView(mscrollview)
mShopListView.setScrollView(mSScrollView);
mShopListView.setAdapter(mShopAdapter);
```
API:
* setScrollView(ScrollView mParentScrollView)
* setInterceptTouchEventEnable(boolean isEnable)//动态的开启或关闭

***
### WaitFinishTextView

一个TextView 点击后变为不可点击状态,直到你手动调用 ```onFinish()```

API:
* onFinish() 在网络请求接口结束的回调(onFinish())中调用此方法,让TextView重新可点击
