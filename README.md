#![Logo](https://github.com/xiaopansky/LinearLineWrapLayout/raw/master/app/src/main/res/drawable-mdpi/ic_launcher.png) LinearLineWrapLayout

这是Android上的一个线性自动换行布局控件

![sample](https://github.com/xiaopansky/LinearLineWrapLayout/raw/master/docs/sample.png)

####示例图源码：
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="8dp"
    android:orientation="vertical">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="线性自动换行布局："
        android:layout_marginTop="8dp"
        android:layout_marginBottom="8dp"/>
    <me.xiaopan.android.linearlinewraplayout.LinearLineWrapLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@drawable/panel_normal"
        android:padding="4dp">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
    </me.xiaopan.android.linearlinewraplayout.LinearLineWrapLayout>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="关键字容器："
        android:layout_marginTop="16dp"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1.继承自线性自动换行布局，并开启了调整子View宽度充满父View宽度功能"
        android:layout_marginTop="4dp"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="2.可监听子View的点击事件"
        android:layout_marginTop="4dp"
        android:layout_marginBottom="8dp"/>
    <me.xiaopan.android.linearlinewraplayout.KeywordContainer
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@drawable/panel_normal"
        android:padding="4dp">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="我是关键字"
            android:gravity="center"
            android:background="#FFA500"
            android:textColor="@android:color/white"
            android:padding="8dp"
            android:layout_margin="4dp"/>
    </me.xiaopan.android.linearlinewraplayout.KeywordContainer>
</LinearLayout>
```

###Downloads
>* [android-linear-line-wrap-layout-1.0.1.jar](https://github.com/xiaopansky/LinearLineWrapLayout/raw/master/releases/android-linear-line-wrap-layout-1.0.1.jar)
>* [android-linear-line-wrap-layout-1.0.1-with-src.jar](https://github.com/xiaopansky/LinearLineWrapLayout/raw/master/releases/android-linear-line-wrap-layout-1.0.1-with-src.jar)

###Change Log
#### 1.0.1
>* 优化测量逻辑
>* 修复当明确指定宽高时，不显示子View的BUG

##License
```java
/*
* Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
```