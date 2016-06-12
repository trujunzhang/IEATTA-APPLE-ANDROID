package org.wikipedia.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.MenuItem;


/** Boilerplate for a {@link android.support.v4.app.FragmentActivity} containing a single stack of
 *  Fragments. */
public abstract class BaseSingleFragmentActivity<T> extends ThemedActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        if (!isFragmentCreated()) {
            T fragment = createFragment();
            if(fragment instanceof Fragment){
                Class<?> aClass = fragment.getClass();
            }
            addFragment(fragment);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return ActivityUtil.defaultOnOptionsItemSelected(this, item)
                || super.onOptionsItemSelected(item);
    }

    protected abstract void addFragment(T fragment);

    protected abstract T createFragment();

    /** @return The Fragment added to the stack. */
    protected abstract T getFragment();

    /** @return The resource layout to inflate which must contain a {@link android.view.ViewGroup}
     * whose ID is {@link #getContainerId()}. */
    @LayoutRes
    protected int getLayout() {
//        return R.layout.activity_single_fragment;
        return -1;
    }

    /** @return The resource identifier for the Fragment container. */
    @IdRes
    protected int getContainerId() {
//        return R.id.fragment_container;
        return -1;
    }

    protected boolean isFragmentCreated() {
        return getFragment() != null;
    }
}