package com.ieatta.android.extensions.storage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by djzhang on 12/5/15.
 */
public class TableViewConfiguration {
    public Builder builder;
    public TableViewConfiguration(Builder builder) {
        this.builder = builder;
    }

    public static class Builder {

        public final Context context;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
        }

        /** Builds configured {@link TableViewConfiguration} object */
        public TableViewConfiguration build() {
            return new TableViewConfiguration(this);
        }
        public RecyclerView.LayoutManager manager;
        public RecyclerView.ItemDecoration decoration;
        protected Builder setLayoutManager(RecyclerView.LayoutManager manager) {
            this.manager = manager;
            return this;
        }

        protected Builder setItemDecoration(RecyclerView.ItemDecoration decoration) {
            this.decoration = decoration;
            return this;
        }
    }
}
