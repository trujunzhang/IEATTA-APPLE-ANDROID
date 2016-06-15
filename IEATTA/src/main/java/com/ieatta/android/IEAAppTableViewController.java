package com.ieatta.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.extensions.storage.DTTableViewManager;

public class IEAAppTableViewController extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getContentView());

        EnvironmentUtils.sharedInstance.registerCurrentActivity(this);

        if (this.hasRecycleView() == true) {
            this.recyclerView = (RecyclerView) findViewById(R.id.recyleView);
            this.recyclerView.setHasFixedSize(true);
        }
        if (this.shouldLeftBarButtonItem() == true) {
            this.setLeftBarButtonItem(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (IEAAppTableViewController.this.navigationShouldPopOnBackButton() == true) {
                        IEAAppTableViewController.this.popViewControllerAnimated(true);
                    }
                }
            });
        }
    }

    protected boolean shouldLeftBarButtonItem() {
        return true;
    }

    protected void setLeftBarButtonItem(View.OnClickListener clickListener) {
//        this.leftBarButtonItem.setVisibility(View.VISIBLE);
//        this.leftBarButtonItem.setOnClickListener(clickListener);
    }

    protected void setRightBarButtonItem(int titleId, View.OnClickListener clickListener) {
//        this.rightBarButtonItem.setVisibility(View.VISIBLE);
//        this.rightBarButtonItem.setText(titleId);
//        this.rightBarButtonItem.setOnClickListener(clickListener);
    }

    public void popViewControllerAnimated(boolean animated) {
        this.finish();
    }

    public void dismissViewControllerAnimated(boolean flag, Object o) {
        this.finish();
    }

    /**
     * Default content view.
     */
    protected int getContentView() {
        return R.layout.table_controller_view;
    }

    protected boolean hasRecycleView() {
        return true;
    }

    protected void startManagingWithDelegate(DTTableViewManager manager) {
        this.recyclerView.setAdapter(manager.getAdapter());
        this.recyclerView.setLayoutManager(manager.configuration.builder.manager);
        this.recyclerView.addItemDecoration(manager.configuration.builder.decoration);
    }

    public boolean navigationShouldPopOnBackButton() {
        return true;
    }

    public boolean showNavigationTitle() {
        return true;
    }

    public boolean havePhotoGallery() {
        return false;
    }

    public boolean shouldShowHUD() {
        return false;
    }

    public void didReceiveMemoryWarning() {
        // Dispose of any resources that can be recreated.
    }


}
