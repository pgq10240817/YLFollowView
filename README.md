# YLFollowView
YLFollowView


效果如下图所示：

![](/assets/intro.gif)

## 实现说明


## 添加依赖


1. 添加**YLFollowView**依赖

   ```groovy
   compile 'com.github.pgq10240817:YLFollowView:1.0.2'
   ```

### 使用示例

```
 <app.yhpl.widgets.YLFollowView
        android:id="@+id/followView"
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:layout_gravity="center"
        app:ylFolloweViewFollowBackGround="@drawable/selector_ffffff_00cccc"
        app:ylFolloweViewFollowMask="@drawable/shape_rectangle_ffffff_border_00cccc_corner_2"
        app:ylFolloweViewFollowText="关注"
        app:ylFolloweViewFollowTextColor="@color/selector_ffffff_00cccc"
        app:ylFolloweViewFollowedBackGround="@drawable/shape_rectangle_a7a7a7_corner_2"
        app:ylFolloweViewFollowedMask="@drawable/shape_rectangle_a7a7a7_corner_2"
        app:ylFolloweViewFollowedText="已关注"
        app:ylFolloweViewFollowedTextColor="#ffcccccc">

        <app.yhpl.widgets.YLLoadingTextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:gravity="center"
            android:textSize="18dp" />
    </app.yhpl.widgets.YLFollowView>
```


