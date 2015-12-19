package com.ieatta.android.modules;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.DTTableViewManager;

/**
 * Created by djzhang on 12/1/15.
 */
public class IEAAppTableViewController extends AppCompatActivity {
    private IEAAppTableViewController self = this;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(self.getContentView());



        EnvironmentUtils.sharedInstance.registerCurrentActivity(this);

        if(hasRecycleView() == true) {
            this.recyclerView = (RecyclerView) findViewById(R.id.recyleView);
            this.recyclerView.setHasFixedSize(true);
        }
    }

    /**
     * Default content view.
     * @return
     */
    protected int getContentView(){
        return R.layout.table_controller_view;
    }

    protected boolean hasRecycleView(){
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
