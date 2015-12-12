package com.yelp.android.ui.activities.mutatebiz;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;


import com.ieatta.android.R;
import com.yelp.android.ui.widgets.SpannedTextView;
import java.io.Serializable;
import java.util.ArrayList;

public class Field extends SpannedTextView

{
    protected Bundle a = new Bundle();
    private int g;
    private int h;
    private int i;


    public Field(Context paramContext)
    {
        this(paramContext, null);
    }

    public Field(Context paramContext, AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 16842884);
    }

    public Field(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet,
                new int[] { R.attr.attachmentIcon,R.attr.position,R.attr.emptyText},  // attribute[s] to access
                paramInt,
                paramInt);  // Style to access

//        TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, b.AddBusinessField, paramInt, paramInt);
        setAttributes(localTypedArray);
        localTypedArray.recycle();
    }

    private boolean a()
    {
        return this.a.getParcelable("key.attachment") != null;
    }

    private boolean b()
    {
        return this.a.get("key.data") != null;
    }

    public void a(Uri paramUri)
    {
        this.a.putParcelable("key.attachment", paramUri);
        a(getText());
    }

    protected void a(CharSequence paramCharSequence)
    {
        if ((!b()) || (TextUtils.isEmpty(paramCharSequence)));
        for (int k = this.g; ; k = this.h)
        {
            if (a())
                k = this.i;
            if (k != 0)
                setCompoundDrawablesWithIntrinsicBounds(k, 0, 0, 0);
//            if (this.j != null)
//                this.j.a(b(), a());
            return;
        }
    }

    public void a(CharSequence paramCharSequence, Parcelable paramParcelable)
    {
        this.a.putParcelable("key.data", paramParcelable);
        setText(paramCharSequence);
    }

    public void a(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
    {
        this.a.putCharSequence("key.data", paramCharSequence2);
        setText(paramCharSequence1);
    }

    public void a(CharSequence paramCharSequence, ArrayList<? extends Parcelable> paramArrayList)
    {
        this.a.putParcelableArrayList("key.data", paramArrayList);
        setText(paramCharSequence);
    }

    public void a(CharSequence paramCharSequence, Parcelable[] paramArrayOfParcelable)
    {
        this.a.putParcelableArray("key.data", paramArrayOfParcelable);
        setText(paramCharSequence);
    }

    public Uri getAttachment()
    {
        return (Uri)this.a.getParcelable("key.attachment");
    }

    public CharSequence getCharSequenceData()
    {
        CharSequence localCharSequence = this.a.getCharSequence("key.data");
        if (!TextUtils.isEmpty(localCharSequence))
            return localCharSequence;
        return "";
    }

    public Parcelable[] getParcelableArrayData()
    {
        return this.a.getParcelableArray("key.data");
    }

    public <T extends Parcelable> ArrayList<T> getParcelableArrayListData()
    {
        return this.a.getParcelableArrayList("key.data");
    }

    public <T extends Parcelable> T getParcelableData()
    {
        return this.a.getParcelable("key.data");
    }

//    public <T extends Serializable> T getSerializable()
//    {
//        return this.a.getSerializable("key.data");
//    }

    public void onRestoreInstanceState(Parcelable paramParcelable)
    {
        this.a = ((Bundle)paramParcelable);
        super.onRestoreInstanceState(this.a.getParcelable("key.data.parent"));
    }

    public Parcelable onSaveInstanceState()
    {
        Parcelable localParcelable = super.onSaveInstanceState();
        this.a.putParcelable("key.data.parent", localParcelable);
        return this.a;
    }

    protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
        super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
        if (this.a != null)
            a(paramCharSequence);
    }

    void setAttributes(TypedArray paramTypedArray)
    {
        this.g = paramTypedArray.getResourceId(0, 0);
        this.h = paramTypedArray.getResourceId(1, 0);
        this.i = paramTypedArray.getResourceId(2, 0);
        CharSequence text = paramTypedArray.getText(3);
//        text = "wanghao";
        setHint(text);
        a(getText(), (Parcelable)null);
    }

//    public void setListener(ag paramag)
//    {
//        this.j = paramag;
//        if (this.j != null)
//            this.j.a(b(), a());
//    }
}

/* Location:
 * Qualified Name:     com.yelp.android.ui.activities.mutatebiz.Field
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */