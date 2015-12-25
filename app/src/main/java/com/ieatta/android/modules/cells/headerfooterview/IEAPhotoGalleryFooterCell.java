package com.ieatta.android.modules.cells.headerfooterview;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.yelp.com.commonlib.EnvironmentUtils;

import com.ieatta.android.R;
import com.ieatta.android.extensions.storage.models.CellType;
import com.ieatta.android.modules.adapter.IEAViewHolder;
import com.ieatta.android.modules.adapter.enums.ViewHolderType;
import com.ieatta.android.modules.common.edit.SectionPhotoGalleryFooterCellModel;

/**
 * Created by djzhang on 12/2/15.
 */
public class IEAPhotoGalleryFooterCell extends IEAViewHolder {
    public static CellType getType() {
        return new CellType(IEAPhotoGalleryFooterCell.class, R.layout.businesspage_section_footer);
    }

    @Override
    protected boolean shouldClickItem() {
        return false;
    }

    @Override
    public ViewHolderType getViewHolderType() {
        return ViewHolderType.footer;
    }

    private IEAPhotoGalleryFooterCell self = this;

    private SectionPhotoGalleryFooterCellModel model;
    private Button footerLargeButton;

    public IEAPhotoGalleryFooterCell(View itemView) {
        super(itemView);

        self.footerLargeButton = (Button) itemView.findViewById(R.id.footer_large_button);
        self.footerLargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.model.viewController.presentPhotoGallery(0);
            }
        });
    }

    @Override
    public void render(Object value) {
        self.model = (SectionPhotoGalleryFooterCellModel) value;

        // update UI
        String title = EnvironmentUtils.sharedInstance.getGlobalContext().getResources().getString(R.string.See_All_Photos) + " " + self.model.photosCount;
        self.footerLargeButton.setText(title);
    }

}