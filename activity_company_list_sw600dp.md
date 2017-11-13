<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:tools="http://schemas.android.com/tools"
              android:id="@+id/company_list"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:layout_marginRight="16dp"
              android:layout_marginLeft="16dp"
              android:baselineAligned="false"
              android:divider="?android:attr/dividerHorizontal"
              android:orientation="horizontal"
              android:showDividers="middle"
              tools:content="org.ptccocp.jsv.videogamedatabase_companies.CompanyListActivity">

    <!-- This layout is a two-pane layout for the Companies
    master/detail flow  -->

    <FrameLayout
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:id="@+id/company_list_container"
        android:layout_weight="3"
        android:layout_gravity="left"/>

    <FrameLayout
        android:id="@+id/company_detail_container"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_weight="3"
        android:layout_gravity="right"/>
</LinearLayout>
