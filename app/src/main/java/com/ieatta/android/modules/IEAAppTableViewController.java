package com.ieatta.android.modules;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.DTTableViewManager;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAAppTableViewController extends AppCompatActivity {
    private IEAAppTableViewController self = this;
    protected IEAAppTableViewController navigationController = this;
    private RecyclerView recyclerView;
    protected TextView rightBarButtonItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(self.getContentView());

        self.rightBarButtonItem = (TextView) findViewById(R.id.rightBarButtonItem);

        EnvironmentUtils.sharedInstance.registerCurrentActivity(this);

        if (hasRecycleView() == true) {
            this.recyclerView = (RecyclerView) findViewById(R.id.recyleView);
            this.recyclerView.setHasFixedSize(true);
        }
    }

    protected void setRightBarButtonItem(int titleId, View.OnClickListener clickListener) {
        self.rightBarButtonItem.setVisibility(View.VISIBLE);
        self.rightBarButtonItem.setText(titleId);
        self.rightBarButtonItem.setOnClickListener(clickListener);
    }

    public void popViewControllerAnimated(boolean animated) {
        self.finish();
    }

    /**
     * Default content view.
     *
     * @return
     */
    protected int getContentView() {
        return R.layout.table_controller_view;
    }

    protected boolean hasRecycleView() {
        return true;
    }

    protected void startManagingWithDelegate(DTTableViewManager manager) {
        self.recyclerView.setAdapter(manager.getAdapter());
        self.recyclerView.setLayoutManager(manager.configuration.builder.manager);
        self.recyclerView.addItemDecoration(manager.configuration.builder.decoration);
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
